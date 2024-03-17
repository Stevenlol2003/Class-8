import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numInstances = scanner.nextInt();

        for (int i = 0; i < numInstances; i++) {
            int numItems = scanner.nextInt();
            int capacity = scanner.nextInt();
            int[] weights = new int[numItems];
            int[] values = new int[numItems];

            for (int j = 0; j < numItems; j++) {
                weights[j] = scanner.nextInt();
                values[j] = scanner.nextInt();
            }

            int maxVal = knapsack(weights, values, capacity);
            System.out.println(maxVal);
        }
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n+1][capacity+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(values[i-1] + dp[i-1][j-weights[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][capacity];
    }
}
