import java.util.Scanner;

/**
 * Problem nr 10878 - Decode the Tape
 * Created by Kristofer Svensson on 2017-10-09.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String result = "";
        while (true) {
            String binaryString = "";
            String row = scanner.nextLine();
            char[] rowArray = row.toCharArray();
            if (rowArray[0] == '_') {
                break;
            }
            for (char sign : rowArray) {
                if (sign == 'o') {
                    binaryString += "1";
                } else if (sign == ' ') {
                    binaryString += "0";
                }
            }
            int charCode = Integer.parseInt(binaryString, 2);
            result += (char) charCode;
        }
        System.out.print(result);
    }
}