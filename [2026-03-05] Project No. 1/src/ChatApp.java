import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.*;
import java.net.Socket;

/**
 * JavaFX Desktop Client for the Lehman Multi-Platform Chat System
 * Features a professional chat interface with proper thread safety
 */
public class ChatApp extends Application {

    // UI Components
    private TextArea chatArea;
    private TextField messageField;
    private Button sendButton;
    
    // Network components
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected = false;
    private String username;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Show connection dialog first
        showConnectionDialog(primaryStage);
    }

    /**
     * Initial connection dialog to get server IP and username
     */
    private void showConnectionDialog(Stage primaryStage) {
        Stage dialog = new Stage();
        dialog.setTitle("Connect to Chat Server");
        dialog.initModality(Modality.APPLICATION_MODAL);

        VBox dialogVBox = new VBox(15);
        dialogVBox.setPadding(new Insets(20));
        dialogVBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Enter Connection Details");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField serverField = new TextField("localhost");
        serverField.setPromptText("Server Address");

        TextField portField = new TextField("59001");
        portField.setPromptText("Port");

        TextField nameField = new TextField();
        nameField.setPromptText("Your Name");

        Button connectButton = new Button("Connect");
        connectButton.setDefaultButton(true);

        dialogVBox.getChildren().addAll(
            titleLabel,
            new Label("Server:"), serverField,
            new Label("Port:"), portField,
            new Label("Name:"), nameField,
            connectButton
        );

        connectButton.setOnAction(e -> {
            String server = serverField.getText().trim();
            int port;
            String name = nameField.getText().trim();

            try {
                port = Integer.parseInt(portField.getText().trim());
            } catch (NumberFormatException ex) {
                showAlert("Invalid port number");
                return;
            }

            if (name.isEmpty()) {
                showAlert("Please enter your name");
                return;
            }

            // Try to connect
            if (connectToServer(server, port, name)) {
                dialog.close();
                showChatWindow(primaryStage);
            } else {
                showAlert("Could not connect to server");
            }
        });

        Scene dialogScene = new Scene(dialogVBox);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    /**
     * Main chat window with BorderPane layout
     */
    private void showChatWindow(Stage stage) {
        stage.setTitle("Lehman Chat - " + username);

        // Create the main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // ===== CENTER: Chat Area (non-editable) =====
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14px;");
        
        // Use VBox to expand TextArea to fill available space
        VBox centerBox = new VBox(chatArea);
        VBox.setVgrow(chatArea, javafx.scene.layout.Priority.ALWAYS);
        root.setCenter(centerBox);

        // ===== BOTTOM: Input Area =====
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(10, 0, 0, 0));

        messageField = new TextField();
        messageField.setPromptText("Type your message...");
        messageField.setPrefWidth(400);
        HBox.setHgrow(messageField, javafx.scene.layout.Priority.ALWAYS);

        sendButton = new Button("Send");
        sendButton.setPrefWidth(80);

        // Event handling: Send on button click OR Enter key
        sendButton.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage());

        bottomBox.getChildren().addAll(messageField, sendButton);
        root.setBottom(bottomBox);

        // Handle window close - clean shutdown
        stage.setOnCloseRequest(e -> {
            disconnect();
        });

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.show();

        // Start listening for messages
        startMessageListener();
    }

    /**
     * Attempt to connect to the chat server
     */
    private boolean connectToServer(String server, int port, String name) {
        try {
            socket = new Socket(server, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Wait for server's name prompt
            String serverMessage = in.readLine();
            
            // Send our name
            out.println(name);

            username = name;
            connected = true;
            return true;

        } catch (IOException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Background thread to continuously listen for server messages
     */
    private void startMessageListener() {
        Thread listenerThread = new Thread(() -> {
            try {
                String message;
                while (connected && (message = in.readLine()) != null) {
                    // CRITICAL: Must use Platform.runLater() to update UI
                    final String msg = message;
                    Platform.runLater(() -> {
                        chatArea.appendText(msg + "\n");
                        // Auto-scroll to bottom
                        chatArea.setScrollTop(Double.MAX_VALUE);
                    });
                }
            } catch (IOException e) {
                if (connected) {
                    Platform.runLater(() -> {
                        chatArea.appendText("Connection lost: " + e.getMessage() + "\n");
                    });
                }
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    /**
     * Send message to server
     */
    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && connected) {
            out.println(message);
            messageField.clear();
            messageField.requestFocus();
        }
    }

    /**
     * Clean disconnect when user closes window
     */
    private void disconnect() {
        connected = false;
        try {
            if (out != null) {
                out.println("QUIT");
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error during disconnect: " + e.getMessage());
        }
        Platform.exit();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}