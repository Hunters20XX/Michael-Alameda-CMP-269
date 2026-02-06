/**
 * Concrete Subclass: SmartLight
 * Inherits from SmartDevice and implements the Adjustable interface.
 */
public class SmartLight extends SmartDevice implements Adjustable {
    
    // Additional field specific to lighting
    private int brightness;

    /**
     * Constructor for SmartLight.
     * @param deviceName The specific name of the light (e.g., "Kitchen Light").
     */
    public SmartLight(String deviceName) {
        super(deviceName);
        this.brightness = 0; // Default brightness level
    }

    /**
     * Requirement: Implement turnOn.
     * State change logic ensures the static count is only modified when necessary.
     */
    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            activeDevicesCount++;
            System.out.println(deviceName + " LED initialized and ON.");
        }
    }

    /**
     * Requirement: Implement turnOff.
     */
    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            activeDevicesCount--;
            System.out.println(deviceName + " LED powered OFF.");
        }
    }

    /**
     * Requirement: Implement setLevel from Adjustable interface.
     * Logic: Prevents adjustment if the device is currently unpowered.
     */
    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: " + deviceName + " is OFF.");
        } else {
            // Logic to keep brightness within valid 0-100 range
            if (level < 0) this.brightness = 0;
            else if (level > 100) this.brightness = 100;
            else this.brightness = level;
            
            System.out.println(deviceName + " brightness adjusted to " + this.brightness + "%.");
        }
    }

    /**
     * Requirement: Implement performSelfDiagnostic.
     */
    @Override
    public void performSelfDiagnostic() {
        System.out.println("[" + deviceName + "] Checking LED health and lumen output...");
    }
}