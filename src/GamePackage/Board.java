package GamePackage;

import Tile.*;
import Character.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends JComponent implements KeyListener {
  public static final int WIDTH = 720;
  public static final int HEIGHT = 720;
  GameLogic gameLogic;

  public Board() throws IOException {
    gameLogic = new GameLogic();
    gameLogic.addHero();
    gameLogic.addSkeletons();
    gameLogic.addBoss();
    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
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
  }

  //
  public void drawCharacters(Graphics g) throws IOException {
//    g.drawImage(gameLogic.getHero().currentImage(), gameLogic.getHero().getX(), gameLogic.getHero().getY(), this);
    ArrayList<Creature> creaList = gameLogic.getCreatures();
    for (Creature creature : creaList) {
      g.drawImage(creature.currentImage(), creature.getX(), creature.getY(), this);
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
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      gameLogic.getHero().moveDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      gameLogic.getHero().moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      gameLogic.getHero().moveRight();
    }
    repaint();
  }


}