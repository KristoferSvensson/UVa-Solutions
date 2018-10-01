import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem nr 116 - Unidirectional TSP
 * Created by Kristofer Svensson on 2018-01-20.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int rows, columns;
        while (scanner.hasNext()) {
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            if (rows < 1 || columns < 1) {
                continue;
            }
            Node[][] matrix = new Node[rows][columns];
            int lastColumn = columns - 1;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    matrix[r][c] = new Node();
                    matrix[r][c].weight = scanner.nextInt();
                    if (c == lastColumn) {
                        matrix[r][lastColumn].distance = matrix[r][lastColumn].weight;
                    } else {
                        matrix[r][c].distance = Long.MAX_VALUE;
                    }
                }
            }
            for (int c = columns - 2; c >= 0; c--) {
                for (int r = 0; r < rows; r++) {
                    int up = (r == 0) ? rows - 1 : r - 1;
                    int down = (r + 1) % rows;
                    int nextColumn = c + 1;
                    int[] rowsToAttempt = {up, r, down};
                    Arrays.sort(rowsToAttempt);
                    matrix[r][c].next = Integer.MAX_VALUE;
                    for (int attemptedRow : rowsToAttempt) {
                        if (matrix[r][c].next == Integer.MAX_VALUE ||
                                (matrix[attemptedRow][nextColumn].distance < matrix[matrix[r][c].next][nextColumn].distance)) {
                            matrix[r][c].next = attemptedRow;
                            matrix[r][c].distance = matrix[r][c].weight + matrix[attemptedRow][nextColumn].distance;
                        }
                    }
                }
            }
            int startRow = 0;
            long shortest = Long.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][0].distance < shortest) {
                    startRow = i;
                    shortest = matrix[i][0].distance;
                }
            }
            for (int i = 0; i < columns; i++) {
                System.out.print((i > 0 ? " " : "") + (startRow + 1));
                startRow = matrix[startRow][i].next;
            }
            System.out.println();
            System.out.println(shortest);
        }
    }

    private static class Node {
        public int weight;
        public long distance;
        public int next;

        public Node() {
            weight = 0;
            distance = 0;
            next = 0;
        }
    }
}