/**
 * Concrete Subclass: SmartThermostat
 * Inherits from SmartDevice and implements Adjustable to manage home temperature.
 */
public class SmartThermostat extends SmartDevice implements Adjustable {
    
    private int temperature;

    /**
     * Constructor for SmartThermostat.
     * @param deviceName The location of the thermostat (e.g., "Hallway").
     */
    public SmartThermostat(String deviceName) {
        super(deviceName);
        this.temperature = 70; // Default comfortable room temperature
    }

    /**
     * Requirement: Override turnOn().
     * Prints a specific HVAC message before using super to execute the base power logic.
     */
    @Override
    public void turnOn() {
        System.out.println("HVAC System Starting...");
        // Calls the logic in SmartDevice to handle isOn flag and activeDevicesCount
        super.turnOn();
    }

    /**
     * Requirement: Implement setLevel() for temperature logic.
     * Logic: Enforces a safe range of 60-80 degrees and checks if the power is on.
     */
    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: " + deviceName + " is OFF.");
        } else if (level < 60 || level > 80) {
            System.out.println("Error: Temperature " + level + " is out of range (60-80 degrees).");
        } else {
            this.temperature = level;
            System.out.println(deviceName + " set to " + this.temperature + " degrees.");
        }
    }

    /**
     * Implementation of the abstract method from SmartDevice.
     */
    @Override
    public void performSelfDiagnostic() {
        System.out.println("[" + deviceName + "] Calibrating temperature sensors and checking air flow...");
    }
}