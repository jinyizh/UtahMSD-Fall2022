package lab03;

import java.util.ArrayList;

/**
 * Test the .size() method
 */

public class Main {
  public static void main(String[] args) {
    int count = 100000;
    ArrayList<Long> longs = new ArrayList<>();
    for (long i = 0; i < 1000000000L; i ++) {
      longs.add(i);
    }
    long lastTime = System.nanoTime();
    int currentCount = 0;
    while (currentCount < count) {
      longs.size();
      currentCount ++;
    }
    long currentTime = System.nanoTime();
    System.out.println((currentTime - lastTime) / count);
  }
}
