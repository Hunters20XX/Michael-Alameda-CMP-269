import java.util.ArrayList;

/**
 * Main Application Class: App
 * Manages the Smart Home devices and tests system logic.
 */
public class App {
    public static void main(String[] args) {
        // 1. Create an ArrayList of the abstract type 'SmartDevice'
        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        // 2. Add two SmartLight objects and one SmartThermostat
        SmartLight livingRoomLight = new SmartLight("Living Room Light");
        SmartLight kitchenLight = new SmartLight("Kitchen Light");
        SmartThermostat hallwayThermostat = new SmartThermostat("Hallway Thermostat");

        homeHub.add(livingRoomLight);
        homeHub.add(kitchenLight);
        homeHub.add(hallwayThermostat);

        System.out.println("--- Executing Logic Test ---");

        // 3. Turn "On" the Living Room Light and the Hallway Thermostat
        livingRoomLight.turnOn();
        hallwayThermostat.turnOn();

        // 4. Set the Kitchen Light brightness to 75
        // Requirement Check: This should print a failure message because it hasn't been turned on.
        kitchenLight.setLevel(75);

        // 5. Print the total activeDevicesCount from the SmartDevice class
        // Requirement Check: Since only 2 devices were turned on, this should print 2.
        System.out.println("\nGlobal System Status: " + SmartDevice.activeDevicesCount + " device(s) currently active.");

        // 6. Polymorphism: Loop through the homeHub and run diagnostics
        // Every object is treated as a SmartDevice, but executes its own specific diagnostic.
        System.out.println("\n--- Running System-Wide Diagnostics ---");
        for (SmartDevice device : homeHub) {
            device.performSelfDiagnostic();
        }
        
        System.out.println("\n--- End of Logic Test ---");
    }
}