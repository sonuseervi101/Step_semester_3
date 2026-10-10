package revision.class_problems;

public class FactorialCalculator {

    static long factorial(int n) {
        long result = 1;          // 0! = 1, so starting at 1 handles the 0 case automatically
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] inputs = {5, 0};

        for (int n : inputs) {
            System.out.println(factorial(n));
        }
    }
}