package objectdetection;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import org.opencv.core.Core;

public class imagepart extends javax.swing.JFrame {

    File f;
    File compressedImageFile,croppedImageFile;
    String imagepath;
    ImageIcon ic;
    BufferedImage bim,bi;
    int ix1, iy1, ix2, iy2,xv,yv;
    Double width ,height ,rx ,ry ,xval,yval ;
    public camera c;

    public imagepart() {
        initComponents();
        b2.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        crop.setEnabled(false);
        combo.setEnabled(false);
        ok.setEnabled(false);
        detect.setEnabled(false);
        tf1.setEnabled(false);
        tf2.setEnabled(false);
        Do.setEnabled(false);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (compressedImageFile != null) {
                    compressedImageFile.delete();
                }
                if (croppedImageFile != null) {
                    croppedImageFile.delete();
                }
                System.exit(0);
            }
        });
    }

    void fitToLabel(JLabel l2, ImageIcon ic, BufferedImage bim, int W, int H) {
        if (W > l2.getWidth() && H < l2.getHeight()) {
            ic = new ImageIcon(bim.getScaledInstance(l2.getWidth(), H, Image.SCALE_SMOOTH));
        } else if (W < l2.getWidth() && H > l2.getHeight()) {
            ic = new ImageIcon(bim.getScaledInstance(W, l2.getHeight(), Image.SCALE_SMOOTH));
        } else if (W > l2.getWidth() && H > l2.getHeight()) {
            ic = new ImageIcon(bim.getScaledInstance(l2.getWidth(), l2.getHeight(), Image.SCALE_SMOOTH));
        } else if (W < ic.getIconWidth() || H < ic.getIconHeight()) {
            ic = new ImageIcon(bim.getScaledInstance(W, H, Image.SCALE_SMOOTH));
        } else {
            ic = new ImageIcon(bim);
        }

        l2.setIcon(ic);
        l2.setHorizontalAlignment(JLabel.CENTER);
        l2.setVerticalAlignment(JLabel.CENTER);
    }

    Double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0;
    private final int SIZE = 8;
    private Rectangle2D[] points = {new Rectangle2D.Double(x1, y1, SIZE, SIZE), new Rectangle2D.Double(x2, y2, SIZE, SIZE)};
    Rectangle2D s = new Rectangle2D.Double();
    ShapeResizeHandler ada;

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.blue);
        Graphics2D g2 = (Graphics2D) g;

        for (Rectangle2D point : points) {
            g2.fill(point);
        }
        s.setRect(points[0].getCenterX(), points[0].getCenterY(),
                Math.abs(points[1].getCenterX() - points[0].getCenterX()),
                Math.abs(points[1].getCenterY() - points[0].getCenterY()));
        x1 = points[0].getCenterX();
        y1 = points[0].getCenterY();
        x2 = points[1].getCenterX();
        y2 = points[1].getCenterY();
        g2.draw(s);
    }

    class ShapeResizeHandler extends MouseAdapter {

        Rectangle2D r = new Rectangle2D.Double(0, 0, SIZE, SIZE);
        private int pos = -1;

        @Override
        public void mousePressed(MouseEvent event) {
            Point p = event.getPoint();
            for (int i = 0; i < points.length; i++) {
                if (points[i].contains(p)) {
                    pos = i;
                    return;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            pos = -1;
        }

        @Override
        public void mouseDragged(MouseEvent event) {

            if (pos == -1) {
                return;
            }
            if (bim.getWidth() > l1.getWidth() || bim.getHeight() > l1.getHeight()) {
                ix1 = 8;
                iy1 = 31;
                ix2 = 466;
                iy2 = 317;
                points[pos].setRect(event.getPoint().x, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());

                if (points[pos].getCenterX() < ix1 && points[pos].getCenterY() < iy1) {
                    points[pos].setRect(ix1 - 4, iy1 - 4, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterX() < ix1) {
                    points[pos].setRect(ix1 - 4, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterY() < iy1) {
                    points[pos].setRect(event.getPoint().x, iy1 - 4, points[pos].getWidth(), points[pos].getHeight());
                }

                if (points[pos].getCenterX() > ix2 && points[pos].getCenterY() > iy2) {
                    points[pos].setRect(ix2 - 4, iy2 - 4, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterX() > ix2) {
                    points[pos].setRect(ix2 - 4, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterY() > iy2) {
                    points[pos].setRect(event.getPoint().x, iy2 - 4, points[pos].getWidth(), points[pos].getHeight());
                }

            } else {
                points[pos].setRect(event.getPoint().x, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());

                if (points[pos].getCenterX() < ix1 && points[pos].getCenterY() < iy1) {
                    points[pos].setRect(ix1 - 2, iy1 - 2, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterX() < ix1) {
                    points[pos].setRect(ix1 - 2, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterY() < iy1) {
                    points[pos].setRect(event.getPoint().x, iy1 - 2, points[pos].getWidth(), points[pos].getHeight());
                }

                if (points[pos].getCenterX() > ix2 && points[pos].getCenterY() > iy2) {
                    points[pos].setRect(ix2 - 6, iy2 - 6, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterX() > ix2) {
                    points[pos].setRect(ix2 - 6, event.getPoint().y, points[pos].getWidth(), points[pos].getHeight());
                } else if (points[pos].getCenterY() > iy2) {
                    points[pos].setRect(event.getPoint().x, iy2 - 6, points[pos].getWidth(), points[pos].getHeight());
                }
            }
            repaint();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        crop = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        combo = new javax.swing.JComboBox<>();
        tf1 = new javax.swing.JTextField();
        tf2 = new javax.swing.JTextField();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        jp1 = new javax.swing.JPanel();
        l1 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        capture = new javax.swing.JButton();
        set = new javax.swing.JButton();
        Do = new javax.swing.JButton();
        detect = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 500, 500));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(15, 15));

        b1.setText("Browse");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setText("Compress");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        crop.setText("Crop");
        crop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cropActionPerformed(evt);
            }
        });

        b4.setText("Black and White");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Blur", "Blur Complete", "Blur Inside", "Blur Outside", "Custom" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        b5.setText("Reduce Dimension");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setText("Save As");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        jp1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        capture.setText("WebCam");
        capture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureActionPerformed(evt);
            }
        });

        set.setText("set");
        set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setActionPerformed(evt);
            }
        });
        set.setVisible(false);

        Do.setText("Do");
        Do.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoActionPerformed(evt);
            }
        });

        detect.setFont(new java.awt.Font("Trajan Pro", 1, 11)); // NOI18N
        detect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Detect", "Potrait" }));
        detect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(detect, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b5)
                        .addGap(18, 18, 18)
                        .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(b1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(capture, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Do)
                                                .addGap(0, 3, Short.MAX_VALUE)))
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(crop, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ok)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(set)
                                .addGap(45, 45, 45))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(capture, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crop, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ok))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Do, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(set)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(detect, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int index;
    MouseListener m1,m2;
    MouseMotionListener m3;
    
    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed

        index = combo.getSelectedIndex();
        if(index==0 || index==1 ||index==4)
            fitToLabel(l1,new ImageIcon(bim),bim,bim.getWidth(),bim.getHeight());
        Do.setEnabled(true);
        try
        {
            if(index == 2 || index ==3 || index == 1 || index == 0)
            {
                l1.removeMouseListener(m1);
                l1.removeMouseListener(m2);
                l1.removeMouseMotionListener(m3);
                if(index == 2 || index == 3)
                    drawRect();
            }
        }
        catch(Exception e)
        {
           if(index == 2 || index == 3)
                drawRect();
        }
        if(index==4)
        {   
            m1=new MouseAdapter()
            {
                public void mouseEntered(java.awt.event.MouseEvent evt) 
                    { 
                        l1MouseEntered(evt); 
                    }
                
                    private void l1MouseEntered(MouseEvent evt) 
                    { //-----------------------------------------------------
                        m2 = new MouseAdapter() 
                        {
                            public void mousePressed(java.awt.event.MouseEvent evt) 
                                {
                                    l1MousePressed(evt);
                                }
                    
                            private void l1MousePressed(MouseEvent evt)
                            {
                                int  x = evt.getX();
                                int y = evt.getY();
                                Graphics g = l1.getGraphics();
                                g.drawRect( x , y , 10 , 10 );
                                doCompute_(x,y,x+10,y+10);
                            }
                        };
                     //-----------------------------------------------------------------
                     
                     //-----------------------------------------------------------------
                        m3=new MouseMotionAdapter()
                        {
                            public void mouseDragged(java.awt.event.MouseEvent evt)
                                {l1MouseDragged(evt);}
            
                            private void l1MouseDragged(MouseEvent evt) 
                            {
                                int x = evt.getX();
                                int y = evt.getY();
                                Graphics g = l1.getGraphics();
                                g.drawRect( x , y , 10 , 10 );
                                doCompute_(x,y,x+10,y+10);
                            }
                        };
                        l1.addMouseListener(m2);
                        l1.addMouseMotionListener(m3);  
                     //-----------------------------------------------------------
                }
            };
            //----------------------------------------------------------------------------------------------
            l1.addMouseListener(m1);
        }
    }//GEN-LAST:event_comboActionPerformed
            
    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed

        JFileChooser choose = new JFileChooser();

        if (choose.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            try {
                File outputfile = new File(choose.getSelectedFile().getAbsolutePath() + ".jpg");
                ImageIO.write(bim, "jpg", outputfile);
                if (compressedImageFile != null) {
                    compressedImageFile.delete();
                }
                if (croppedImageFile != null) {
                    croppedImageFile.delete();
                }
                JOptionPane.showMessageDialog(this, "File Saved Successfully !!");
                System.exit(0);
            } catch (IOException ex) { }
        }
    }//GEN-LAST:event_b6ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        int w, h;

        try {
            w = Integer.parseInt(tf1.getText());
            h = Integer.parseInt(tf2.getText());
            ic = new ImageIcon(bim);
            if (w > ic.getIconWidth() || h > ic.getIconHeight()) {
                JOptionPane.showMessageDialog(null, "Size can't be more than original size.", "Alert", ERROR_MESSAGE);
            } else {
                fitToLabel(l1, ic, bim, w, h);
            }

            int type = bim.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bim.getType();
            bim = resizeImage(bim, type, w, h);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter both values !!", "Alert", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_b5ActionPerformed

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        return resizedImage;
    }
    private void cropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cropActionPerformed
        drawRect();
        ok.setEnabled(true);
        b2.setEnabled(false);
        capture.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        crop.setEnabled(false);
        combo.setEnabled(false);
        b1.setEnabled(false);
        detect.setEnabled(false);
        tf1.setEnabled(false);
        tf2.setEnabled(false);
        Do.setEnabled(false);
    }//GEN-LAST:event_cropActionPerformed

    private void drawRect()
    {
        x1 = 220.0;
        y1 = 155.0;
        x2 = 246.0;
        y2 = 181.0;
        int t = l1.getWidth();
        int t2 = l1.getHeight();
        points[0] = new Rectangle2D.Double(x1, y1, SIZE, SIZE);
        points[1] = new Rectangle2D.Double(x2, y2, SIZE, SIZE);
        ix1 = 237 - (ic.getIconWidth() / 2);
        iy1 = 174 - (ic.getIconHeight() / 2);
        ix2 = 237 + (ic.getIconWidth() / 2);
        iy2 = 174 + (ic.getIconHeight() / 2);
        Graphics g = getGraphics();
        ada = new ShapeResizeHandler();
        addMouseListener(ada);
        addMouseMotionListener(ada);
        paint(g);
    }
    
    
    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed

        for (int i = 0; i < bim.getWidth(); i++) {
            for (int j = 0; j < bim.getHeight(); j++) {
                Color color = new Color(bim.getRGB(i, j));
                int rc = color.getRed();
                int gc = color.getGreen();
                int bc = color.getBlue();
                int bw = (rc + gc + bc) / 3;
                bim.setRGB(i, j, new Color(bw, bw, bw).getRGB());
            }
        }
        fitToLabel(l1, ic, bim, bim.getWidth(), bim.getHeight());
    }//GEN-LAST:event_b4ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        String q = JOptionPane.showInputDialog("Enter Image Quality in Percentage");
        if (JOptionPane.OK_OPTION == 0) {
            float quality = Float.parseFloat(q) / 100;
            try {
                    String filelocation = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(File.separator)) + "\\Compressed_" + f.getName();
                    compressedImageFile = new File(filelocation);
                    OutputStream os = new FileOutputStream(compressedImageFile);

                    Path path = Paths.get(compressedImageFile.getAbsolutePath());
                    Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
                    if (hidden != null && !hidden) {
                        Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
                    }

                Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
                ImageWriter writer = (ImageWriter) writers.next();

                ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                writer.setOutput(ios);

                ImageWriteParam param = writer.getDefaultWriteParam();

                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);
                writer.write(null, new IIOImage(bim, null, null), param);
                os.close();
                ios.close();
                writer.dispose();

                JFrame fra = new JFrame();
                fra.setBounds(0, 0, 525, 400);
                fra.setLayout(null);
                fra.setResizable(false);
                JLabel inlabel = new JLabel("");
                JLabel size=new JLabel("Size :");
                
                size.setBounds(350, 315, 70, 20);
                JLabel sbf = new JLabel("");
                JLabel saf = new JLabel("");
                sbf.setBounds(380, 327, 100, 20);
                saf.setBounds(380, 340, 100, 20);
                
                inlabel.setBounds(25, 25, 458, 285);
                fra.add(inlabel);
                fra.add(saf);
                fra.add(sbf);
                fra.add(size);
                JButton inb = new JButton("Save");
                inb.setBounds(230, 320, 70, 30);
                fra.add(inb);

                ImageIcon ic1 = new ImageIcon(compressedImageFile.getAbsolutePath());
                BufferedImage bim1 = ImageIO.read(compressedImageFile);
                double filesize;
                filesize = f.length()/1024.0;
                sbf.setText("Before  "+new DecimalFormat("##.##").format(filesize)+" Kb");
                filesize=compressedImageFile.length()/1024.0;
                saf.setText(" After  "+new DecimalFormat("##.##").format(filesize)+" Kb");
                
                fitToLabel(inlabel, ic1, bim1, bim1.getWidth(), bim1.getHeight());

                fra.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if (compressedImageFile != null) {
                            compressedImageFile.delete();
                        }
                        fra.dispose();
                    }
                });

                inb.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            inbActionPerformed(evt);
                        } catch (IOException ex) {
                        }
                    }

                    private void inbActionPerformed(ActionEvent evt) throws IOException {
                        f=compressedImageFile;
                        imagepath=f.getAbsolutePath();
                        ic = new ImageIcon(f.getAbsolutePath());
                        bim = ImageIO.read(f);
                        bi=bim;
                        fitToLabel(l1, ic, bim, bim.getWidth(), bim.getHeight());
                        fra.dispose();
                    }
                });

                fra.setVisible(true);
            } catch (IOException e) {
            }
        }

    }//GEN-LAST:event_b2ActionPerformed
        
    void doCompute()
    {
        
        width = Math.abs(x2 - x1);
        height = Math.abs(y2 - y1);
        rx = (double) bim.getWidth() / l1.getWidth();
        ry = (double) bim.getHeight() / l1.getHeight();

        if (bim.getWidth() > l1.getWidth() || bim.getHeight() > l1.getHeight()) {
            width = width * rx;
            height = height * ry;
        }

         xval = rx * (x1 - 8);
         yval = ry * (y1 - 31); 
    }    
        
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed

            doCompute();
            bim = bim.getSubimage(xval.intValue(), yval.intValue(), width.intValue(), height.intValue());
            fitToLabel(l1, new ImageIcon(bim), bim, width.intValue(), height.intValue());
            
            b2.setEnabled(true);
            b4.setEnabled(true);
            b5.setEnabled(true);
            b6.setEnabled(true);
            crop.setEnabled(true);
            combo.setEnabled(true);
            detect.setEnabled(true);
            tf1.setEnabled(true);
            tf2.setEnabled(true);
            ok.setEnabled(false);
            b1.setEnabled(true);
            capture.setEnabled(true);
            
            int type = bim.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bim.getType();
            bim = resizeImage(bim, type, bim.getWidth(), bim.getHeight());
            bi=bim;
            
            try {
                
                String filelocation = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(File.separator)) + "\\Cropped_" + f.getName();
                croppedImageFile= new File(filelocation);
                ImageIO.write(bim, "jpg", croppedImageFile);
                f=croppedImageFile;
                imagepath=f.getAbsolutePath();
                Path path = Paths.get(f.getAbsolutePath());
                Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
                if (hidden != null && !hidden) {
                    Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
            }
        } catch (IOException ex) {}    
    }//GEN-LAST:event_okActionPerformed


    private void captureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureActionPerformed

        c = new camera(set);
        c.setVisible(true);
        b2.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        crop.setEnabled(true);
        combo.setEnabled(true);
        detect.setEnabled(true);
        tf1.setEnabled(true);
        tf2.setEnabled(true);

    }//GEN-LAST:event_captureActionPerformed


    private void setActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setActionPerformed
        try {
            File temp = new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Snapshot\\"));
            int counts = temp.listFiles().length;
            imagepath = "C:/Users/" + System.getProperty("user.name") + "/Documents/Snapshot/snap" + String.valueOf(counts) + ".jpg";
            f = new File(imagepath);
            temp.delete();
            bim = ImageIO.read(f);
            ic = new ImageIcon(bim);
            fitToLabel(l1, ic, bim, bim.getWidth(), bim.getHeight());
        } catch (IOException ex) {}
    }//GEN-LAST:event_setActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed

        JFileChooser choose = new JFileChooser();
        int result = choose.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            f = choose.getSelectedFile();
            try {
                imagepath = f.getAbsolutePath();
                bim = ImageIO.read(f);
                bi=bim;
                ic = new ImageIcon(bim);
                fitToLabel(l1, ic, bim, bim.getWidth(), bim.getHeight());
            } catch (IOException e) {
            }
            b2.setEnabled(true);
            b4.setEnabled(true);
            b5.setEnabled(true);
            b6.setEnabled(true);
            crop.setEnabled(true);
            combo.setEnabled(true);
            detect.setEnabled(true);
            tf1.setEnabled(true);
            tf2.setEnabled(true);
        }
    }//GEN-LAST:event_b1ActionPerformed

    private void DoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoActionPerformed
       
        doCompute();
        blur b = new blur(imagepath,xval.intValue(),yval.intValue(),width.intValue(),height.intValue());
             
        try
        {
            if(index==0)    
                bim=bi;
            
            if(index == 1)
                bim = b.fullBlur();

            if(index == 2)      //inside blur
                bim = b.blurImg(bim);
       
            if(index == 3)      //outside blur
                bim = b.blurRest(bim);
                
        }catch(IOException e){}
        
        fitToLabel(l1,new ImageIcon(bim),bim,bim.getWidth(),bim.getHeight());
        l1.removeMouseListener(m1);
        l1.removeMouseListener(m2);
        l1.removeMouseMotionListener(m3);
        Do.setEnabled(false);
    }//GEN-LAST:event_DoActionPerformed

    private void detectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detectActionPerformed

        if (detect.getSelectedIndex() == 0)
       {
        try
        {
            detectImage di = new detectImage(imagepath);
            bim = di.doDetection() ;
            fitToLabel(l1,new ImageIcon(bim),bim,bim.getWidth(),bim.getHeight());
        }
        catch (Exception e) { }
       }
       
       if (detect.getSelectedIndex() == 1)
       {
        try
        {
           detectImage di = new detectImage(imagepath);
           bim = di.doBlur();
           fitToLabel(l1,new ImageIcon(bim),bim,bim.getWidth(),bim.getHeight());
        }
        catch (IOException e) {}
       }
    }//GEN-LAST:event_detectActionPerformed
        
    void doCompute_(int x1,int y1,int x2,int y2)
    {
        int width , height; 
        width = Math.abs(x2 - x1);
        height = Math.abs(y2 - y1);
        rx = (double) bim.getWidth() / l1.getWidth();
        ry = (double) bim.getHeight() / l1.getHeight();

        if (bim.getWidth() > l1.getWidth() || bim.getHeight() > l1.getHeight()) {
            width = (int)(width * rx);
            height = (int)(height * ry);
        }

        xv =(int) (rx * (x1));
        yv = (int)(ry * (y1));
        
        blur b = new blur(f.getAbsolutePath(),xv,yv,width,height);
        BufferedImage bi = null ; 

        try {
                bim = b.blurImg(bim);
            } catch (IOException ex) {}
           fitToLabel(l1,new ImageIcon(bim),bim,bim.getWidth(),bim.getHeight());
    }    
    
    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new imagepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Do;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton capture;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton crop;
    private javax.swing.JComboBox<String> detect;
    private javax.swing.JPanel jp1;
    private javax.swing.JLabel l1;
    private javax.swing.JButton ok;
    private javax.swing.JButton set;
    private javax.swing.JTextField tf1;
    private javax.swing.JTextField tf2;
    // End of variables declaration//GEN-END:variables
}
