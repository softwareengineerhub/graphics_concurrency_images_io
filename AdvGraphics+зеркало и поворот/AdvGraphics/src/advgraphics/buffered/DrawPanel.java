package advgraphics.buffered;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImageOp;
import java.awt.geom.AffineTransform;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DrawPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    private BufferedImage img;

    public DrawPanel() {

    }

    public void setImage(BufferedImage img){
        this.img = img;
        repaint();
    }

    public BufferedImage getImage(){
        return img;
    }

    public void applyFilter(ImageFilter filter){
        if (img!=null){
            ImageProducer fis = new FilteredImageSource(img.getSource(), filter);
            Image res = Toolkit.getDefaultToolkit().createImage(fis);
            BufferedImage nImg = new BufferedImage(img.getWidth(),
                    img.getHeight(), img.getType());
            Graphics2D g2 = nImg.createGraphics();
            g2.drawImage(res, 0, 0, this);
            g2.dispose();
            img = nImg;
            repaint();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (img!=null){
            g2.drawImage(img, null, (getWidth()-img.getWidth())/2, (getHeight()-img.getHeight())/2);
        }
    }

    public void mirrorImage(){
        if (img!=null){
            Thread t = new Thread(){
                public void run(){
                    for (int yyy = 0; yyy < img.getHeight(); yyy++) {
                        for (int xxx = 0; xxx < img.getWidth() / 2; xxx++) {
                            int curr = img.getRGB(xxx, yyy);
                            img.setRGB(xxx, yyy,
                                       img.getRGB(img.getWidth() - xxx - 1, img.getHeight()-yyy-1));
                            img.setRGB(img.getWidth() - xxx - 1, img.getHeight()-yyy-1, curr);
                        }
                        repaint();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            };
            t.setPriority(Thread.NORM_PRIORITY);
            t.start();
        }
    }

    public void applyOp(BufferedImageOp op){
        if (img!=null){
            img = op.filter(img, null);
            repaint();
        }
    }

    public void rotate(){
        if (img!=null){
            BufferedImage temp = new BufferedImage(img.getHeight(),
                    img.getWidth(), img.getType());
            AffineTransform at = new AffineTransform();
            at.translate(temp.getWidth() / 2, temp.getHeight()/2 );
        //  at.translate(temp.getHeight()/2,temp.getWidth()/2);
            at.rotate(Math.toRadians(90));
            Graphics2D g2 = temp.createGraphics();
            g2.setTransform(at);
            g2.drawImage(img, -img.getWidth()/2,-img.getHeight()/2, this);
            g2.dispose();
            img = temp;
            repaint();
        }
    }

}
