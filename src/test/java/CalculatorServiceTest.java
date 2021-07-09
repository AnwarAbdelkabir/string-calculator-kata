import kata.calculator.CalculatorService;
import kata.calculator.NegativeParamExecption;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testBasic() throws Exception {
        assertEquals(14, calculatorService.add("4,5\n5"));
    }

    @Test
    public void testExceptionIsNotOK() {
        Exception thrown = assertThrows(Exception.class, () -> {
            calculatorService.add("4,5\n");
        });
        assertTrue(thrown.getMessage().equals(CalculatorService.msgExeption));
    }

    @Test
    public void testDelimiter() throws Exception {
        assertEquals(15, calculatorService.add("4,5\n6"));
    }

    @Test
    public void testInLine() throws Exception {
        assertEquals(3, calculatorService.add("//;\n1;2"));
    }

    @Test
    public void testInNegatives() {
        String input = "//;\n4;-5\n-5";
        NegativeParamExecption thrown = assertThrows(NegativeParamExecption.class, () -> {
            calculatorService.add(input);
        });

        String delimiter = input.split("\n")[0].trim().substring(2);
        final int indexOfFirstN = input.indexOf("\n");
        String cleanInput = input.substring(indexOfFirstN + 1).replace("\n", delimiter);
        final String[] splitInput = cleanInput.split(delimiter);
        final OptionalInt optionalNegative = Arrays.stream(splitInput).mapToInt(s -> Integer.parseInt(s)).filter(s -> s < 0).findAny();
        if (optionalNegative.isPresent()) {
            final String negaviveNumbersResult = Arrays.stream(splitInput).mapToInt(s -> Integer.parseInt(s))
                    .filter(s -> s < 0).mapToObj(s -> String.valueOf(s)).reduce(" : ", (a, b) -> a + " " + b + " ");

            assertTrue(thrown.getMessage().equals("Negatives not allowed " + negaviveNumbersResult));
        }
    }
}
