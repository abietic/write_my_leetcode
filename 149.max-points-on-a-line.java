/*
 * @lc app=leetcode id=149 lang=java
 *
 * [149] Max Points on a Line
 */

// @lc code=start
class Solution {
    // 暴力O(n^3)，先两点确定一条直线，再看直线上有多少个点
    public int maxPoints(int[][] points) {
        final int n = points.length;
        if (n <= 2) {
            return n;
        }
        int maxPoints = 2;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                final int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int pointCount = 0;
                for (int k = 0; k < n; ++k) {
                    final int x3 = points[k][0], y3 = points[k][1];
                    if ((x1-x3)*(y1-y2)==(x1-x2)*(y1-y3)) {
                        pointCount++;
                    }
                }
                maxPoints = Math.max(maxPoints, pointCount);
            }
        }
        return maxPoints;
    }
}
// @lc code=end

