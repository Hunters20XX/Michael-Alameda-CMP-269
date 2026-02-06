/**
 * Concrete Subclass: MealPlan
 * Extends PaymentMethod to implement student dining account logic.
 */
public class MealPlan extends PaymentMethod {

    /**
     * Constructor for MealPlan.
     * Passes the account holder name and initial balance up to the parent class.
     */
    public MealPlan(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    /**
     * Requirement: Override validateAccount.
     * Logic: Meal plan balances cannot be negative.
     */
    @Override
    public void validateAccount() {
        if (balance < 0) {
            System.out.println("Validation Error: Meal Plan balance for " + accountHolder + " is negative!");
        } else {
            System.out.println("Meal Plan validation successful for " + accountHolder + ".");
        }
    }

    /**
     * Requirement: Implement processPayment specifically for meal plan logic.
     * Logic: Only allows payments if the balance is sufficient (no credit allowed).
     */
    @Override
    public void processPayment(double amount) {
        // Check if the student has enough funds in their specific meal plan account
        if (amount > balance) {
            System.out.println("Transaction Declined for " + accountHolder + ": Insufficient Meal Plan Funds.");
        } else {
            // Subtract the amount from the current balance
            balance -= amount;
            
            // Increment the static transaction counter shared by all PaymentMethods
            totalTransactions++;
            
            System.out.println("Meal Plan Payment of USD " + amount + " processed successfully.");
        }
    }
}