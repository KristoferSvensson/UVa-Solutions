import java.io.IOException;
import java.util.Scanner;

/**
 * Problem nr 10300 - Ecological Premium
 * Created by Kristofer Svensson on 2017-10-04.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        int farmers, space, animals, eco, totalPremium;
        double temp;
        for (int i=0; i<testCases; i++) {
            totalPremium = 0;
            farmers = scanner.nextInt();
            for (int j=0; j<farmers; j++) {
                space = scanner.nextInt();
                animals = scanner.nextInt();
                eco = scanner.nextInt();
                temp = ((double)space)/((double)animals);
                temp *=eco;
                temp *=animals;
                totalPremium += Math.round(temp);
            }
            System.out.println(totalPremium);
        }
    }
}