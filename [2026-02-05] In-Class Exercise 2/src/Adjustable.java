/**
 * Interface: Adjustable
 * Defines the contract for devices that allow numerical level adjustments, 
 * such as intensity, volume, or temperature settings.
 */
public interface Adjustable {

    /**
     * Sets the device to a specific level.
     * @param level An integer representing the desired setting (e.g., 0-100 for brightness).
     */
    void setLevel(int level);
}