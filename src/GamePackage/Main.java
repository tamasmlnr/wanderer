package GamePackage;

import javax.swing.*;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Wanderer");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
  }
}