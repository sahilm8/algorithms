package algorithms.dynamic;

/*
 * Dynamic programming is an algorithmic technique that solves complex problems
 * by breaking them down into simpler subproblems. It stores the results of these
 * subproblems to avoid redundant computations, thus optimizing the overall solution.
 */
public class DynamicProgramming {
    public static int fibonacci(int n) {
        int[] fib = new int[n + 1]; // Calculating Fibonacci from 0 to n inclusive, hence size is n + 1
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <=n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
    public static void main(String[] args) {
        System.out.println(fibonacci(10)); // Output: 55
    }
}
