
package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;


public abstract class ImageComponent extends JComponent implements Serializable {
    
    
    protected String imgSource;
    protected BufferedImage image;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        AffineTransform at = new AffineTransform();
        g2D.drawImage(this.getScaledInstance(this.image, this.getWidth(), this.getHeight(), false), at, null);
    }

    public BufferedImage getScaledInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            w = img.getWidth();
            h = img.getHeight();
        } else {
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }
    
    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
        this.repaint();
    }
    
    public void readImage() {
        try {
            this.image = ImageIO.read(getClass().getResource(this.imgSource));
        } catch (IOException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
