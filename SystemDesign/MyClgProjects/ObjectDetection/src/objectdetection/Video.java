package objectdetection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.*;
//COPY  opencv\build\x64\vc15\opencv_ffmpeg341_64.dll      to        opencv\build\java\x64

public class Video 
{
    VideoCapture vCapture;
    Mat matFrame;
    JLabel l;
    JFrame j;
    String videopath;
    ImageIcon ic;

   Video(String s1) throws IOException
   {
       videopath=s1;
       playvideo();
   }
   
   Video() throws IOException
   {
       videopath="C:\\Users\\"+System.getProperty("user.name") +"\\Documents\\fd1.MP4";
       playvideo();
   }
   
    void playvideo() throws IOException
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        vCapture = new VideoCapture();
        matFrame = new Mat();
        try {
                vCapture.open(videopath);
            } catch (Exception e1) {System.out.println("No video found.");}
                    
        j=new JFrame();
        j.setLayout(new BorderLayout());
        j.setBounds(0,0,650,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        j.setLocation(dim.width/2-j.getSize().width/2, dim.height/2-j.getSize().height/2);
        j.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        l=new JLabel();
        l.setBounds(0,0,655,450);
        j.add(l);
        j.setVisible(true);
    
        if (vCapture.isOpened()) 
        {
            while (vCapture.grab())
            {
                vCapture.retrieve(matFrame);
                if (!matFrame.empty())
                {
                    matFrame = detect(matFrame);
                    MatOfByte mob=new MatOfByte();
                    Imgcodecs.imencode(".jpg", matFrame, mob);
                    byte ba[]=mob.toArray();
                
                    BufferedImage bi=ImageIO.read(new ByteArrayInputStream(ba));
                    ic=new ImageIcon(bi);
                    fitToLabel(bi,bi.getWidth(),bi.getHeight());             
                }
            } vCapture.release();
        }
    }


    void fitToLabel(BufferedImage bim,int W,int H)
    {
        if(W>l.getWidth() && H<l.getHeight())
            ic = new ImageIcon(bim.getScaledInstance(l.getWidth(), H, Image.SCALE_SMOOTH));
        else if(W<l.getWidth() && H>l.getHeight())
            ic = new ImageIcon(bim.getScaledInstance(W, l.getHeight(), Image.SCALE_SMOOTH));
        else if(W>l.getWidth()&& H>l.getHeight())
            ic = new ImageIcon(bim.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH));
        else if(W<ic.getIconWidth() || H<ic.getIconHeight())
            ic = new ImageIcon(bim.getScaledInstance(W, H, Image.SCALE_SMOOTH));
        else
            ic=new ImageIcon(bim);
        
        l.setIcon(ic);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);
    }


    public Mat detect(Mat mt) throws IOException
    {
        CascadeClassifier csd = new CascadeClassifier("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\opencv\\build\\etc\\lbpcascades\\lbpcascade_frontalface_improved.xml");
        MatOfRect mor = new MatOfRect();
        csd.detectMultiScale(mt, mor);
    
        return drawRect(mt,mor);
    }

    public  Mat drawRect(Mat mt,MatOfRect mor) throws IOException
    {
        for(Rect rect : mor.toArray())
            Imgproc.rectangle(mt, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(0,255,0));
        return mt;
    }
       
 public static void main(String[] args) throws IOException
 {
     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {} 
        catch (InstantiationException ex) {} 
        catch (IllegalAccessException ex) {} 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {}
     Video v=new Video();
 }
}