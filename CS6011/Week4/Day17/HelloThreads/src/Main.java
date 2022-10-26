public class Main {
    public static void sayHello() throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            // counts from 1 to 100
            Thread thread = new Thread(() -> {
                for (int j = 1; j <= 100; j++) {
                    if (j % 10 == 0) {
                        System.out.println("hello number " + j + " from thread number " +
                                Thread.currentThread().threadId() + "; ");
                    } else {
                        System.out.print("hello number " + j + " from thread number " +
                                Thread.currentThread().threadId() + "; ");
                    }
                }
            });
            threads[i] = thread;
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static int answer; // all threads add nums to it

    public static void badSum() throws InterruptedException {
        answer = 0;
        int maxValue = 40000;
        Thread[] threads = new Thread[100];
        int numThreads = threads.length;
        for (int i = 0; i < numThreads; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = finalI * maxValue / numThreads; j < Math.min((finalI + 1) * maxValue/ numThreads, maxValue); j++) {
                        answer += j;
                    }
                }
            });
            threads[i] = thread;
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("coumputed answer: " + answer);
        System.out.println("correct answer: " + (maxValue * (maxValue - 1)) / 2);
    }

    public static void main(String[] args) throws InterruptedException {
        sayHello();
        badSum();
    }
}