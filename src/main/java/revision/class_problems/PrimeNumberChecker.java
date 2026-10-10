package revision.class_problems;

public class PrimeNumberChecker {

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        // Only check divisors up to the square root of n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;   // found a divisor: not prime
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numbers = {29, 21};

        for (int n : numbers) {
            if (isPrime(n)) {
                System.out.println(n + " is prime");
            } else {
                System.out.println(n + " is not prime");
            }
        }
    }
}