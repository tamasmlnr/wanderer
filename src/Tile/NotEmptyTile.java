package Tile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NotEmptyTile {
  public BufferedImage image = ImageIO.read(new File("img/floor.png"));
  public NotEmptyTile() throws IOException {
  }

  public BufferedImage getImage() {
    return image;
  }


}

