package GamePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Character.*;

public class GameLogic {
  public ArrayList<Creature> creatures;
  Hero hero;
  public int currentLevel = 0;

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


  public GameLogic() throws IOException {
    creatures = new ArrayList<>();
    addHero();
  }

//  public void generateRandomMap() {
//    for (int i = 0; i < GameLogic.mapArray.length; i++) {
//      for (int j = 0; j < GameLogic.mapArray[0].length; j++) {
//        Random random = new Random();
//        double x = random.nextDouble();
//        if(x>0.3)
//        mapArray[i][j] = 1;
//        if(x<=0.3)
//          mapArray[i][j] = 0;
//      }
//    }
//  }

  public void generateRandomMap() {
    for (int i = 0; i < GameLogic.mapArray.length; i++) {
      for (int j = 0; j < GameLogic.mapArray[0].length; j++) {
        mapArray[i][j] = 0;
      }
    }
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Random random = new Random();
    int x = 0;
    int y = 0;
    GameLogic.mapArray[x][y] = 1;
    for (int i = 0; i < 200; i++) {
      int randomPick = random.nextInt(4);
      System.out.println(randomPick);
      if (randomPick == 0 && (x - 1) > 0) x -= 1;
      GameLogic.mapArray[x][y] = 1;
      if (randomPick == 1 && (y + 1) < 10) y += 1;
      GameLogic.mapArray[x][y] = 1;
      if (randomPick == 2 && (x + 1) < 10) x += 1;
      GameLogic.mapArray[x][y] = 1;
      if (randomPick == 3 && (y - 1) > 0) y -= 1;
      GameLogic.mapArray[x][y] = 1;
    }


  }


  public static int d6() {
    Random rn = new Random();
    return rn.nextInt(5) + 1;
  }

  public void addHero() throws IOException {
    hero = new Hero();
  }

  public int[] generateRandomSpawn() {
    Random rn = new Random();
    int[] co = new int[2];
    while (true) {
      int x = rn.nextInt(9) + 1;
      int y = rn.nextInt(9) + 1;
      co[0] = x * 72;
      co[1] = y * 72;
      if (GameLogic.isPassable(co)) {
        return co;
      } else generateRandomSpawn();
    }
  }


  public void addSkeletons(int level) throws IOException {
    int[] skelPos;
    for (int i = 0; i < 3; i++) {
      skelPos = generateRandomSpawn();
      creatures.add(new Skeleton(skelPos[0], skelPos[1], level));
    }
  }

  public void addBoss(int level) throws IOException {
    int[] bossPos = generateRandomSpawn();
    creatures.add(new Boss(bossPos[0], bossPos[1], level));
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

  public void randomMoveCreatures() {
    for (Creature creature : creatures) {
      creature.randomMove();
    }
  }

  public Hero getHero() {
    return hero;
  }


  public Creature getEnemyOnTile() {
    for (Creature crea : creatures) {
      if ((crea.currentPos()[0] - getHero().currentPos()[0]) == 0 && (crea.currentPos()[1] - getHero().currentPos()[1]) == 0) {
        return crea;
      }
    }
    return null;
  }

  public void battle(Creature enemy) throws IOException {

    while (true) {
      getHero().strike(enemy);
      enemy.strike(getHero());
      if (getHero().currentHealth <= 0) {
        getHero().alive = false;
        break;
      }
      if (enemy.getCurrentHealth() <= 0) {
        getHero().levelUp();
        creatureDeath(enemy);
        break;
      }
    }
  }


  public void creatureDeath(Creature creature) throws IOException {
    creatures.remove(creature);
    if (creature.hasKey) newLevel();
    creature = null;
  }

  public void newLevel() throws IOException {
    creatures.clear();
    if (currentLevel > 1) generateRandomMap();
    getHero().x=0;
    getHero().y=0;
    currentLevel++;
    addSkeletons(currentLevel);
    addBoss(currentLevel);
    creatures.get(new Random().nextInt(creatures.size())).hasKey = true;
    getHero().getLevelBonus();

  }
}




