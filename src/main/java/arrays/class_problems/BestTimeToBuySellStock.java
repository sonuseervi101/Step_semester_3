package arrays.class_problems;

import java.util.Arrays;

public class BestTimeToBuySellStock {

    // Returns the largest profit from one buy and one later sell
    public static int maxProfit(int[] prices) {
        int lowestPrice = prices[0];
        int bestProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Profit if we sold today, having bought at the lowest price so far
            int profitToday = prices[i] - lowestPrice;

            if (profitToday > bestProfit) {
                bestProfit = profitToday;
            }

            if (prices[i] < lowestPrice) {
                lowestPrice = prices[i];
            }
        }

        return bestProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("prices = " + Arrays.toString(prices1) + " -> " + maxProfit(prices1));

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("prices = " + Arrays.toString(prices2) + " -> " + maxProfit(prices2));
    }
}