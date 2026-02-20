import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Processor {
    public static void main(String[] args) {
        // Define file paths
        File inputFile = new File("students.txt");
        File outputFile = new File("grades_report.txt");

        // Try-with-resources handles automatic closing of resources
        try (Scanner reader = new Scanner(inputFile);
             PrintWriter writer = new PrintWriter(outputFile)) {

            System.out.println("--- Starting Gradebook Processing ---");

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);

                try {
                    // Check if the line has a name
                    if (!lineScanner.hasNext()) continue;
                    String name = lineScanner.next();

                    // Parse the three test scores
                    double sum = 0;
                    int count = 0;
                    
                    while (lineScanner.hasNext()) {
                        String token = lineScanner.next();
                        sum += Double.parseDouble(token); // May throw NumberFormatException
                        count++;
                    }

                    if (count == 0) continue;

                    double average = sum / count;

                    // Bonus Challenge: Custom Exception for low grades
                    if (average < 60) {
                        throw new LowGradeException("Low average detected for " + name);
                    }

                    // Successful data writing
                    writer.println("Student: " + name + " | Average: " + String.format("%.2f", average));

                } catch (NumberFormatException e) {
                    System.err.println("Skipping Student: Invalid score data found in line [" + line + "]");
                } catch (LowGradeException e) {
                    // Handle the bonus challenge requirement
                    String name = line.split(" ")[0];
                    writer.println("Student: " + name + " | Average: [WARNING - FAIL]");
                    System.out.println("Warning: " + e.getMessage());
                } finally {
                    lineScanner.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file 'students.txt' was not found in the project folder.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Requirement: Print completion message regardless of success or failure
            System.out.println("Processing Complete.");
        }
    }
}