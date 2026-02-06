import java.util.ArrayList;

/**
 * Interface: Payable
 * Defines the contract for any payment method within the Lehman Campus system.
 */
interface Payable {
    void processPayment(double amount);
    String getPaymentStatus();
}

/**
 * Abstract Class: PaymentMethod
 * Implements Payable and provides common fields and logic for specific payment types.
 * This class cannot be instantiated directly.
 */
abstract class PaymentMethod implements Payable {
    protected String accountHolder;
    protected double balance;
    
    // Static member to track transactions across the entire system
    protected static int totalTransactions = 0;

    public PaymentMethod(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Abstract method to be defined by specific payment types
    public abstract void validateAccount();

    @Override
    public String getPaymentStatus() {
        return "Account Holder: " + accountHolder + " | Current Balance: USD " + balance;
    }
}

/**
 * Concrete Subclass: CreditCard
 * Extends PaymentMethod and introduces a credit limit.
 */
class CreditCard extends PaymentMethod {
    private double creditLimit;

    public CreditCard(String accountHolder, double balance, double creditLimit) {
        // Use the super keyword to call the parent constructor
        super(accountHolder, balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public void validateAccount() {
        System.out.println("Validating Credit Card for " + accountHolder + "...");
    }

    @Override
    public void processPayment(double amount) {
        // Logic: Allow transaction if amount is within balance + credit limit
        if (amount > (balance + creditLimit)) {
            System.out.println("Transaction Declined for " + accountHolder + ": Insufficient Credit.");
        } else {
            balance -= amount;
            totalTransactions++;
            System.out.println("Credit Card Payment of USD " + amount + " processed for " + accountHolder);
        }
    }
}

/**
 * Concrete Subclass: MealPlan
 * Extends PaymentMethod with logic specific to campus dining.
 */
class MealPlan extends PaymentMethod {

    public MealPlan(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void validateAccount() {
        // Logic: Meal plan balances cannot be negative
        if (balance < 0) {
            System.out.println("Alert: Meal Plan balance for " + accountHolder + " is negative!");
        } else {
            System.out.println("Meal Plan for " + accountHolder + " is valid.");
        }
    }

    @Override
    public void processPayment(double amount) {
        // Logic: Only allow payments if the balance is sufficient (no credit)
        if (amount > balance) {
            System.out.println("Transaction Declined for " + accountHolder + ": Insufficient Meal Plan Funds.");
        } else {
            balance -= amount;
            totalTransactions++;
            System.out.println("Meal Plan Payment of USD " + amount + " processed for " + accountHolder);
        }
    }
}

/**
 * Main Class: LehmanCampusPaymentSystem
 * Orchestrates the payment processing using polymorphism.
 */
public class LehmanCampusPaymentSystem {
    public static void main(String[] args) {
        // Create an ArrayList of the interface type (Polymorphism)
        ArrayList<Payable> paymentQueue = new ArrayList<>();

        // Add concrete objects to the list
        paymentQueue.add(new CreditCard("John Doe", 100.0, 500.0));
        paymentQueue.add(new MealPlan("Jane Smith", 75.0));

        System.out.println("--- Processing Lehman Campus Payments ---");

        // Polymorphism in Action: Loop through the list and process payments
        for (Payable payment : paymentQueue) {
            payment.processPayment(50.0);
            System.out.println(payment.getPaymentStatus());
            System.out.println("---------------------------------------");
        }

        // Print the final totalTransactions using the Class name (Static reference)
        System.out.println("System Report: Total Successful Transactions processed: " + PaymentMethod.totalTransactions);
    }
}