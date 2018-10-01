import java.util.Scanner;

/**
 * Problem nr 11054 - Wine Trading in Gergovia
 * Created by Kristofer Svensson on 2017-10-23.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Main prog = new Main();
        prog.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        int houses = scanner.nextInt();
        while (houses > 0) {
            long cost = 0;
            int balance = 0;
            for (int i = 0; i < houses; i++) {
                int trade = scanner.nextInt();
                balance += trade;
                if (balance < 0) {
                    cost -= balance;
                } else {
                    cost += balance;
                }
            }
            System.out.println(cost);
            houses = scanner.nextInt();
        }
    }
}
