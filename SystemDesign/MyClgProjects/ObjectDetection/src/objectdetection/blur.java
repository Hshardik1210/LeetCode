package objectdetection;

import org.opencv.core.Point;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class blur 
{
    Mat mt ;
    String path ;
    BufferedImage bim  ;
    int x1 , y1 , dx , dy ;
    int flag = 0 ;
    
    blur(String path , int x1 , int y1 , int dx , int dy)
    {
        flag = 1 ;
        mt = Imgcodecs.imread(path);
                  
        this.path = path ;
        this.x1= x1;
        this.y1 = y1;
        this.dx = dx ;
        this.dy = dy ;
    }
    
    blur(BufferedImage bim , int x1 , int y1 , int dx , int dy)
    {
        flag = 2 ;
        this.bim = bim ;
        this.x1= x1;
        this.y1 = y1;
        this.dx = dx ;
        this.dy = dy ; 
    }
    
    blur()
    { ; }
    
    void BIMToMat()
    {
        byte[] pixels = ((DataBufferByte)  bim.getRaster().getDataBuffer()).getData();
        mt.put(0, 0, pixels);
    }
    
    BufferedImage  blurImg() throws IOException 
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat nw = new Mat(mt,new Rect(x1,y1,dx,dy));
        Imgproc.blur(nw, nw, new Size(new Point(10,5)));
        return image(mt);
                       
    }
    
    BufferedImage  blurImg(BufferedImage bim) throws IOException 
    {   
        this.bim = bim ; 
        BIMToMat();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat nw = new Mat(mt,new Rect(x1,y1,dx,dy));
        Imgproc.blur(nw, nw, new Size(new Point(10,5)));
        return image(mt);
                     
    }


    BufferedImage blurRest() throws IOException
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mt = Imgcodecs.imread(path);
        Mat nw = new Mat(mt,new Rect(x1,y1,dx,dy));
        Mat fullBlur = new Mat(mt.rows(),mt.cols(),mt.type());
        Imgproc.blur(mt, fullBlur, new Size(50,50));
        Mat blur = new Mat(fullBlur,new Rect(x1,y1,dx,dy));
        for(int i=0;i<nw.rows();i++)
        {
            for(int j=0;j<nw.cols();j++)
            {
                double []channel = nw.get(i, j);
                blur.put(i, j,channel);
            }
        }
    return image(fullBlur);
    }
        
    BufferedImage blurRest(BufferedImage bim) throws IOException
    {
        this.bim = bim ; 
        BIMToMat();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mt = Imgcodecs.imread(path);
        Mat nw = new Mat(mt,new Rect(x1,y1,dx,dy));
        Mat fullBlur = new Mat(mt.rows(),mt.cols(),mt.type());
        Imgproc.blur(mt, fullBlur, new Size(50,50));
        Mat blur = new Mat(fullBlur,new Rect(x1,y1,dx,dy));
        for(int i=0;i<nw.rows();i++)
        {
            for(int j=0;j<nw.cols();j++)
            {
                double []channel = nw.get(i, j);
                blur.put(i, j,channel);
            }
        }
    return image(fullBlur);
    }
    
    BufferedImage image(Mat mt) throws IOException
    {
            
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", mt, mob);
        byte [] bt = mob.toArray();
        InputStream in = new ByteArrayInputStream(bt);
        BufferedImage bim =  ImageIO.read(in);
                   
        return bim;
    }
    
    BufferedImage fullBlur() throws IOException
    {
        Mat mt = Imgcodecs.imread(path);
        Mat fullBlur = new Mat(mt.rows(),mt.cols(),mt.type());   
        Imgproc.blur(mt, fullBlur, new Size(50,50));
        
        return image(fullBlur);
    }
}
