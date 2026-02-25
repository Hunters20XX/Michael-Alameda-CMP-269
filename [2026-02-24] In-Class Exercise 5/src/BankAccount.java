
public class BankAccount {
    // Shared resource: The account balance
    private int balance = 1000;

    /**
     * The 'synchronized' keyword ensures that only one thread can execute 
     * this method at a time for a specific BankAccount instance.
     */
    public synchronized void withdraw(int amount) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " is attempting to withdraw USD " + amount);
        System.out.println("Current balance before check: USD " + balance);

        if (balance >= amount) {
            System.out.println(name + " passed the balance check.");
            
            // Artificial delay to simulate real-world processing 
            // and to prove that 'synchronized' is working.
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            balance -= amount;
            System.out.println(name + " successfully withdrew. New Balance: USD " + balance);
        } else {
            System.out.println("Transaction Denied for " + name + ": Insufficient funds. Balance: USD " + balance);
        }
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        // Create one shared account object
        BankAccount sharedAccount = new BankAccount();

        // Define the task for the threads
        Runnable task = () -> {
            sharedAccount.withdraw(700);
        };

        // Create two threads representing the Husband and Wife
        Thread husband = new Thread(task, "Husband");
        Thread wife = new Thread(task, "Wife");

        // Start both threads simultaneously
        husband.start();
        wife.start();
    }
}