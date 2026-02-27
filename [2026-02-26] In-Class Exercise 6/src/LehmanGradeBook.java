
public class LehmanGradeBook {

    /**
     * Determines if a grade is passing (70 or above).
     * Throws IllegalArgumentException if grade is not between 0 and 100.
     */
    public boolean isPassing(int grade) {
        // Phase 4: Handle "bad" data with an exception
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        
        // Phase 2: Implementation of passing logic
        return grade >= 70;
    }

    /**
     * Phase 3: Returns the letter grade based on numeric score.
     */
    public char getLetterGrade(int score) {
        // We use 'if-else' or simple 'if' returns to handle the boundaries
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else {
            return 'F';
        }
    }
}