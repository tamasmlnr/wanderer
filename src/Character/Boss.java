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
    super.level = level;
    super.maxHealth = 2 * level * GameLogic.d6() + GameLogic.d6();
    this.currentHealth = maxHealth;
    super.dp = (level *0.5) * GameLogic.d6() + GameLogic.d6()*0.5;
    super.sp = level * GameLogic.d6() + level;
  }

  public BufferedImage currentImage() {
    return bossImg;
  }
}
