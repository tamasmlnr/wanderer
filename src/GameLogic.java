import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameLogic {
  public ArrayList<Creature> characters;
  Hero hero;
  public boolean passable;

  public static int[][] mapArray = new int[][]{
      {1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
      {1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
      {1, 0, 0, 0, 1, 0, 1, 0, 0, 1},
      {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
      {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
      {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
      {1, 1, 1, 1, 1, 0, 0, 1, 0, 1},
      {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
      {1, 1, 1, 0, 1, 0, 0, 1, 1, 1},
  };

  public GameLogic() throws IOException {
    ArrayList<Creature> characters = new ArrayList<>();
    hero = new Hero();
  }

  public static boolean isPassable(int[] tile) {
    int x=tile[0]/72;
    int y=tile[1]/72;

    if (mapArray[y][x]==0)
    return false;
    return true;
  }

  public void addCreature(Creature creature, int x, int y) {
    characters.add(creature);
  }

  public Hero getHero() {
    return hero;
  }

  public int getX(Creature creature) {
    return creature.getX();
  }

  public int getY(Creature creature) {
    return creature.getY();
  }
}


