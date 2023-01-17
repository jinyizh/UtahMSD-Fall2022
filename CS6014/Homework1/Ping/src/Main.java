import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream("ping.txt"));
    PrintWriter pw = new PrintWriter("output.txt");
    pw.println("The total round trip delay and queueing delay for each packet is: ");
    pw.println(""); // add blank line in output
    sc.nextLine();
    ArrayList<Float> delays = new ArrayList<>(); // round (two ways)
    while (!Objects.equals(sc.nextLine(), "")) {
      String datum = sc.nextLine();
      System.out.println(datum);
      String[] data = datum.split(" ");
      String timeData = data[data.length - 2];
      System.out.println(timeData);
      String timeString = timeData.substring(timeData.lastIndexOf("=") + 1);
      float roundDelay = Float.parseFloat(timeString);
      float delay = roundDelay / 2;
      System.out.println("round delay is: " + roundDelay);
      delays.add(roundDelay);
    }
    Collections.sort(delays);
    float[] qDelay = new float[delays.size()]; // queueing delays
    float delaySum = 0;
    for (int i = 0; i < delays.size(); i++) {
      System.out.println(delays.get(i));
      pw.println("For packet " + i + ":");
      pw.println("Total round trip delay is: " + delays.get(i) + " ms");
      qDelay[i] = delays.get(i) - delays.get(0);
      pw.println("Queueing delay is: " + qDelay[i] + " ms");
      delaySum += qDelay[i];
      pw.println(""); // add blank line in output
    }
    float avgQueDelay = delaySum / delays.size();
    pw.println("The average round trip queueing delay is: " + avgQueDelay + " ms");
    pw.close();
  }
}