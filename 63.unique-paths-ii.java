import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=63 lang=java
 *
 * [63] Unique Paths II
 */

// @lc code=start
class Solution {
    private static final class Axis {
        public final int row, col;

        public Axis(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
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
            Axis other = (Axis) obj;
            if (col != other.col)
                return false;
            if (row != other.row)
                return false;
            return true;
        }
        

    }
    private Map<Axis, Integer> memo;
    private int uniquePathsWithObstacles(int[][] obstacleGrid, int row, int col, int m, int n) {
        Axis axis = new Axis(row, col);
        Integer tmp = this.memo.get(axis);
        if (tmp != null) {
            return tmp;
        }
        if (obstacleGrid[row][col] == 1) {
            this.memo.put(axis, 0);
            return 0;
        }
        if (row == m - 1) {
            for (int i = col; i < n; ++i) {
                if (obstacleGrid[m - 1][i] == 1) {
                    this.memo.put(axis, 0);
                    return 0;
                }
            }
            this.memo.put(axis, 1);
            return 1;
        }
        if (col == n - 1) {
            for (int i = row; i < m; ++i) {
                if (obstacleGrid[i][n - 1] == 1) {
                    this.memo.put(axis, 0);
                    return 0;
                }
            }
            this.memo.put(axis, 1);
            return 1;
        }
        int down, right;
        if (obstacleGrid[row + 1][col] == 1) {
            down = 0;
        } else {
            down = uniquePathsWithObstacles(obstacleGrid, row + 1, col, m, n);
        }
        if (obstacleGrid[row][col + 1] == 1) {
            right = 0;
        } else {
            right = uniquePathsWithObstacles(obstacleGrid, row, col + 1, m, n);
        }
        this.memo.put(axis, down + right);
        return down + right;
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.memo = new HashMap<>();
        return uniquePathsWithObstacles(obstacleGrid, 0, 0, obstacleGrid.length, obstacleGrid[0].length);
        // return 0;
    }
}
// @lc code=end

