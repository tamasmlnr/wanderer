package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero extends Creature {
  public BufferedImage heroImgDown = ImageIO.read(new File("img/hero-down.png"));
  public BufferedImage heroImgUp = ImageIO.read(new File("img/hero-up.png"));
  public BufferedImage heroImgLeft = ImageIO.read(new File("img/hero-left.png"));
  public BufferedImage heroImgRight = ImageIO.read(new File("img/hero-right.png"));
  public BufferedImage heroDead = ImageIO.read(new File("img/hero-dead.png"));
  public boolean alive;

  public Hero() throws IOException {
    super(0, 0);
    level=1;
    maxHealth=20+3* GameLogic.d6();
    dp=2* GameLogic.d6();
    sp=5+ GameLogic.d6();
    this.currentHealth=maxHealth;
    alive=true;
  }

  public void moveRight() {
    System.out.println(alive);
    if(alive==true) super.moveRight();
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
    int healthDiff=GameLogic.d6();
    maxHealth+=healthDiff;
    currentHealth+=healthDiff;
    dp+=GameLogic.d6();
    sp+=GameLogic.d6();
  }
}
