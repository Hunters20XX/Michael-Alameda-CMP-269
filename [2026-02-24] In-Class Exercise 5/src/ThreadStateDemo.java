
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(2000); // Move to TIMED_WAITING state
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 1. NEW: Created but not yet started
        System.out.println("State after creation: " + worker.getState());

        worker.start();
        // 2. RUNNABLE: After start() is called
        System.out.println("State after start(): " + worker.getState());

        // 3. TIMED_WAITING: Wait a bit to ensure the worker has hit the sleep() call
        Thread.sleep(500);
        System.out.println("State while sleeping: " + worker.getState());

        // 4. TERMINATED: Wait for the worker to finish completely
        worker.join();
        System.out.println("State after completion: " + worker.getState());
    }
}