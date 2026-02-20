/**
 * Custom checked exception to identify students with failing averages.
 */
@SuppressWarnings("serial")
class LowGradeException extends Exception {
    public LowGradeException(String message) {
        super(message);
    }
}