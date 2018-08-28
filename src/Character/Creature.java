package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Creature {
  public int x;
  public int y;
  public String lastMovement;
  public int level = 0;
  public int currentHealth;
  public int maxHealth;
  public double dp = 0;
  public int sp = 0;

  public double getDp() {
    return dp;
  }

  public int getSp() {
    return sp;
  }

  public Creature(int x, int y) {
    this.x = x;
    this.y = y;
    this.lastMovement = "down";
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public int getCurrentHealth() {
    return currentHealth;
  }

  public int getLevel() {
    return level;
  }

  public int[] currentPos() {
    int[] pos = new int[2];
    pos[0] = x;
    pos[1] = y;
    return pos;
  }

  public BufferedImage currentImage() throws IOException {
    BufferedImage def = ImageIO.read(new File("img/hero-right.png"));
    return def;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void moveRight() {
    this.lastMovement = "right";
    int[] targetPos = currentPos();
    targetPos[0] += 72;
    if ((x < 648) && GameLogic.isPassable(targetPos)) x += 72;
  }

  public void moveLeft() {
    this.lastMovement = "left";
    int[] targetPos = currentPos();
    targetPos[0] -= 72;
    if ((x > 0) && GameLogic.isPassable(targetPos)) x -= 72;
  }

  public void moveDown() {
    this.lastMovement = "down";
    int[] targetPos = currentPos();
    targetPos[1] += 72;
    if ((y < 648) && GameLogic.isPassable(targetPos)) y += 72;
  }

  public void moveUp() {
    this.lastMovement = "up";
    int[] targetPos = currentPos();
    targetPos[1] -= 72;
    if ((y > 0) && GameLogic.isPassable(targetPos)) y -= 72;
  }


//  public void randomMove() {
//    Random rn = new Random();
//    if (Hero.stepCount % 2 == 0) {
//      int x = rn.nextInt(3);
//      switch (x) {
//        case 0:
//          moveUp();
//          break;
//        case 1:
//          moveDown();
//          break;
//        case 2:
//          moveRight();
//          break;
//        case 3:
//          moveLeft();
//          break;
//      }
//    }
//  }

  public void randomMove() {
    int[] newPos = availableTiles();
    if (Hero.stepCount % 2 == 0) {
      x = newPos[0];
      y = newPos[1];
    }
  }

  public int[] availableTiles() {
    ArrayList<int[]> positions = new ArrayList<>();
    int[] posDown = currentPos();
    posDown[1] += 72;
    if (posDown[1] < 648 && GameLogic.isPassable(posDown)) positions.add(posDown);
    int[] posUp = currentPos();
    posUp[1] -= 72;
    if (posUp[1] > 0 && GameLogic.isPassable(posUp)) positions.add(posUp);
    int[] posLeft = currentPos();
    posLeft[0] -= 72;
    if (posLeft[0] > 0 && GameLogic.isPassable(posLeft)) positions.add(posLeft);
    int[] posRight = currentPos();
    posRight[0] += 72;
    if (posRight[0] < 648 && GameLogic.isPassable(posRight)) positions.add(posRight);
    return positions.get(new Random().nextInt(positions.size()));
  }

  public void strike(Creature enemy) {
    if (2 * GameLogic.d6() > enemy.dp) {
      if (sp - enemy.dp > 0)
        enemy.currentHealth -= sp - enemy.dp;
    }
  }


}
