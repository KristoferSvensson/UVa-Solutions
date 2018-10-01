import java.math.BigInteger;
import java.util.Scanner;

/**
 * Problem nr 374 - Big Mod
 * Created by Kristofer Svensson on 2017-10-23.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger b, p, m;
        while (scanner.hasNext()) {
            b = scanner.nextBigInteger();
            p = scanner.nextBigInteger();
            m = scanner.nextBigInteger();
            BigInteger product = b.modPow(p, m);
            System.out.println(product);
        }
    }
}