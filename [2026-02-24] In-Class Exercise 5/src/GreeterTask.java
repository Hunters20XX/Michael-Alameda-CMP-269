
public class GreeterTask implements Runnable {

    /**
     * The run() method contains the code that will be executed
     * by the threads created in the main method.
     */
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            // Get the name of the thread currently executing this task
            String threadName = Thread.currentThread().getName();
            
            System.out.println("Iteration " + i + ": Hello from [" + threadName + "]");
            
            try {
                // Pause execution for 500 milliseconds
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
                // Restore the interrupted status
                Thread.currentThread().interrupt();
                return; 
            }
        }
    }

    public static void main(String[] args) {
        // Instantiate the task
        GreeterTask task = new GreeterTask();

        // Create two Thread objects, passing the task and a custom name
        Thread t1 = new Thread(task, "Lehman-Thread-1");
        Thread t2 = new Thread(task, "Lehman-Thread-2");

        // start() invokes the run() method in a new execution thread
        t1.start();
        t2.start();
        
        System.out.println("Main thread has finished starting the children.");
    }
}