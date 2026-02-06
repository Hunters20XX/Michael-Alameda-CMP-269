/**
 * Interface: Powerable
 * Defines the standard contract for any device that can be powered on or off.
 */
public interface Powerable {

    /**
     * Activates the device and updates its power state.
     */
    void turnOn();

    /**
     * Deactivates the device and updates its power state.
     */
    void turnOff();
}