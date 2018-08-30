package Tile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NotEmptyTile {
  public BufferedImage image;

  {
    try {
      image = ImageIO.read(new File("img/floor.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public NotEmptyTile() {
  }

  public BufferedImage getImage() {
    return image;
  }


}

