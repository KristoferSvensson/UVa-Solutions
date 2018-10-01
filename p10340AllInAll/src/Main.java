import java.util.Scanner;

/**
 * Problem nr 10340 - All in All
 * Created by Kristofer Svensson on 2017-12-30.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Main prog = new Main();
        prog.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] strings = scanner.nextLine().split(" ");
            char[] sequence1 = strings[0].toCharArray();
            char[] sequence2 = strings[1].toCharArray();
            int charIndex = 0;
            boolean subsequenceFound = false;
            for (int i = 0; i < sequence2.length; i++) {
                if (sequence2[i] == sequence1[charIndex]) {
                    charIndex++;
                }
                if (charIndex >= sequence1.length) {
                    subsequenceFound = true;
                    break;
                }
            }
            if (subsequenceFound) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}