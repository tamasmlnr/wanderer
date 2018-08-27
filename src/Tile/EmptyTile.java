package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EmptyTile {
  public BufferedImage image = ImageIO.read(new File("img/wall.png"));

  public EmptyTile() throws IOException {
  }

  public BufferedImage getImage() {
    return image;
  }
}