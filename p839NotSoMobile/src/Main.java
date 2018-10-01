import java.util.Scanner;

/*
 * * Problem nr 839 - Not so Mobile
 * Created by Kristofer Svensson on 2017-10-09.
*/

public class Main {
    Scanner scanner = new Scanner(System.in);

    public int[] makeAttributeArray() {
        int[] attributes = new int[4];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = scanner.nextInt();
        }
        return attributes;
    }

    public void main() {
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            Mobile root = addMobile();
            String balanced = root.isBalanced() ? "YES" : "NO";
            System.out.println(balanced);
            if (i != testCases - 1) {
                System.out.println();
            }
        }
    }

    public Mobile addMobile() {
        Mobile m = new Mobile();
        int[] attributes = makeAttributeArray();
        if (attributes[0] == 0) {
            m.leftChild = addMobile();
        } else {
            m.wLeft = attributes[0];
        }
        m.dLeft = attributes[1];
        if (attributes[2] == 0) {
            m.rightChild = addMobile();
        } else {
            m.wRight = attributes[2];
        }
        m.dRight = attributes[3];
        return m;
    }

    private class Mobile {

        public int wLeft, dLeft, wRight, dRight;
        public Mobile leftChild, rightChild;

        public int getTotalWeight() {
            return getLeftWeight() + getRightWeight();
        }

        public int getLeftWeight() {
            return wLeft == 0 ? leftChild.getTotalWeight() : wLeft;
        }

        public int getRightWeight() {
            return wRight == 0 ? rightChild.getTotalWeight() : wRight;
        }

        public boolean isBalanced() {
            if (wLeft != 0 && wRight != 0) { //no children
                return (wLeft * dLeft == wRight * dRight);
            } else if (wLeft == 0 && wRight == 0) { //2 children
                if (!leftChild.isBalanced() || !rightChild.isBalanced()) {
                    return false;
                } else {
                    return (leftChild.getTotalWeight() * dLeft == rightChild.getTotalWeight() * dRight);
                }
            } else if (wLeft == 0) { //one left child
                if (!leftChild.isBalanced()) {
                    return false;
                } else {
                    return (leftChild.getTotalWeight() * dLeft == wRight * dRight);
                }
            } else if (wRight == 0) { //one right child
                if (!rightChild.isBalanced()) {
                    return false;
                } else {
                    return (wLeft * dLeft == rightChild.getTotalWeight() * dRight);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Main prog = new Main();
        prog.main();
    }
}