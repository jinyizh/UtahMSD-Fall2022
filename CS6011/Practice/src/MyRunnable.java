public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // get the socket from the server
        // read the headers
        // send the
        int count = (int) (Thread.currentThread().threadId());
        while (count < 100) {
            // thread starts
            System.out.println("Hello from this thread: " + Thread.currentThread().threadId() + ": " + count++);
        }
        // thread ends.. <- and garbage collector can get rid of
    }
}
