package GamePackage;

import Tile.*;
import Character.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JComponent implements KeyListener {
  public static final int WIDTH = 720;
  public static final int HEIGHT = 720;
  public static int updateTime = 1500;
  GameLogic gameLogic;
  public BufferedImage gameOver;

  {
    try {
      gameOver = ImageIO.read(new File("img/gameover.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Board() {
    gameLogic = new GameLogic();
    setPreferredSize(new Dimension(720, 750));
    setVisible(true);
    randomMoveEveryX();
  }

  public void drawStats(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 720, 720, 100);
    g.setColor(Color.white);
    Font myFont = new Font("Garamond", 0, 17);
    g.setFont(myFont);
    g.drawString("Level: " + gameLogic.getHero().getLevel()
            + "     Health: " + gameLogic.getHero().getCurrentHealth()
            + "/" + gameLogic.getHero().getMaxHealth()
            + "     Defend point: " + Math.round(gameLogic.getHero().getDp())
            + "     Strike point: " + gameLogic.getHero().getSp()
        , +20, 738);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {
        int mod_x = WIDTH / 10 * x;
        int mod_y = HEIGHT / 10 * y;
        if (GameLogic.mapArray[y][x] == 0) {
          g.drawImage(new EmptyTile().getImage(), mod_x, mod_y, this);
        } else {
          g.drawImage(new NotEmptyTile().getImage(), mod_x, mod_y, this);
        }
      }
    }
    drawCharacters(g);
    drawStats(g);
    if (!gameLogic.getHero().alive) g.drawImage(gameOver, 250, 250, this);

  }

  //
  public void drawCharacters(Graphics g) {
    if (gameLogic.getCreatures().isEmpty()) gameLogic.newLevel();

    g.drawImage(gameLogic.getHero().currentImage(), gameLogic.getHero().getX(), gameLogic.getHero().getY(), this);
    ArrayList<Creature> creaList = gameLogic.getCreatures();
    for (Creature creature : creaList) {
      g.drawImage(creature.currentImage(), creature.getX(), creature.getY(), this);
      Font myFont = new Font("Garamond", 0, 12);
      g.setFont(myFont);
      g.setColor(Color.white);
      g.drawString("HP " + creature.currentHealth + "" + " DP " + creature.dp + " SP " + creature.sp, creature.getX() - 20, creature.getY());
    }

  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      if (gameLogic.getEnemyOnTile() != null) {
        gameLogic.battle(gameLogic.getHero(), gameLogic.getEnemyOnTile());

      }
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      gameLogic.getHero().moveUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      gameLogic.getHero().moveDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      gameLogic.getHero().moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      gameLogic.getHero().moveRight();
    }
    repaint();
  }

  public void randomMoveEveryX() {
    java.util.Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {
        gameLogic.randomMoveCreatures();
        repaint();
      }
    }, 0, 1000);
  }
}