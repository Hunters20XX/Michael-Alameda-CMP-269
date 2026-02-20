import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A JavaFX application for student course registration.
 * This class handles both the UI layout and the event logic.
 */
public class RegistrationApp extends Application {

    @SuppressWarnings("unused")
	@Override
    public void start(Stage primaryStage) {
        // 1. Setup the Layout (GridPane)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15); // Vertical gap between components
        grid.setHgap(10); // Horizontal gap between components
        grid.setAlignment(Pos.CENTER);

        // 2. Create UI Components
        Label lblName = new Label("Student Name:");
        TextField txtName = new TextField();
        txtName.setPromptText("Enter your full name");

        Label lblCourse = new Label("Course Code:");
        ComboBox<String> comboCourse = new ComboBox<>();
        comboCourse.getItems().addAll("CMP 167", "CMP 269", "CMP 338", "MAT 172", "ENG 111");
        comboCourse.setPromptText("Select a Course");
        comboCourse.setMaxWidth(Double.MAX_VALUE); // Make it fill the column width

        Button btnRegister = new Button("Register");
        btnRegister.setStyle("-fx-background-color: #005a31; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblStatus = new Label();
        lblStatus.setWrapText(true); // Allow long messages to wrap
        lblStatus.setStyle("-fx-font-style: italic;");

        // 3. Add Components to the Grid (Column, Row)
        grid.add(lblName, 0, 0);
        grid.add(txtName, 1, 0);
        
        grid.add(lblCourse, 0, 1);
        grid.add(comboCourse, 1, 1);
        
        grid.add(btnRegister, 1, 2);
        grid.add(lblStatus, 0, 3, 2, 1); // Span 2 columns at row 3

        // 4. Implement Button Action using a Lambda Expression
        btnRegister.setOnAction(event -> {
            String studentName = txtName.getText().trim();
            String selectedCourse = comboCourse.getValue();

            // Validation check
            if (studentName.isEmpty() || selectedCourse == null) {
                lblStatus.setText("Error: Please provide both a name and a course selection.");
                lblStatus.setStyle("-fx-text-fill: red;");
            } else {
                // Update status with success message
                lblStatus.setText("Registration Successful for " + studentName + " in " + selectedCourse + "!");
                lblStatus.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                
                // Optional: Clear fields after success
                txtName.clear();
                comboCourse.getSelectionModel().clearSelection();
            }
        });

        // 5. Setup Scene and Stage
        Scene scene = new Scene(grid, 450, 300);
        primaryStage.setTitle("Lehman Course Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launches the JavaFX application lifecycle
        launch(args);
    }
}