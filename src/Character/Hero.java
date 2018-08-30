package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Hero extends Creature {
  public BufferedImage heroImgDown;

  {
    try {
      heroImgDown = ImageIO.read(new File("img/hero-down.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BufferedImage heroImgUp;

  {
    try {
      heroImgUp = ImageIO.read(new File("img/hero-up.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BufferedImage heroImgLeft;

  {
    try {
      heroImgLeft = ImageIO.read(new File("img/hero-left.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BufferedImage heroImgRight;

  {
    try {
      heroImgRight = ImageIO.read(new File("img/hero-right.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BufferedImage heroDead;

  {
    try {
      heroDead = ImageIO.read(new File("img/hero-dead.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean alive;
//  public static int stepCount = 0;

  public Hero() {
    super(0, 0);
    level = 1;
    maxHealth = 20 + 3 * GameLogic.d6();
    dp = 2 * GameLogic.d6();
    sp = 2 + GameLogic.d6();
    this.currentHealth = maxHealth;
    alive = true;
  }

  public void moveRight() {
    if (alive)
      super.moveRight();
  }

  public void moveLeft() {
    if (alive) super.moveLeft();
  }

  public void moveUp() {
    if (alive) super.moveUp();
  }

  public void moveDown() {
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
    if (0.9<x) currentHealth=maxHealth;
    if (currentHealth>maxHealth) currentHealth=maxHealth;
  }
}

