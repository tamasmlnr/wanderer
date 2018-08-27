import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero extends Creature {
  public BufferedImage heroImgDown = ImageIO.read(new File("img/hero-down.png"));
  public BufferedImage heroImgUp = ImageIO.read(new File("img/hero-up.png"));
  public BufferedImage heroImgLeft = ImageIO.read(new File("img/hero-left.png"));
  public BufferedImage heroImgRight = ImageIO.read(new File("img/hero-right.png"));


  public Hero() throws IOException {
    super(0, 0);
  }

  public BufferedImage currentImage() {
    if (lastMovement == "left") return heroImgLeft;
    if (lastMovement == "right") return heroImgRight;
    if (lastMovement == "up") return heroImgUp;
    return heroImgDown;
  }


}
