import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeClassifierTest {

    @Test
    @DisplayName("Single Parameter: Line")
    public void testSingleParameter_Line() {
        ShapeClassifier s = new ShapeClassifier();
        String result = s.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes", result, "Line with correct guesses should return 'Yes'");
    }

    @Test
    @DisplayName("Two Parameters: Circle")
    public void testTwoParameters_Circle() {
        ShapeClassifier s = new ShapeClassifier();
        String result = s.evaluateGuess("Circle,Large,No,15,15");
        assertEquals("Yes", result, "Circle with correct guesses should return 'Yes'");
    }

    @Test
    @DisplayName("Triangle Classification: Equilateral")
    public void testTriangleClassification_Equilateral() {
        ShapeClassifier s = new ShapeClassifier();
        String result = s.evaluateGuess("Equilateral,Large,Yes,50,50,50");
        assertEquals("Yes", result, "Equilateral triangle with correct guesses should return 'Yes'");
    }

    @Test
    @DisplayName("Four Parameters: Rectangle")
    public void testRectangleWithIncorrectGuesses() {
        ShapeClassifier s = new ShapeClassifier();
        String result = s.evaluateGuess("Rectangle,Small,Yes,20,10,20,10");
        assertEquals("Wrong Size,Wrong Even/Odd", result,
                "Incorrect size and even/odd guesses should be identified");
    }

    @Test
    @DisplayName("Bad Guesses: Exceed Limit")
    public void testBadGuessesExceedingLimit() {
        ShapeClassifier s = new ShapeClassifier();
        s.evaluateGuess("Circle,Large,Yes,5,7"); // Wrong shape
        s.evaluateGuess("Rectangle,Small,No,20,20,20,20"); // Wrong size and even/odd
        String result = s.evaluateGuess("Scalene,Small,Yes,10,15,20"); // All incorrect
        assertEquals("No: Suggestion=Scalene", result, "Should suggest correct shape");
    }
}
