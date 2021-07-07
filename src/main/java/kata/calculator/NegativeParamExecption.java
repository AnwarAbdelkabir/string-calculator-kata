package kata.calculator;

public class NegativeParamExecption extends Exception {

    public NegativeParamExecption(String negaviveNumbers) {
        super("Negatives not allowed " + negaviveNumbers);
    }


}
