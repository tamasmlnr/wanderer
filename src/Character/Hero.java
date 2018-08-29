package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Hero extends Creature {
  public BufferedImage heroImgDown = ImageIO.read(new File("img/hero-down.png"));
  public BufferedImage heroImgUp = ImageIO.read(new File("img/hero-up.png"));
  public BufferedImage heroImgLeft = ImageIO.read(new File("img/hero-left.png"));
  public BufferedImage heroImgRight = ImageIO.read(new File("img/hero-right.png"));
  public BufferedImage heroDead = ImageIO.read(new File("img/hero-dead.png"));
  public boolean alive;
//  public static int stepCount = 0;

  public Hero() throws IOException {
    super(0, 0);
    level = 1;
    maxHealth = 20 + 3 * GameLogic.d6();
    dp = 2 * GameLogic.d6();
    sp = 2 + GameLogic.d6();
    this.currentHealth = maxHealth;
    alive = true;
  }

  public void moveRight() {
//    stepCount++;
    if (alive)
      super.moveRight();
  }

  public void moveLeft() {
//    stepCount++;
    if (alive) super.moveLeft();
  }

  public void moveUp() {
//    stepCount++;
    if (alive) super.moveUp();
  }

  public void moveDown() {
//    stepCount++;
    if (alive) super.moveDown();
  }

  public int getLevel() {
    return this.level;
  }

  public BufferedImage currentImage() {
    if (!alive) return heroDead;
    if (lastMovement == "left") return heroImgLeft;
    if (lastMovement == "right") return heroImgRight;
    if (lastMovement == "up") return heroImgUp;
    return heroImgDown;

  }

  public void levelUp() {
    level++;
    maxHealth += GameLogic.d6();
    dp += GameLogic.d6()*0.6;
    sp += GameLogic.d6();
  }
  public void getLevelBonus() {
    Random random = new Random();
    double x = random.nextDouble();
    if (0.5>x) currentHealth=(int)(currentHealth*1.1);
    if (0.5<=x&&0.9>x) currentHealth=(int)(currentHealth*1.3);
    if (0.9<x) currentHealth=(int)(currentHealth=maxHealth);
    if (currentHealth>maxHealth) currentHealth=maxHealth;
  }

}

