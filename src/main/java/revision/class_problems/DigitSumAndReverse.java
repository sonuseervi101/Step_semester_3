package revision.class_problems;

public class DigitSumAndReverse {

    static void processNumber(int number) {
        int sum = 0;
        int reversed = 0;
        int n = number;   // work on a copy so the original stays intact

        while (n > 0) {
            int lastDigit = n % 10;             // get the last digit
            sum = sum + lastDigit;
            reversed = reversed * 10 + lastDigit;
            n = n / 10;                         // remove the last digit
        }

        System.out.println("Sum of digits: " + sum);
        System.out.println("Reverse: " + reversed);
    }

    public static void main(String[] args) {
        processNumber(4721);
    }
}