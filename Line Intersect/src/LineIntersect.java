import java.util.*;

public class LineIntersect {

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point other) {
            return Integer.compare(this.x, other.x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numInstances = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numInstances; i++) {
            int n = Integer.parseInt(sc.nextLine());
            Point[] top = new Point[n];
            Point[] bottom = new Point[n];
            for (int j = 0; j < n; j++) {
                top[j] = new Point(Integer.parseInt(sc.nextLine()), 1);
            }
            for (int j = 0; j < n; j++) {
                bottom[j] = new Point(Integer.parseInt(sc.nextLine()), 0);
            }
            long count = countIntersect(top, bottom);
            System.out.println(count);
        }
    }

    public static int countIntersect(Point[] top, Point[] bottom) {
        int n = top.length;
        Point[] merged = new Point[2 * n];
        System.arraycopy(top, 0, merged, 0, n);
        System.arraycopy(bottom, 0, merged, n, n);
        Arrays.sort(merged);
        return countIntersectHelper(merged, 0, n - 1);
    }

    private static int countIntersectHelper(Point[] points, int low, int high) {
        if (low >= high) return 0;
        int mid = low + (high - low) / 2;
        int count = countIntersectHelper(points, low, mid) +
                countIntersectHelper(points, mid + 1, high) +
                countCrossingIntersect(points, low, mid, high);
        mergeSortedHalves(points, low, mid, high);
        return count;
    }

    private static int countCrossingIntersect(Point[] points, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int count = 0;
        while (i <= mid && j <= high) {
            if (points[i].y > points[j].y) {
                count += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }
        return count;
    }

    private static void mergeSortedHalves(Point[] points, int low, int mid, int high) {
        Point[] merged = new Point[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (points[i].compareTo(points[j]) <= 0) {
                merged[k++] = points[i++];
            } else {
                merged[k++] = points[j++];
            }
        }
        while (i <= mid) {
            merged[k++] = points[i++];
        }
        while (j <= high) {
            merged[k++] = points[j++];
        }
    }
}
