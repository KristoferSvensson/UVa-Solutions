import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem nr 10307 - Killing Aliens in Borg Maze
 * Created by Kristofer Svensson on 2017-10-18.
 */
public class Main {

    public static final int WALL = 0, SPACE = 1, ALIEN = 2, START = 3;
    public Node[][] maze;
    public Node startNode;
    public int nbrOfRows, slotsPerRow;
    private LinkedList<Edge> edges;
    private LinkedList<Node> aliens;

    public void bfs(Node start) {
        for (int r = 0; r < nbrOfRows; r++) {
            for (int s = 0; s < slotsPerRow; s++) {
                maze[r][s].visited = false;
            }
        }
        start.level = 0;
        LinkedList<Node> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            //up
            Node up = maze[n.y - 1][n.x];
            if (up.type != WALL && !up.visited) {
                up.visited = true;
                queue.add(up);
                up.level = n.level + 1;
                if (up.type == ALIEN) {
                    edges.add(new Edge(start, up, up.level));
                }
            }
            //right
            Node right = maze[n.y][n.x + 1];
            if (right.type != WALL && !right.visited) {
                right.visited = true;
                queue.add(right);
                right.level = n.level + 1;
                if (right.type == ALIEN) {
                    edges.add(new Edge(start, right, right.level));
                }
            }
            //down
            Node down = maze[n.y + 1][n.x];
            if (down.type != WALL && !down.visited) {
                down.visited = true;
                queue.add(down);
                down.level = n.level + 1;
                if (down.type == ALIEN) {
                    edges.add(new Edge(start, down, down.level));
                }
            }
            //left
            Node left = maze[n.y][n.x - 1];
            if (left.type != WALL && !left.visited) {
                left.visited = true;
                queue.add(left);
                left.level = n.level + 1;
                if (left.type == ALIEN) {
                    edges.add(new Edge(start, left, left.level));
                }
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            edges = new LinkedList<>();
            aliens = new LinkedList<>();
            int counter = 0;
            slotsPerRow = scanner.nextInt();
            nbrOfRows = scanner.nextInt();
            maze = new Node[nbrOfRows][slotsPerRow];
            scanner.nextLine();
            for (int r = 0; r < nbrOfRows; r++) {
                String line = scanner.nextLine();
                if (r == 0 || r == nbrOfRows - 1) {
                    for (int a = 0; a < slotsPerRow - 5; a++) {
                        line += "#";
                    }
                }
                char symbols[] = line.toCharArray();
                for (int s = 0; s < slotsPerRow; s++) {
                    switch (symbols[s]) {
                        case '#':
                            maze[r][s] = new Node(WALL, -1, r, s);
                            break;
                        case ' ':
                            maze[r][s] = new Node(SPACE, counter, r, s);
                            counter++;
                            break;
                        case 'A':
                            maze[r][s] = new Node(ALIEN, counter, r, s);
                            aliens.add(maze[r][s]);
                            counter++;
                            break;
                        case 'S':
                            maze[r][s] = new Node(START, counter, r, s);
                            startNode = maze[r][s];
                            counter++;
                            break;
                        default:
                            break;
                    }
                }
            }
            bfs(startNode);
            for (Node alien : aliens) {
                bfs(alien);
            }
            Collections.sort(edges, new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                    return e1.weight < e2.weight ? -1 : e1.weight == e2.weight ? 0 : 1;
                }
            });
            for (Edge n : edges) {
                if (n.from.parent == null) {
                    makeSet(n.from);
                }
            }
            int steps = 0;
            for (Edge n : edges) {
                if (union(n.from, n.to)) {
                    steps += n.weight;
                }
            }
            System.out.println(steps);
        }
    }

    public static void main(String[] args) {
        Main prog = new Main();
        prog.start();
    }

    public void makeSet(Node node) {
        node.parent = node;
        node.rank = 0;
    }

    public Node find(Node node) {
        if (node.parent == node) {
            return node;
        }
        node.parent = find(node.parent);
        return node.parent;
    }

    public boolean union(Node data1, Node data2) {
        Node parent1 = find(data1);
        Node parent2 = find(data2);
        if (parent1.number == parent2.number) {
            return false;
        }
        if (parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    private class Node {

        public int y, x, level, rank;
        public int type, number;
        public boolean visited;
        public Node parent;

        public Node(int type, int number, int y, int x) {
            this.type = type;
            this.number = number;
            this.y = y;
            this.x = x;
            this.level = 0;
        }
    }

    private class Edge {
        public int weight;
        public Node from, to;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
