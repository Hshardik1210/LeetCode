
package objectdetection;

import java.io.IOException;

public class videoDTRC {
   
    
videoDTRC() throws IOException
{
    String command = "python C:/Users/"+"user.name"+"/Desktop/openCV/recongnizer/videoDetector.py" ;
    Process p = Runtime.getRuntime().exec(command);
    if(p.isAlive() == false)
    {
        System.out.println("dead");
    }
}

public static void main(String []args) throws IOException
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
    new videoDTRC();
}
    
}

