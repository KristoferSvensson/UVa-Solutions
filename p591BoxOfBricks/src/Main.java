import java.io.IOException;
import java.util.Scanner;

/**
 * Problem nr 591 - Box of Bricks
 * Created by Kristofer Svensson on 2017-10-04.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int set = 0;
        while (true) {
            int nbrOfStacks = scanner.nextInt();
            int height = 0, operations = 0;
            if (nbrOfStacks > 0) {
                int totalBlocks = 0;
                int[] stacks = new int[nbrOfStacks];
                for (int i = 0; i < stacks.length; i++) {
                    stacks[i] = scanner.nextInt();
                    totalBlocks += stacks[i];
                }
                height = totalBlocks / nbrOfStacks;
                for (int j = 0; j < stacks.length; j++) {
                    if (stacks[j] > height) {
                        operations += stacks[j] - height;
                    }
                }
                set++;
                System.out.println("Set #" + set);
                System.out.println("The minimum number of moves is " + operations + ".");
                System.out.println();
            } else {
                break;
            }
        }
    }
}