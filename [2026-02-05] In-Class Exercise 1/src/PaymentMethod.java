/**
 * Abstract Class: PaymentMethod
 * This class serves as the base for all specific payment types.
 * It implements the Payable interface but remains abstract as it 
 * cannot represent a complete payment method on its own.
 */
public abstract class PaymentMethod implements Payable {
    
    // Protected fields allow access within subclasses
    protected String accountHolder;
    protected double balance;
    
    // Static member shared across all instances of any subclass
    protected static int totalTransactions = 0;

    /**
     * Constructor to initialize common fields for all payment methods.
     * @param accountHolder The name of the person owning the account.
     * @param balance The initial starting funds.
     */
    public PaymentMethod(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /**
     * Abstract Method: Must be implemented by concrete subclasses (CreditCard, MealPlan).
     * Used to define specific validation rules for different account types.
     */
    public abstract void validateAccount();

    /**
     * Implementation of the Payable interface method.
     * Provides a basic status string common to all payment methods.
     */
    @Override
    public String getPaymentStatus() {
        return "Account: " + accountHolder + " | Balance: USD " + balance;
    }
}