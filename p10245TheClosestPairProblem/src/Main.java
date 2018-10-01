import java.util.Scanner;

/**
 * Problem nr 10245 - The Closest Pair Problem
 * Created by Kristofer Svensson on 2018-01-21.
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        while (testcases != 0) {
            double[][] matrix = new double[testcases][2];
            for (int i = 0; i < testcases; i++) {
                matrix[i][0] = scanner.nextDouble();
                matrix[i][1] = scanner.nextDouble();
            }
            if (testcases == 1) {
                System.out.println("INFINITY");
                testcases = scanner.nextInt();
                continue;
            }
            double shortestDistance = Double.MAX_VALUE;
            for (int i = 0; i < testcases; i++) {
                for (int j = i + 1; j < testcases; j++) {
                    double distance = calculateDistance(matrix[i], matrix[j]);
                    if (distance < shortestDistance)
                        shortestDistance = distance;
                }
            }
            shortestDistance = Math.round(shortestDistance * 10000.0) / 10000.0;
            if (shortestDistance <= 10000.0000)
                System.out.printf("%.4f%n", shortestDistance);
            else
                System.out.println("INFINITY");
            testcases = scanner.nextInt();
        }
    }

    private static double calculateDistance(double[] p1, double[] p2) {
        double dx = Math.abs(p1[0] - p2[0]);
        double dy = Math.abs(p1[1] - p2[1]);
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}