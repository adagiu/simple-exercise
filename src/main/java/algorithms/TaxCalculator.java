package algorithms;

import utils.test.Test;

public class TaxCalculator {

    private static double calculateTax(int amount, double[][] taxBands) {
        double totalTax = 0;
        double partialAmount = amount;

        for (double[] band : taxBands) {
            if (band[0] == -1 || partialAmount < band[0]) {
                return totalTax + partialAmount * band[1];
            } else {
                totalTax += band[0] * band[1];
                partialAmount -= band[0];
            }
        }
        return totalTax;
    }

    public static void main(String[] args) {
        double[][] tax = new double[][]{
                {10000, 0.1},
                {20000, 0.2},
                {10000, 0.3},
                {-1, 0.4}
        };

        // 50K -> 1000 + 4000 + 3000 + 4000 = 12000
        Test.equals(calculateTax(50000, tax), 12000.0);

        // 45K -> 1000 + 4000 + 3000 + 2000 = 10000
        Test.equals(calculateTax(45000, tax), 10000.0);

        // 15K -> 1000 + 1000 = 2000
        Test.equals(calculateTax(15000, tax), 2000.0);

        // 5K -> 500
        Test.equals(calculateTax(5000, tax), 500.0);

        Test.equals(calculateTax(0, tax), 0.0);
    }
}
