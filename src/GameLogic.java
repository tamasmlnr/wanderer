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

  public int getX(Creature creature) {
    int[] temp=characters.get(creature);
    return temp[0];
  }


  public int getY(Creature creature) {
    int[] temp=characters.get(creature);
    return temp[1];
  }
//
//  public void drawAllCreatures() {
//    for (Creature crea : characters.keySet())
//    Map.drawCreatre(crea, crea.getX, y);
//  }

}
