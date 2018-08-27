package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Skeleton extends Creature {
  public BufferedImage skelly = ImageIO.read(new File("img/skeleton.png"));

  public Skeleton(int x, int y, int level) throws IOException {
    super(x, y);
    super.level=level;
    super.maxHealth=2*level*GameLogic.d6();
    super.dp=(double)(level / 2 )* GameLogic.d6();
    super.sp=level*GameLogic.d6();
  }

  public BufferedImage currentImage() {
    return skelly;
  }

}
