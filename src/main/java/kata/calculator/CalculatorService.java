package kata.calculator;

import java.util.Arrays;
import java.util.OptionalInt;

public class CalculatorService {

    public static String msgExeption = "The input param is NOT ok";
    private String defaultDelimiter = ",";

    public int add(String numbers) throws Exception {
        if (!numbers.isEmpty()) {
            // Step 4
            if (numbers.startsWith("//")) {
                defaultDelimiter = numbers.split("\n")[0].trim().substring(2);
                final int indexOfFirstN = numbers.indexOf("\n");
                numbers = numbers.substring(indexOfFirstN + 1);
            }
            // Step 3
            if (numbers.endsWith("\n")) {
                throw new Exception(CalculatorService.msgExeption);
            } else {
                numbers = numbers.replace("\n", defaultDelimiter);
            }
            final String[] splitNumbers = numbers.split(defaultDelimiter);
            // Step 5
            final OptionalInt optionalNegative = Arrays.stream(splitNumbers).mapToInt(s -> Integer.parseInt(s)).filter(s -> s < 0).findAny();
            if (optionalNegative.isPresent()) {
                final String negaviveNumbersResult = Arrays.stream(splitNumbers).mapToInt(s -> Integer.parseInt(s))
                        .filter(s -> s < 0).mapToObj(s -> String.valueOf(s)).reduce(" : ", (a, b) -> a + " " + b + " ");
                throw new NegativeParamExecption(negaviveNumbersResult);
            }
            final int sum = Arrays.stream(splitNumbers).mapToInt(s -> Integer.parseInt(s)).sum();
            return sum;
        }
        return 0;
    }
}
