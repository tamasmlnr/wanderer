package GamePackage;

import java.io.IOException;
import java.util.*;

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

//  public void randomMoveEveryX() {
//    Timer t = new Timer();
//    t.schedule(new TimerTask() {
//      @Override
//      public void run() {
//        try {
//          randomMoveCreatures();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }, 0, 1000);
//  }

  public void generateRandomMap() {
    for (int i = 0; i < GameLogic.mapArray.length; i++) {
      for (int j = 0; j < GameLogic.mapArray[0].length; j++) {
        mapArray[i][j] = 0;
      }
    }
    Random random = new Random();
    int x = 0;
    int y = 0;
    GameLogic.mapArray[x][y] = 1;
    for (int i = 0; i < 101; i++) {
      int randomPick = random.nextInt(8);
      if (randomPick == 1&&y<9) {
        int r= random.nextInt(8)+1;
        while (y<r+1) {
        y ++;
        GameLogic.mapArray[x][y] = 1;
      }}
      if (randomPick == 2&&0<y) {
        int r= random.nextInt(y);
        while (y>r) {
          y --;
          GameLogic.mapArray[x][y] = 1;
        }}
      if (randomPick == 3&&x<9) {
      int r= random.nextInt(8)+1;
        while (x<r+1) {
          x ++;
          GameLogic.mapArray[x][y] = 1;
        }}
      if (randomPick == 4&&0<x) {
        int r= random.nextInt(x);
        while (x>r) {
          x --;
          GameLogic.mapArray[x][y] = 1;
        }}
        

  }


  public static int d6() {
    Random rn = new Random();
    return rn.nextInt(5) + 1;
  }

  public void addHero() throws IOException {
    hero = new Hero();
  }

//  public int[] generateRandomSpawn() {
//    Random rn = new Random();
//    int[] co = new int[2];
//    while (true) {
//      int x = rn.nextInt(9) + 1;
//      int y = rn.nextInt(9) + 1;
//      co[0] = x * 72;
//      co[1] = y * 72;
//      if (GameLogic.isPassable(co)) {
//        return co;
//      } else generateRandomSpawn();
//    }
//  }

  public int[] generateRandomSpawn() {
    int[] co = new int[2];
    while (true) {
      Random rn = new Random();
      int x = rn.nextInt(9) + 1;
      int y = rn.nextInt(9) + 1;
      co[0] = x * 72;
      co[1] = y * 72;
      if (isPassable(co)) break;
    }
    return co;
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

  public void randomMoveCreatures() throws IOException {
    ArrayList<Creature> toRemove = new ArrayList<Creature>();
    for (Creature creature : creatures) {
      creature.randomMove();
      if (creature.x == hero.x && creature.y == hero.y) toRemove.add(battle(creature, getHero()));
    }
    if (!toRemove.contains(null)) {
      for (Creature crea : toRemove) {
        creatureDeath(crea);
      }
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
//  public Creature battle(Creature attacker, Creature enemy) throws IOException {
//    if (attacker.equals(getHero())) {
//      while (true) {
//        getHero().strike(enemy);
//        if (enemy.getCurrentHealth() <= 0) {
//          getHero().levelUp();
//          creatureDeath(enemy);
//          return attacker;
//        }
//        enemy.strike(getHero());
//        if (getHero().currentHealth <= 0) {
//          getHero().alive = false;
//          break;
//        }
//      }
//    } else while (true) {
//      attacker.strike(getHero());
//      if (getHero().currentHealth <= 0) {
//        getHero().alive = false;
//        break;
//      }
//      getHero().strike(attacker);
//      if (attacker.getCurrentHealth() <= 0) {
//        getHero().levelUp();
//        return attacker;
//      }
//    }
//
//    return null;
//  }

  public Creature battle(Creature attacker, Creature enemy) throws IOException {
    System.out.println("Attacker: " + attacker);
    if (attacker.equals(getHero())) {
      while (true) {
        getHero().strike(enemy);
        if (enemy.getCurrentHealth() <= 0) {
          getHero().levelUp();
          creatureDeath(enemy);
          return enemy;
        }
        enemy.strike(getHero());
        if (getHero().currentHealth <= 0) {
          getHero().alive = false;
          break;
        }
      }
    } else while (true) {
      attacker.strike(getHero());
      if (getHero().currentHealth <= 0) {
        getHero().alive = false;
        break;
      }
      getHero().strike(attacker);
      if (attacker.getCurrentHealth() <= 0) {
        getHero().levelUp();
        return attacker;
      }
    }

    return null;
  }


  public void creatureDeath(Creature creature) throws IOException {
    creatures.remove(creature);
    if (creature.hasKey) newLevel();
  }

  public void newLevel() throws IOException {
    creatures.clear();
    currentLevel++;
    if (currentLevel > 1) generateRandomMap();
    getHero().x = 0;
    getHero().y = 0;
    addSkeletons(currentLevel);
    addBoss(currentLevel);
    creatures.get(new Random().nextInt(creatures.size())).hasKey = true;
    getHero().getLevelBonus();
    if (Board.updateTime > 300) Board.updateTime -= 100;
  }
}




