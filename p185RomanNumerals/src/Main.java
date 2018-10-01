import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Problem nr 185 - Roman Numerals
 * Created by Kristofer Svensson on 2018-01-20.
 */

public class Main {

    int firstLength, secondLength, thirdLength;
    Digit I, V, X, L, C, D, M;
    Digit[] first, second, third;
    DigitPointer[] pointers;

    public static void main(String[] args) throws Exception {
        Main prog = new Main();
        prog.start();
    }

    private void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine().trim();
        while (!line.equals("#")) {
            String roman = validateRoman(line);
            String arabic = validateArabic(line);
            System.out.println(roman + " " + arabic);
            line = reader.readLine().trim();
        }
    }

    private String validateRoman(String input) {
        String first = input.split("\\+")[0];
        String second = input.split("\\+")[1].split("=")[0];
        String third = input.split("\\+")[1].split("=")[1];
        if (decode(first) + decode(second) == decode(third))
            return "Correct";
        return "Incorrect";
    }

    private int decodeSingle(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    private int decode(String romanLetters) {
        int result = 0;
        String roman = romanLetters.toUpperCase();
        for (int i = 0; i < roman.length() - 1; i++) {
            if (decodeSingle(roman.charAt(i)) < decodeSingle(roman.charAt(i + 1))) {
                result -= decodeSingle(roman.charAt(i));
            } else {
                result += decodeSingle(roman.charAt(i));
            }
        }
        result += decodeSingle(roman.charAt(roman.length() - 1));
        return result;
    }

    private String validateArabic(String line) throws IOException {

        I = new Digit();
        V = new Digit();
        X = new Digit();
        L = new Digit();
        C = new Digit();
        D = new Digit();
        M = new Digit();
        first = new Digit[9];
        second = new Digit[9];
        third = new Digit[9];
        pointers = new DigitPointer[7];
        initializeEquation(line);
        return arabic();
    }

    private String arabic() {
        int counter = calculateArabic();
        if (counter == 0) {
            return "impossible";
        }
        if (counter == 1) {
            return "valid";
        }
        return "ambiguous";
    }

    private int calculateArabic() {
        int counter = 0;
        for (int i1 = 0; i1 <= 9; i1++) {
            pointers[0].digit.value = i1;
            for (int i2 = 0; i2 <= 9; i2++) {
                if (i2 == i1)
                    continue;
                if (pointers[1] == null) {
                    counter = validateEquation(counter);
                    break;
                }
                pointers[1].digit.value = i2;
                for (int i3 = 0; i3 <= 9; i3++) {
                    if (i3 == i2 || i3 == i1)
                        continue;
                    if (pointers[2] == null) {
                        counter = validateEquation(counter);
                        break;
                    }
                    pointers[2].digit.value = i3;
                    for (int i4 = 0; i4 <= 9; i4++) {
                        if (i4 == i3 || i4 == i2 || i4 == i1)
                            continue;
                        if (pointers[3] == null) {
                            counter = validateEquation(counter);
                            break;
                        }
                        pointers[3].digit.value = i4;
                        for (int i5 = 0; i5 <= 9; i5++) {
                            if (i5 == i4 || i5 == i3 || i5 == i2 || i5 == i1)
                                continue;
                            if (pointers[4] == null) {
                                counter = validateEquation(counter);
                                break;
                            }
                            pointers[4].digit.value = i5;
                            for (int i6 = 0; i6 <= 9; i6++) {
                                if (i6 == i5 || i6 == i4 || i6 == i3 || i6 == i2 || i6 == i1)
                                    continue;
                                if (pointers[5] == null) {
                                    counter = validateEquation(counter);
                                    break;
                                }
                                pointers[5].digit.value = i6;
                                for (int i7 = 0; i7 <= 9; i7++) {
                                    if (i7 == i6 || i7 == i5 || i7 == i4 || i7 == i3 || i7 == i2 || i7 == i1)
                                        continue;
                                    if (pointers[6] == null) {
                                        counter = validateEquation(counter);
                                        break;
                                    }
                                    pointers[6].digit.value = i7;
                                    counter = validateEquation(counter);
                                    if (counter >= 2)
                                        return 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    private void initializeEquation(String line) throws IOException {
        char[] lineChars = line.toCharArray();
        int digitIndex = -1;
        int charIndex = 0;
        ArrayList<Character> symbols = new ArrayList<>();
        for (int i = 0; i < lineChars.length; i++) {
            char c = lineChars[i];
            charIndex++;
            digitIndex++;
            if (c == '+') {
                firstLength = digitIndex;
                break;
            } else {
                switch (c) {
                    case 'I': {
                        first[digitIndex] = I;
                        if (!symbols.contains('I'))
                            symbols.add('I');
                        break;
                    }
                    case 'X': {
                        first[digitIndex] = X;
                        if (!symbols.contains('X'))
                            symbols.add('X');
                        break;
                    }
                    case 'C': {
                        first[digitIndex] = C;
                        if (!symbols.contains('C'))
                            symbols.add('C');
                        break;
                    }
                    case 'M': {
                        first[digitIndex] = M;
                        if (!symbols.contains('M'))
                            symbols.add('M');
                        break;
                    }
                    case 'V': {
                        first[digitIndex] = V;
                        if (!symbols.contains('V'))
                            symbols.add('V');
                        break;
                    }
                    case 'L': {
                        first[digitIndex] = L;
                        if (!symbols.contains('L'))
                            symbols.add('L');
                        break;
                    }
                    case 'D': {
                        first[digitIndex] = D;
                        if (!symbols.contains('D'))
                            symbols.add('D');
                        break;
                    }
                }
            }
        }
        digitIndex = -1;
        for (int i = charIndex; i < lineChars.length; i++) {
            char c = lineChars[i];
            charIndex++;
            digitIndex++;
            if (c == '=') {
                secondLength = digitIndex;
                break;
            } else {
                switch (c) {
                    case 'I': {
                        second[digitIndex] = I;
                        if (!symbols.contains('I'))
                            symbols.add('I');
                        break;
                    }
                    case 'X': {
                        second[digitIndex] = X;
                        if (!symbols.contains('X'))
                            symbols.add('X');
                        break;
                    }
                    case 'C': {
                        second[digitIndex] = C;
                        if (!symbols.contains('C'))
                            symbols.add('C');
                        break;
                    }
                    case 'M': {
                        second[digitIndex] = M;
                        if (!symbols.contains('M'))
                            symbols.add('M');
                        break;
                    }
                    case 'V': {
                        second[digitIndex] = V;
                        if (!symbols.contains('V'))
                            symbols.add('V');
                        break;
                    }
                    case 'L': {
                        second[digitIndex] = L;
                        if (!symbols.contains('L'))
                            symbols.add('L');
                        break;
                    }
                    case 'D': {
                        second[digitIndex] = D;
                        if (!symbols.contains('D'))
                            symbols.add('D');
                        break;
                    }
                }
            }
        }
        digitIndex = -1;
        for (int i = charIndex; i < lineChars.length; i++) {
            char c = lineChars[i];
            digitIndex++;
            switch (c) {
                case 'I': {
                    third[digitIndex] = I;
                    if (!symbols.contains('I'))
                        symbols.add('I');
                    break;
                }
                case 'X': {
                    third[digitIndex] = X;
                    if (!symbols.contains('X'))
                        symbols.add('X');
                    break;
                }
                case 'C': {
                    third[digitIndex] = C;
                    if (!symbols.contains('C'))
                        symbols.add('C');
                    break;
                }
                case 'M': {
                    third[digitIndex] = M;
                    if (!symbols.contains('M'))
                        symbols.add('M');
                    break;
                }
                case 'V': {
                    third[digitIndex] = V;
                    if (!symbols.contains('V'))
                        symbols.add('V');
                    break;
                }
                case 'L': {
                    third[digitIndex] = L;
                    if (!symbols.contains('L'))
                        symbols.add('L');
                    break;
                }
                case 'D': {
                    third[digitIndex] = D;
                    if (!symbols.contains('D'))
                        symbols.add('D');
                    break;
                }
            }
            thirdLength = digitIndex + 1;
        }
        for (int i1 = 0; i1 < symbols.size(); i1++) {
            Character myChar = symbols.get(i1);
            switch (myChar) {
                case 'I': {
                    pointers[i1] = new DigitPointer(I);
                    break;
                }
                case 'X': {
                    pointers[i1] = new DigitPointer(X);
                    break;
                }
                case 'C': {
                    pointers[i1] = new DigitPointer(C);
                    break;
                }
                case 'M': {
                    pointers[i1] = new DigitPointer(M);
                    break;
                }
                case 'V': {
                    pointers[i1] = new DigitPointer(V);
                    break;
                }
                case 'L': {
                    pointers[i1] = new DigitPointer(L);
                    break;
                }
                case 'D': {
                    pointers[i1] = new DigitPointer(D);
                    break;
                }
            }
        }
    }

    private int validateEquation(int counter) {
        int part1 = generatePart1();
        int part2 = generatePart2();
        int part3 = generatePart3();
        if (part1 == -1 || part2 == -1 || part3 == -1)
            return counter;
        if (part1 + part2 == part3) {
            return counter + 1;
        }
        return counter;
    }

    private int generatePart1() {
        if (first[0].value == 0)
            return -1;
        String result = "";
        for (int i = 0; i < firstLength; i++) {
            result += first[i].value;
        }
        return Integer.parseInt(result);
    }

    private int generatePart2() {
        if (second[0].value == 0)
            return -1;
        String result = "";
        for (int i = 0; i < secondLength; i++) {
            result += second[i].value;
        }
        return Integer.parseInt(result);
    }

    private int generatePart3() {
        if (third[0].value == 0)
            return -1;
        String result = "";
        for (int i = 0; i < thirdLength; i++) {
            result += third[i].value;
        }
        return Integer.parseInt(result);
    }

    public class Digit {
        public int value;

        public Digit() {
            value = 0;
        }
    }

    public class DigitPointer {
        public Digit digit;

        public DigitPointer(Digit i) {
            digit = i;
        }
    }
}
