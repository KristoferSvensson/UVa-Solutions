import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Problem nr 10608 - Friends
 * Created by Kristofer Svensson on 2017-10-12.
 */
public class Main {

    private Map<Integer, Node> citizens = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main prog = new Main();
        prog.run();
    }

    private void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int testCases, population, pairs;
        testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            population = scanner.nextInt();
            pairs = scanner.nextInt();
            for (int p = 0; p <= population; p++) {
                makeSet(p);
            }
            for (int j = 0; j < pairs; j++) {
                union(scanner.nextInt(), scanner.nextInt());
            }
            System.out.println(findBiggestSetSize());
        }
    }

    private class Node {

        private int data, rank, children;
        private Node parent;

        private Node(int data) {
            this.data = data;
            this.children = 1;
        }
    }

    public void makeSet(int data) {
        Node node = new Node(data);
        node.parent = node;
        node.rank = 0;
        citizens.put(data, node);
    }

    public Node find(Node node) {
        if (node.parent == node) {
            return node;
        }
        node.parent = find(node.parent);
        return node.parent;
    }

    public boolean union(int data1, int data2) {
        Node parent1 = find(citizens.get(data1));
        Node parent2 = find(citizens.get(data2));
        if (parent1.data == parent2.data) {
            return false;
        }
        if (parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
            parent1.children += parent2.children;
        } else {
            parent1.parent = parent2;
            parent2.children += parent1.children;
        }
        return true;
    }

    public int findBiggestSetSize() {
        int biggest = 0;
        for (int i = 0; i < citizens.size(); i++) {
            biggest = (citizens.get(i).children > biggest) ? citizens.get(i).children : biggest;
        }
        return biggest;
    }
}