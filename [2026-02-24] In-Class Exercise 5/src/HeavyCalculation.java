
public class HeavyCalculation {
    private static long result = 0;

    public static void main(String[] args) {
        Thread calcThread = new Thread(() -> {
            System.out.println("Calculation started...");
            long sum = 0;
            for (int i = 0; i < 1_000_000_000; i++) {
                sum += i;
            }
            result = sum;
        });

        calcThread.start();

        try {
            // Block the main thread until calcThread is finished
            calcThread.join();
            System.out.println("Calculation Finished: " + result);
        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted.");
        }
    }
}