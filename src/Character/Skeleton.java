package Character;

import GamePackage.GameLogic;
import GamePackage.PositionedImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Skeleton extends Creature {

  public BufferedImage skelly;

  {
    try {
      skelly = ImageIO.read(new File("img/skeleton.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Skeleton(int x, int y, int level) {
    super(x, y);
    this.level=1;
    maxHealth=2*level*GameLogic.d6();
    currentHealth=maxHealth;
    dp=(level*0.5)*GameLogic.d6();
    sp=level*GameLogic.d6();
  }

  public BufferedImage currentImage() {
    return skelly;
  }

}
