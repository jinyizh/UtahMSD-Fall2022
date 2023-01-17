import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream("traceroute1.txt"));
    PrintWriter pw = new PrintWriter("output1.txt");
    while (sc.hasNextLine()) {
      String datum = sc.nextLine();
      System.out.println(datum);
      String[] data = datum.split("  ");
      try {
        int hopNum = Integer.parseInt(data[0].trim());
        System.out.println("valid, # of hop: " + hopNum);
        if (data[1].lastIndexOf("(") != -1) { // address exists
          String address = data[1].substring(data[1].lastIndexOf("(") + 1, data[1].length() - 1);
          System.out.println("address is: " + address);
          float avgDelay = 0; // in ms
          int numDelay = 0;
          for (int i = 2; i < data.length; i++) { // compute avg delay
            String delayString = data[i].substring(0, data[i].indexOf(" "));
            System.out.println("delay is: " + delayString);
            avgDelay += Float.parseFloat(delayString);
            numDelay++;
          }
          avgDelay /= numDelay;
          System.out.println("avg delay is: " + avgDelay);
          pw.println(hopNum + " " + avgDelay);
        }
      } catch (NumberFormatException e) {
        System.out.println("not valid"); // only count the first route for each hop
      }
      for (String s: data) {
        System.out.println(s);
      }
    }
    pw.close();
  }
}