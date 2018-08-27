public class Creature {
  public int x;
  public int y;
  // private int hp;
// private int dmg;
  public String lastMovement;

  public Creature(int x, int y) {
    this.x = x;
    this.y = y;
    this.lastMovement = "down";
//   this.hp=0;
//   this.dmg=0;
  }

  public int[] currentPos() {
    int[] pos = new int[2];
    pos[0] = x;
    pos[1] = y;
    return pos;
  }


  public int getX() {
    return this.x;
  }

  public void moveRight() {
    this.lastMovement = "right";
    int[] targetPos=currentPos();
    targetPos[0]+=72;
    if ((x < 648)&&GameLogic.isPassable(targetPos))
      x += 72;
  }

  public void moveLeft() {
    this.lastMovement = "left";
    int[] targetPos=currentPos();
    targetPos[0]-=72;
    if ((x > 0)&&GameLogic.isPassable(targetPos))  x -= 72;
  }

  public void moveDown() {
    this.lastMovement = "down";
    int[] targetPos=currentPos();
    targetPos[1]+=72;
    if ((y < 648)&&GameLogic.isPassable(targetPos)) y += 72;
  }

  public void moveUp() {
    this.lastMovement = "up";
    int[] targetPos=currentPos();
    targetPos[1]-=72;
    if ((y > 0)&&GameLogic.isPassable(targetPos))
      y -= 72;
  }

  public int getY() {
    return this.y;
  }

}
