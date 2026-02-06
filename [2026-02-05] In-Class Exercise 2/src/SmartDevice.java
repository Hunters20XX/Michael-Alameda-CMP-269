/**
 * Abstract Class: SmartDevice
 * This class provides the base functionality for all smart devices in the home.
 * It implements Powerable to handle basic ON/OFF state management.
 */
public abstract class SmartDevice implements Powerable {
    
    // Protected fields accessible by subclasses
    protected String deviceName;
    protected boolean isOn;
    
    // Static member to track how many devices are "On" globally
    protected static int activeDevicesCount = 0;

    /**
     * Constructor to initialize the device name.
     * By default, all devices start in the "Off" state.
     */
    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.isOn = false;
    }

    /**
     * Abstract Method: Must be implemented by concrete devices.
     * Each device type has a unique way of checking its internal health.
     */
    public abstract void performSelfDiagnostic();

    /**
     * Implementation of turnOn from the Powerable interface.
     * Updates the global count only if the state changes.
     */
    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            activeDevicesCount++;
            System.out.println(deviceName + " is now powered ON.");
        }
    }

    /**
     * Implementation of turnOff from the Powerable interface.
     * Updates the global count only if the state changes.
     */
    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            activeDevicesCount--;
            System.out.println(deviceName + " is now powered OFF.");
        }
    }
}