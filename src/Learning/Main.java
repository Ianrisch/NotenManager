package Learning;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(1, 1);
        System.out.println("Calculator left value: " + calculator.LeftNumber);
        System.out.println(calculator.CalculateSum());
    }
}
