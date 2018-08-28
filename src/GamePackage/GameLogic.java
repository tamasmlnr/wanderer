package GamePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Character.*;

public class GameLogic {
  public ArrayList<Creature> creatures;
  Hero hero;
  public int currentLevel=0;

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
//      if (crea.currentPos().equals(getHero().currentPos())) {
      if ((crea.currentPos()[0] - getHero().currentPos()[0]) == 0 && (crea.currentPos()[1] - getHero().currentPos()[1]) == 0) {
        return crea;
      }
    }
    return null;
  }

  public void battle(Creature enemy) {
    while (true) {
      getHero().strike(enemy);
      enemy.strike(getHero());
      if (getHero().currentHealth<0) gameOver();
      if (enemy.getCurrentHealth()<0) {creatureDeath(enemy);
      getHero().levelUp();
      break;}
    }
  }

  public boolean gameOver() {
    if (getHero().currentHealth<0)
    {hero.alive=false;
      return true;}
    return false;
  }

    public void creatureDeath(Creature creature) {
    creatures.remove(creature);
    }

    public void newLevel() throws IOException {
    currentLevel++;
      addSkeletons(currentLevel);
      addBoss(currentLevel);
    }
  }




