import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Map extends JPanel {
  public static final int WIDTH=720;
  public static final int HEIGHT=720;
  BufferedImage floorTile;



  public Map() throws IOException {
    BufferedImage image = ImageIO.read(new File("img/floor.png"));
    BufferedImage wallImage = ImageIO.read(new File("img/wall.png"));
    BufferedImage heroImg = ImageIO.read(new File("img/hero-down.png"));
    floorTile = image;
  }



  public static void drawCreatre(int x,int y, Creature creature) {

  }

  protected void paintComponent(Graphics g) {
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {

        int mod_x = WIDTH/10 * x;
        int mod_y = HEIGHT/10 * y;
            g.drawImage(floorTile, mod_x, mod_y, this);
        }
      }
    }



      public Dimension getPreferredSize () {
        return new Dimension(720, 720);
      }


    }