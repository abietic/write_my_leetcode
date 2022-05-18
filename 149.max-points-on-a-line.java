import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=149 lang=java
 *
 * [149] Max Points on a Line
 */

// @lc code=start
class Solution {
    // 给出的点没有重合的

    // 暴力O(n^3)，先两点确定一条直线，再看直线上有多少个点
    // public int maxPoints(int[][] points) {
    // final int n = points.length;
    // if (n <= 2) {
    // return n;
    // }
    // int maxPoints = 2;
    // for (int i = 0; i < n; ++i) {
    // for (int j = i + 1; j < n; ++j) {
    // final int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 =
    // points[j][1];
    // int pointCount = 0;
    // for (int k = 0; k < n; ++k) {
    // final int x3 = points[k][0], y3 = points[k][1];
    // if ((x1-x3)*(y1-y2)==(x1-x2)*(y1-y3)) {
    // pointCount++;
    // }
    // }
    // maxPoints = Math.max(maxPoints, pointCount);
    // }
    // }
    // return maxPoints;
    // }

    // public int maxPoints(int[][] points) {
    // // 由于两点构成一条直线，因此在同一直线上任取两点x轴之差和y轴之差的比值是相同的（排除分母为0的特殊情况）
    // // 因此，以一个原点作为基准点，其他点与基准点做x轴y轴差值的比值，比值相等代表共线
    // // 比值是一个有理数，要考虑如何辨别
    // if (points.length <= 2) {
    // return points.length;
    // }
    // // 这里偷懒直接使用浮点数表示有理数，可能出现问题
    // // 可以使用问题166的解来用字符串表示有理数，这样就能保证准确性，同时用字符串"INF"代表无穷大即可表示垂直于x轴的线
    // Map<Double, Integer> count = new HashMap<>();
    // int res = 2;
    // for (int i = 0; i < points.length - 1; ++i) {
    // for (int j = i + 1; j < points.length; ++j) {
    // Double deltaX = (double)(points[i][0] - points[j][0]), deltaY =
    // (double)(points[i][1] - points[j][1]), slope;
    // slope = deltaX.intValue() == 0 ? Double.POSITIVE_INFINITY : deltaY / deltaX;
    // // 这个除法可能会出现正零和负零在接下来的统计中不同的问题
    // if (slope == 0.0 || slope == -0.0) {
    // slope = 0.0;
    // }
    // count.put(slope, count.getOrDefault(slope, 0) + 1);
    // }
    // res = Math.max(res, count.values().stream().max((a,b)->{return a - b;}).get()
    // + 1);
    // count.clear();
    // }
    // return res;
    // }

    private static final class Slope {
        public final int x, y;

        public Slope(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Slope other = (Slope) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

    }

    private static int shabbyGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // 这种性能反而不高
    public int maxPoints(int[][] points) {
        final int n = points.length;
        if (n <= 2) {
            return n;
        }
        int res = 2;
        // 规定slope的x值一定为正，以方便定位
        Map<Slope, Integer> couting = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            int vertical = 1, horizontal = 1;
            for (int j = i + 1; j < n; ++j) {
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                if (x == 0) {
                    vertical++;
                    continue;
                }
                if (y == 0) {
                    horizontal++;
                    continue;
                }
                int gcd = shabbyGCD(x, y);
                x /= gcd;
                y /= gcd;
                if (x < 0) {
                    x = -x;
                    y = -y;
                }
                Slope key = new Slope(x, y);
                couting.put(key, couting.getOrDefault(key, 1) + 1);
            }
            res = Math.max(res, Math.max(vertical, Math.max(horizontal, couting.values().stream().max((a, b)->{return a - b;}).orElse(2))));
            couting.clear();
        }
        return res;
    }
}
// @lc code=end
