package GamePackage;

import Tile.*;
import Character.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JComponent implements KeyListener {
  public static final int WIDTH = 720;
  public static final int HEIGHT = 720;
  GameLogic gameLogic;

  public Board() throws IOException {
    gameLogic = new GameLogic();
    gameLogic.addHero();
    gameLogic.addSkeletons(1);
    gameLogic.addBoss(1);
    setPreferredSize(new Dimension(720, 750));
    setVisible(true);
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
            + "     Defend point: " + gameLogic.getHero().getDp()
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
          try {
            g.drawImage(new EmptyTile().getImage(), mod_x, mod_y, this);
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          try {
            g.drawImage(new NotEmptyTile().getImage(), mod_x, mod_y, this);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    try {
      drawCharacters(g);
    } catch (IOException e) {
      e.printStackTrace();
    }
    drawStats(g);
  }

  //
  public void drawCharacters(Graphics g) throws IOException {
    g.drawImage(gameLogic.getHero().currentImage(), gameLogic.getHero().getX(), gameLogic.getHero().getY(), this);
    ArrayList<Creature> creaList = gameLogic.getCreatures();
    for (Creature creature : creaList) {
      g.drawImage(creature.currentImage(), creature.getX(), creature.getY(), this);
      Font myFont = new Font("Garamond", 0, 17);
      g.setFont(myFont);
      g.setColor(Color.white);
      g.drawString(creature.currentHealth+"",creature.getX()+32, creature.getY());
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
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      gameLogic.getHero().moveUp();
      gameLogic.randomMoveCreatures();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      gameLogic.getHero().moveDown();
      gameLogic.randomMoveCreatures();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      gameLogic.getHero().moveLeft();
      gameLogic.randomMoveCreatures();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      gameLogic.getHero().moveRight();
    }
     if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      if(gameLogic.getEnemyOnTile()!=null)
      gameLogic.getHero().Strike(gameLogic.getEnemyOnTile());
    }

    repaint();
  }
}