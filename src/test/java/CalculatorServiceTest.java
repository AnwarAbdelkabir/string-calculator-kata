import kata.calculator.CalculatorService;
import kata.calculator.NegativeParamExecption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    private static String testBasicInput = "4,5\n5";
    private static String testExceptionIsNotOKInput = "4,5\n";
    private static String testEmptyParamInput = "";
    private static String testDelimiterInput = "4,5\n6";
    private static String testInLineInput = "//;\n1;2";
    private static String testInNegativesInput = "//;\n4;-5\n-5";

    @BeforeAll
    public static void init() {
        System.out.println("=========> Start CalculatorService'tests ...");
    }

    @Test
    public void testBasic() throws Exception {
        assertEquals(14, calculatorService.add(testBasicInput));
    }

    @Test
    public void testEmptyParam() {
        Exception thrown = assertThrows(Exception.class, () -> {
            calculatorService.add(testEmptyParamInput);
        });
        assertTrue(thrown.getMessage().equals(CalculatorService.msgExeption));
    }

    @Test
    public void testExceptionIsNotOK() {
        Exception thrown = assertThrows(Exception.class, () -> {
            calculatorService.add(testExceptionIsNotOKInput);
        });
        assertTrue(thrown.getMessage().equals(CalculatorService.msgExeption));
    }

    @Test
    public void testDelimiter() throws Exception {
        assertEquals(15, calculatorService.add(testDelimiterInput));
    }

    @Test
    public void testInLine() throws Exception {
        assertEquals(3, calculatorService.add(testInLineInput));
    }

    @Test
    public void testInNegatives() {
        NegativeParamExecption thrown = assertThrows(NegativeParamExecption.class, () -> {
            calculatorService.add(testInNegativesInput);
        });

        String delimiter = testInNegativesInput.split("\n")[0].trim().substring(2);
        final int indexOfFirstN = testInNegativesInput.indexOf("\n");
        String cleanInput = testInNegativesInput.substring(indexOfFirstN + 1).replace("\n", delimiter);
        final String[] splitInput = cleanInput.split(delimiter);
        final OptionalInt optionalNegative = Arrays.stream(splitInput).mapToInt(s -> Integer.parseInt(s)).filter(s -> s < 0).findAny();
        if (optionalNegative.isPresent()) {
            final String negaviveNumbersResult = Arrays.stream(splitInput).mapToInt(s -> Integer.parseInt(s))
                    .filter(s -> s < 0).mapToObj(s -> String.valueOf(s)).reduce(" : ", (a, b) -> a + " " + b + " ");

            assertTrue(thrown.getMessage().equals("Negatives not allowed " + negaviveNumbersResult));
        }
    }
}
