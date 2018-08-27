package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Creature {
  public int x;
  public int y;
  public String lastMovement;
  public int level=0;
  public int maxHealth=0;
  public int currentHealth=0;
  public double dp=0;
  public int sp=0;

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

  public void randomMove() {
    Random rn = new Random();
    int x = rn.nextInt(3);
    switch (x) {
      case 0:  moveUp();
        break;
      case 1:  moveDown();
        break;
      case 2:  moveRight();
        break;
      case 3:  moveLeft();
        break;
  }
  }

  public void Strike(Creature enemy){
    //
    if(this.currentPos()==enemy.currentPos()&&(2*GameLogic.d6()>enemy.dp)){
    enemy.currentHealth-=sp-enemy.dp;
    }
  }

}
