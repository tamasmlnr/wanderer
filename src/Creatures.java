import java.util.ArrayList;
import java.util.HashMap;

public class Creatures {
  public HashMap<Creature, int[]> characters = new HashMap<Creature, int[]>();

  public Creatures() {
  }

  public void addCreature(Creature creature, int x, int y) {
    int[] location = new int[]{x, y};
    characters.put(creature, location);
  }
}
