import java.util.Scanner;

/**
 * Problem nr 10943 - How Do You Add?
 * Created by Kristofer Svensson on 2018-01-01.
 */
public class Main {
    static int[][] matrix = new int[101][101];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        for (int i = 0; i <= 100; i++) {
            matrix[i][1] = 1;
            matrix[1][i] = i;
        }
        while (!(k == 0 && n == 0)) {
            int res = calculate(n, k);
            System.out.println(res);
            n = scanner.nextInt();
            k = scanner.nextInt();
        }
    }

    private static int calculate(int n, int k) {
        if (matrix[n][k] != 0) {
            return matrix[n][k];
        }
        for (int i = 0; i <= n; i++) {
            matrix[n][k] = (matrix[n][k] + calculate(i, k - 1)) % 1000000;
        }
        return matrix[n][k];
    }
}
