import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem nr 10078 - The Art Gallery
 * Created by Kristofer Svensson on 2018-01-02.
 **/

public class Main {

    private LinkedList<Point> points;
    private LinkedList<Vector> vectors;

    public static void main(String[] args) {
        Main prog = new Main();
        prog.start();
    }

    public int crossProduct(Vector v1, Vector v2) {
        int product = (v1.x * v2.y) - (v1.y * v2.x);
        if (product > 0) {
            return 1;
        } else if (product == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        points = new LinkedList<>();
        vectors = new LinkedList<>();
        int n = scanner.nextInt();
        int firstTurn;
        boolean convex;
        while (n != 0) {
            Point firstPoint = null;
            points.clear();
            vectors.clear();
            convex = true;
            for (int i = 0; i < n; i++) {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
                if (firstPoint == null) {
                    firstPoint = points.getFirst();
                }
            }
            int nbrOfPoints = points.size();
            for (int i = 1; i < nbrOfPoints; i++) {
                Point c1 = points.removeFirst();
                Point c2 = points.getFirst();
                vectors.add(new Vector(c1, c2));
            }
            Vector firstVector = vectors.getFirst();
            Vector lastVector = new Vector(points.getLast(), firstPoint);
            vectors.addLast(lastVector);
            Vector vector1st = vectors.removeFirst();
            Vector vector2nd = vectors.getFirst();
            firstTurn = crossProduct(vector1st, vector2nd);
            int nbrOfVectors = vectors.size();
            for (int i = 0; i < nbrOfVectors; i++) {
                if (vectors.size() >= 2) {
                    Vector v1 = vectors.removeFirst();
                    Vector v2 = vectors.getFirst();
                    int crossProduct = crossProduct(v1, v2);
                    if (crossProduct != firstTurn) {
                        if (crossProduct == 0) {
                            continue;
                        } else {
                            convex = false;
                            break;
                        }
                    }
                }
            }
            int lastTurn = crossProduct(vectors.getLast(), firstVector);
            if (lastTurn != firstTurn) {
                if (lastTurn != 0) {
                    convex = false;
                }
            }
            if (convex) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
            n = scanner.nextInt();
        }
    }

    private class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Vector {
        int x, y;

        public Vector(Point c1, Point c2) {
            this.x = c2.x - c1.x;
            this.y = c2.y - c1.y;
        }
    }
}
