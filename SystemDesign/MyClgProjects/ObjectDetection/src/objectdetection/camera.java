package objectdetection;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
/**
 * @author Hardik
 * Paste in Package Properties/Run --> -Djava.library.path="C:\Users\Hardik\Desktop\opencv\build\java\x64"
 */
public class camera extends javax.swing.JFrame {

    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
    Mat frame;
    MatOfByte mem;
    BufferedImage bim1;
    int i;
    Thread t;
    imagepart im  ;
    JButton jbtn ;
    int set  = 0 ;
    public camera()
    {;}
    
    public camera(int i) {
        look();
        set = 1 ;
        init();
        webSource =new VideoCapture(0);
        myThread = new DaemonThread();
        t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
	snapshot.setVisible(false);
    } 
    
    public camera(JButton img) {
        set=0;
        look();
        this.jbtn = img;
        init();
        webSource =new VideoCapture(0);
        myThread = new DaemonThread();
        t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
	snapshot.setEnabled(true);
    }
    
    void init()
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        frame = new Mat();
        mem = new MatOfByte();
        initComponents();
        snapshot.setEnabled(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                myThread.runnable=false;
                webSource.release();
            }});
    }
    
    
    class DaemonThread implements Runnable
    {
    protected volatile boolean runnable = false;

    @Override
    public  void run()
    {
        synchronized(this)
        {
            while(runnable)
            {
                if(webSource.grab())
                {
		    	try
                        {
                            webSource.retrieve(frame);
                            
                            if(set == 1)
                            {
                                detectImage dt = new detectImage(frame);
                                bim1 = dt.doDetection();
                            }
                            else
                            {
                            
                                Imgcodecs.imencode(".bmp", frame, mem);
                                Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                                bim1 = (BufferedImage) im;
                            }
			    Graphics g=jPanel1.getGraphics();
                                try{
                                    if (g.drawImage(bim1, 0, 0, getWidth(), getHeight() -50 , 0, 0, bim1.getWidth(), bim1.getHeight(), null));
                                   }
                                catch (Exception e) {}
                        }
			 catch(IOException ex){}
                }
            }
        }
     }
   }

    BufferedImage getImage()
    {
        return bim1 ;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        snapshot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        snapshot.setText("Snapshot");
        snapshot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(snapshot)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(snapshot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        
    public void snap( ) throws IOException
    {
        File temp = new File(("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Snapshot"));
        if (!temp.exists())
            temp.mkdir();
        int counts = temp.listFiles().length;
        i=counts+1;
        String path="C:/Users/"+System.getProperty("user.name")+"/Documents/Snapshot/snap"+String.valueOf(i)+".jpg";
        temp = new File(path);
        ImageIO.write(bim1, "jpg", temp);
        if(jbtn==null);
        else
        jbtn.doClick();
    }
    private void snapshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotActionPerformed
        try {  snap(); } catch (IOException ex) {}
    }//GEN-LAST:event_snapshotActionPerformed

    void look()
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
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton snapshot;
    // End of variables declaration//GEN-END:variables

}
