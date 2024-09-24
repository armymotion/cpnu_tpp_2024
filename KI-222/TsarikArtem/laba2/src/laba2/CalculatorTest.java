package laba2;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    public void testPlus() {
        int result = calculator.plus(2, 2);
        if (result != 4) {
            throw new TestException("2+2 must be 4");
        }
    }

    public void testMinus() {
        int result = calculator.subtract(4, 2);
        if (result != 2) {
            throw new TestException("4-2 must be 2");
        }
    }

    public void tesMinus() {
        int result = calculator.subtract(4, 2);
        if (result != 2) {
            throw new TestException("4-2 must be 2");
        }
    }

    public void testMultiply() {
        int result = calculator.multiply(3, 3);
        if (result != 9) {
            throw new TestException("3*3 must be 9");
        }
    }
}
