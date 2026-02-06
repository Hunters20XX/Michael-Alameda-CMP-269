/**
 * Interface: Payable
 * This interface defines the essential contract for any payment method 
 * implemented within the Lehman Campus system.
 */
public interface Payable {

    /**
     * Processes a financial transaction for a specified amount.
     * @param amount The total value to be charged.
     */
    void processPayment(double amount);

    /**
     * Retrieves the current state or details of the payment method.
     * @return A string representing the account status.
     */
    String getPaymentStatus();
}