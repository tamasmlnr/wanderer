package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Creature {
  public int x;
  public int y;
  public String lastMovement;
  private int level = 1;
  public int maxHealth;
  public int currentHealth;
  public int defense;
  public int strike;

  public int getDefense() {
    return defense;
  }

  public int getStrike() {
    return strike;
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

  public int getY() {
    return this.y;
  }

}
