package objectdetection;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class detectImage {
    
    private int x , y ;
    private Mat mt , fullBlur ;
        
    private String path;
    detectImage(String path)
    {
     this.path = path ;   
     mt = Imgcodecs.imread(path);
    }
    
    detectImage(Mat mt)
    {
        this.mt = mt ;
    }
    
    BufferedImage doBlur() throws IOException
    {
        fullBlur = new Mat(mt.width(),mt.height(),mt.type());
        Imgproc.blur(mt, fullBlur, new Size(50,50));
        MatOfRect mor= detect();
        blurDetection(mor);
        return new blur().image(fullBlur);
    }
       
   private void blurDetection(MatOfRect mor)
   {
        Mat blur ;
        for(Rect rect : mor.toArray())
        {
            x = rect.x ;
            y = rect.y ;
            Mat eye = new Mat(mt,rect); 
            Mat detected = new Mat(mt,new Rect(rect.x,rect.y,rect.width,rect.height));
            
            blur = new Mat(fullBlur,new Rect(rect.x,rect.y,rect.width,rect.height));
            for(int i=0;i<detected.rows();i++)
               {
                   for(int j=0;j<detected.cols();j++)
                   {
                       double []channel = detected.get(i, j);
                       blur.put(i, j, channel);
                   }
               }
        }
   }
    
    
    BufferedImage doDetection() throws IOException
    {
                MatOfRect mor = detect();
                drawFace(mor);
                return new blur().image(mt);
    }
    
    private MatOfRect detect()
    {
        CascadeClassifier face = new CascadeClassifier("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_default.xml");
        MatOfRect mor = new MatOfRect(); 
        face.detectMultiScale(mt, mor);
        return mor;
    }
    
    private void drawFace(MatOfRect mor)
    {
        
        for ( Rect rect : mor.toArray())
        {
            x = rect.x ;
            y = rect.y ;
            Imgproc.rectangle(mt, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height) , new Scalar(0,255,0), 5);
            Mat eye = new Mat(mt,rect); 
            detectEye(eye);
        }
        
    }
    void detectEye(Mat eye)
    {
        CascadeClassifier eyes = new CascadeClassifier("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\opencv\\build\\etc\\haarcascades\\haarcascade_eye.xml");
        MatOfRect mori = new MatOfRect();
        eyes.detectMultiScale(eye, mori);
        drawEye(mori);
    }
    
    private void drawEye(MatOfRect mor)
    {
        for (Rect rect : mor.toArray())
            Imgproc.rectangle(mt, new Point(x+rect.x,y+rect.y), new Point(x+rect.x+rect.width,y+rect.y+rect.height) , new Scalar(0,255,0), 5); 
    }
}
