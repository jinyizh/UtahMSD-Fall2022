import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream("output.txt"));
    PrintWriter pw = new PrintWriter("delay.txt");
  }
}