import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LehmanGradeBookTest {

	@Test
	@DisplayName("Grade 70 should return true for passing")
	void testPassingGrade() {
	    LehmanGradeBook gb = new LehmanGradeBook();
	    boolean result = gb.isPassing(70);
	    
	    // Add this to see it in the Console!
	    System.out.println("Testing grade 70. Result: " + result);
	    
	    assertTrue(result, "A grade of 70 should pass.");
	}
    
    @Test
    void testLetterGrades() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(95));
        assertEquals('F', gb.getLetterGrade(50));
    }

    @Test
    void testBoundaries() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(90));
        assertEquals('B', gb.getLetterGrade(80));
        assertEquals('C', gb.getLetterGrade(70));
    }
    
    @Test
    void testInvalidGradeThrowsException() {
        LehmanGradeBook gb = new LehmanGradeBook();
        
        // YOU REFERENCE THE CLASS HERE
        assertThrows(IllegalArgumentException.class, () -> {
            gb.isPassing(150); // This call triggers the 'throw' in the main class
        });
    }
}