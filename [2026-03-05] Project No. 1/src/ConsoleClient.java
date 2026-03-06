import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Console Client for the Lehman Multi-Platform Chat System
 * Handles bidirectional communication with the ChatServer
 */
public class ConsoleClient {
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private boolean running;

    public ConsoleClient() {
        scanner = new Scanner(System.in);
        running = true;
    }

    public void connect(String serverAddress, int port) {
        try {
            // Step 1: Establish socket connection
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            System.out.println("Connected to " + serverAddress + ":" + port);
            
            // Step 2: Wait for server's name prompt and send username
            String serverMessage = in.readLine();
            System.out.println(serverMessage);
            
            String username = scanner.nextLine();
            out.println(username);
            
            // Step 3: Start the background listener thread
            startMessageListener();
            
            // Step 4: Main loop - handle user input
            handleUserInput();
            
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    /**
     * Background thread that continuously listens for incoming messages
     * This runs concurrently with the main thread handling user input
     */
    private void startMessageListener() {
        Thread listenerThread = new Thread(() -> {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                if (running) {
                    System.err.println("Error receiving messages: " + e.getMessage());
                }
            }
        });
        listenerThread.setDaemon(true); // Allows JVM to exit even if thread is running
        listenerThread.start();
    }

    /**
     * Main thread handles user input and sends messages to server
     */
    private void handleUserInput() {
        while (running && scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("QUIT")) {
                System.out.println("Disconnecting...");
                running = false;
                break;
            } else if (!input.isEmpty()) {
                out.println(input);
            }
        }
    }

    private void cleanup() {
        running = false;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
        scanner.close();
        System.out.println("Disconnected from chat server.");
    }

    public static void main(String[] args) {
        ConsoleClient client = new ConsoleClient();
        
        // Allow configurable server address and port
        String serverAddress = "localhost";
        int port = 59001;
        
        if (args.length >= 1) {
            serverAddress = args[0];
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }
        
        client.connect(serverAddress, port);
    }
}