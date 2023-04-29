package Learning;


public class Calculator {
    public int LeftNumber;
    public int RightNumber;

    public Calculator(int leftNumber, int rightNumber) {
        LeftNumber = leftNumber;
        RightNumber = rightNumber;
    }
    public int CalculateSum(){
        return LeftNumber + RightNumber;
    }
}
