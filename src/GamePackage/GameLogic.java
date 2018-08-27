package GamePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Character.*;

public class GameLogic {
  public ArrayList<Creature> creatures;
  Hero hero;
  public boolean passable;

  public static int[][] mapArray = new int[][]
      {{1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
          {1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
          {1, 0, 0, 0, 1, 0, 1, 0, 0, 1},
          {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
          {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
          {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
          {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
          {1, 1, 1, 1, 1, 0, 0, 1, 0, 1},
          {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
          {1, 1, 1, 0, 1, 0, 0, 1, 1, 1},};


  public GameLogic() {
    creatures = new ArrayList<>();
  }

  public void addHero() throws IOException {
    hero = new Hero();
    creatures.add(hero);
  }

  public int[] generateRandomPosition() {
    Random rn = new Random();
    int[] co = new int[2];
    while (true) {
      int x = rn.nextInt(9) + 1;
      int y = rn.nextInt(9) + 1;
      co[0] = x * 72;
      co[1] = y * 72;
      if (GameLogic.isPassable(co)) {
        return co;
      } else generateRandomPosition();
    }
  }


  public void addSkeletons() throws IOException {
    int[] skelPos;
    for (int i = 0; i < 3; i++) {
      skelPos = generateRandomPosition();
      creatures.add(new Skeleton(skelPos[0], skelPos[1]));
    }
  }

  public void addBoss() throws IOException {
    int[] bossPos = generateRandomPosition();
    creatures.add(new Boss(bossPos[0], bossPos[1]));
  }

  public ArrayList<Creature> getCreatures() {
    return creatures;
  }

  public static boolean isPassable(int[] tile) {
    int x = tile[0] / 72;
    int y = tile[1] / 72;

    if (mapArray[y][x] == 0) return false;
    return true;
  }

  public void addCreature(Creature creature, int x, int y) {
    creatures.add(creature);
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


