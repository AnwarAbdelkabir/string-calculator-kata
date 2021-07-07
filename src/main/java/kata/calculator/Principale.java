package kata.calculator;

public class Principale {

    public static void main(String[] args) throws Exception {
        System.out.println("Start ...");
        CalculatorService calculatorService = new CalculatorService();

        //final int result = calculatorService.Add("4,5\n");
        //System.out.println(result);
        final int result1 = calculatorService.Add("4,5\n5");
        System.out.println(result1);
        final int result2 = calculatorService.Add("//;\n4;5\n5");
        System.out.println(result2);
        final int result3 = calculatorService.Add("//;\n1;2");
        System.out.println(result3);
        final int result4 = calculatorService.Add("//;\n4;-5\n-5");
        System.out.println(result4);
    }


}
