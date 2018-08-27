package Character;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Skeleton extends Creature {
  public BufferedImage skelly = ImageIO.read(new File("img/skeleton.png"));

  public Skeleton(int x, int y) throws IOException {
    super(x, y);
  }

  public BufferedImage currentImage() {
    return skelly;
  }

}
