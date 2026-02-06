/**
 * Concrete Subclass: CreditCard
 * Extends PaymentMethod to include credit-specific functionality.
 */
public class CreditCard extends PaymentMethod {
    
    // Additional field specific to Credit Cards
    private double creditLimit;

    /**
     * Constructor for CreditCard.
     * Uses the 'super' keyword to pass common data to the PaymentMethod constructor.
     */
    public CreditCard(String accountHolder, double balance, double creditLimit) {
        // Requirement: Call the parent constructor as the first line
        super(accountHolder, balance);
        this.creditLimit = creditLimit;
    }

    /**
     * Requirement: Implement specific validation for Credit Cards.
     */
    @Override
    public void validateAccount() {
        System.out.println("Validating credit standing for " + accountHolder + "...");
    }

    /**
     * Requirement: Override processPayment with custom logic.
     * Checks if the amount is within the combined range of balance and credit limit.
     */
    @Override
    public void processPayment(double amount) {
        // Requirement: Check if amount exceeds balance + creditLimit
        if (amount > (balance + creditLimit)) {
            System.out.println("Transaction Declined. Amount exceeds available balance and credit limit.");
        } else {
            // Subtract the amount from the balance
            balance -= amount;
            
            // Requirement: Increment the static counter from the parent class
            totalTransactions++;
            
            System.out.println("Payment of USD " + amount + " processed via Credit Card.");
        }
    }
}