import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Runnable runnable = new MyRunnable();
            Thread myThread = new Thread(runnable);
            myThread.start();
        }
    }
}