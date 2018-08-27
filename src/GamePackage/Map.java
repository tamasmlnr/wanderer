package GamePackage;

import Character.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Map extends JPanel {
  public static final int WIDTH = 720;
  public static final int HEIGHT = 720;
  BufferedImage floorTile;
  BufferedImage wallTile;
  BufferedImage hero;
  int[][] mapArray = new int[][]{{1, 1, 1, 0, 1, 0, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 0, 1, 0, 0, 1}, {1, 0, 0, 0, 1, 0, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1}, {1, 0, 1, 0, 1, 1, 1, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 0, 1, 0, 0, 1, 0, 1},};

  public Map() throws IOException {
    BufferedImage image = ImageIO.read(new File("img/floor.png"));
    BufferedImage wallImage = ImageIO.read(new File("img/wall.png"));
    BufferedImage heroImg = ImageIO.read(new File("img/hero-down.png"));
    floorTile = image;
    hero = heroImg;
    wallTile = wallImage;
  }

//  public void drawCharacters(Graphics g) {
//        g.drawImage(hero, Hero.y, Hero.x, this);
//      }


  public static void drawCreature(int x, int y, Creature creature) {

  }

  protected void paintComponent(Graphics g) {
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {

        int mod_x = WIDTH / 10 * x;
        int mod_y = HEIGHT / 10 * y;

        switch (mapArray[y][x]) {
          case 0:
            g.drawImage(wallTile, mod_x, mod_y, this);
            break;
          case 1:
            g.drawImage(floorTile, mod_x, mod_y, this);
            break;
        }
      }
    }
  }


  public Dimension getPreferredSize() {
    return new Dimension(720, 720);
  }
}