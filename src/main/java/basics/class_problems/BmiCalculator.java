package basics.class_problems;

public class BmiCalculator {

    // Decides the health status from the BMI value
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // Calculates BMI for everyone and prints the table
    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person\tHeight(m)\tWeight(kg)\tBMI\t\tStatus");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            System.out.println((i + 1) + "\t\t" + String.format("%.2f", heights[i]) + "\t\t" + weights[i]
                    + "\t\t" + String.format("%.2f", bmi) + "\t" + status);
        }
    }

    public static void main(String[] args) {
        // Data for 10 employees
        double[] heights = {1.75, 1.60, 1.68, 1.82, 1.55, 1.70, 1.65, 1.78, 1.62, 1.90};
        double[] weights = {70, 90, 50, 85, 45, 68, 80, 95, 58, 75};

        printWellnessReport(heights, weights);
    }
}