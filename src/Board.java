import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JComponent implements KeyListener {
  public static final int WIDTH=720;
  public static final int HEIGHT=720;
  int testBoxX;
  int testBoxY;
  BufferedImage floorTile;
  BufferedImage wallTile;
  BufferedImage hero;
  int[][] mapArray = new int[][]{
      {1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
      {1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
      {1, 0, 0, 0, 1, 0, 1, 0, 0, 1},
      {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
      {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
      {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
      {1, 1, 1, 1, 1, 0, 0, 1, 0, 1},
      {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
      {1, 1, 1, 0, 1, 0, 0, 1, 0, 1},
  };

  public Board() throws IOException {
    BufferedImage image = ImageIO.read(new File("img/floor.png"));
    BufferedImage wallImage = ImageIO.read(new File("img/wall.png"));
    BufferedImage heroImg = ImageIO.read(new File("img/hero-down.png"));
    floorTile = image;
    hero = heroImg;
    wallTile = wallImage;
    // set the size of your draw board
    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {

        int mod_x = WIDTH/10 * x;
        int mod_y = HEIGHT/10 * y;

        switch (mapArray[y][x]) {
          case 0:
            g.drawImage(wallTile, mod_x, mod_y, this);
            drawCharacters(g);
            break;
          case 1:
            System.out.println("1"+mapArray[x][y]);
            g.drawImage(floorTile, mod_x, mod_y, this);
            drawCharacters(g);
            break;
        }
      }
    }
  }

  public void drawCharacters(Graphics g) {
    g.drawImage(hero, Hero.y, Hero.x, this);
  }

  public static void main(String[] args) throws IOException {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
  }

  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      testBoxY -= 100;
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      testBoxY += 100;
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}