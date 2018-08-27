package Character;

import GamePackage.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss extends Creature {
  public BufferedImage bossImg = ImageIO.read(new File("img/boss.png"));

  public Boss(int x, int y, int level) throws IOException {
    super(x, y);
    super.level=level;
    super.maxHealth=2*level* GameLogic.d6()+GameLogic.d6();
    super.dp=(double)(level / 2 )* GameLogic.d6()+GameLogic.d6()/2;
    super.sp=level*GameLogic.d6()+level;
  }

  public BufferedImage currentImage() {
    return bossImg;
  }
}
