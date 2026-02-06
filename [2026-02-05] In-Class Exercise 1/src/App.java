import java.util.ArrayList;

/**
 * Main Application Class: App
 * Demonstrates the Lehman Campus Payment System logic and polymorphism.
 */
public class App {
    public static void main(String[] args) {
        // 1. Create an ArrayList of the interface type 'Payable'
        // This allows us to store any object that implements the Payable contract.
        ArrayList<Payable> paymentQueue = new ArrayList<>();

        // 2. Add one CreditCard object and one MealPlan object to the list.
        // CreditCard: $10.0 balance, $500.0 credit limit
        // MealPlan: $75.0 balance
        paymentQueue.add(new CreditCard("John Lehman", 10.0, 500.0));
        paymentQueue.add(new MealPlan("Jane Student", 75.0));

        System.out.println("--- Starting Payment Processing Queue ---");

        // 3. Polymorphism in Action:
        // Loop through the list and call processPayment(50.0) on every item.
        // Even though the objects are different types, they both respond to processPayment.
        for (Payable p : paymentQueue) {
            p.processPayment(50.0);
            
            // Printing status to show the result of the transaction
            System.out.println(p.getPaymentStatus());
            System.out.println("---------------------------------------");
        }

        // 4. Print the final totalTransactions using the class name.
        // Since totalTransactions is static, we access it via the abstract parent class name.
        System.out.println("FINAL SYSTEM REPORT");
        System.out.println("Total Global Transactions: " + PaymentMethod.totalTransactions);
    }
}