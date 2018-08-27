package Character;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss extends Creature {
  public BufferedImage bossImg = ImageIO.read(new File("img/boss.png"));

  public Boss(int x, int y) throws IOException {
    super(x, y);
  }

  public BufferedImage currentImage() {
    return bossImg;
  }

}
