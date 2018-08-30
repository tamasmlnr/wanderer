package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EmptyTile {
  public BufferedImage image;

  {
    try {
      image = ImageIO.read(new File("img/wall.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public EmptyTile()  {
  }

  public BufferedImage getImage() {
    return image;
  }
}