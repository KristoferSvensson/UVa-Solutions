import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem nr 920 - Sunny Mountains
 * Created by Kristofer Svensson on 2018-01-11.
 */

public class Main {

    public static void main(String[] args) {
        Main prog = new Main();
        prog.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Point> points = new LinkedList<>();
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            points.clear();
            int pairs = scanner.nextInt();
            for (int j = 0; j < pairs; j++) {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
            Collections.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point p1, Point p2) {
                    return p1.x < p2.x ? -1 : p1.x == p2.x ? 0 : 1;
                }
            });
            int highestPoint = 0;
            double totalLength = 0;
            for (int p = points.size() - 1; p >= 0; p--) {
                if (points.get(p).y > highestPoint) {
                    double heightDifference = points.get(p).y - highestPoint;
                    highestPoint = points.get(p).y;
                    Point prevPoint = points.get(p + 1);
                    double newx = prevPoint.x - points.get(p).x;
                    double newy = points.get(p).y - prevPoint.y;
                    double angle = Math.atan((newy / newx));
                    double hypotenuse = heightDifference / Math.sin(angle);
                    totalLength += hypotenuse;
                }
            }
            System.out.println(Math.round(totalLength * 100.0) / 100.0);
        }
    }
}
