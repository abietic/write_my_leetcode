import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=64 lang=java
 *
 * [64] Minimum Path Sum
 */

// @lc code=start
class Solution {
    // private static final class Axis {
    //     public final int row, col;

    //     public Axis(int row, int col) {
    //         this.row = row;
    //         this.col = col;
    //     }

    //     @Override
    //     public int hashCode() {
    //         final int prime = 31;
    //         int result = 1;
    //         result = prime * result + col;
    //         result = prime * result + row;
    //         return result;
    //     }

    //     @Override
    //     public boolean equals(Object obj) {
    //         if (this == obj)
    //             return true;
    //         if (obj == null)
    //             return false;
    //         if (getClass() != obj.getClass())
    //             return false;
    //         Axis other = (Axis) obj;
    //         if (col != other.col)
    //             return false;
    //         if (row != other.row)
    //             return false;
    //         return true;
    //     }
        

    // }
    // private Map<Axis, Integer> memo;
    // private int minPathSum(int row, int col, int[][] grid, int m, int n){
    //     Axis axis = new Axis(row, col);
    //     if (memo.containsKey(axis)) {
    //         return memo.get(axis);
    //     }
    //     int sum = 0;
    //     if (row == m - 1) {
    //         for (int i = col; i < n; ++i) {
    //             sum += grid[m - 1][i];
    //         }
    //         memo.put(axis, sum);
    //         return sum;
    //     }
    //     if (col == n - 1) {
    //         for (int i = row; i < m; ++i) {
    //             sum += grid[i][n - 1];
    //         }
    //         memo.put(axis, sum);
    //         return sum;
    //     }
    //     int down = minPathSum(row + 1, col, grid, m, n), right = minPathSum(row, col + 1, grid, m, n);
    //     sum = down > right ? grid[row][col] + right : grid[row][col] + down;
    //     memo.put(axis, sum);
    //     return sum;
    // }
    // public int minPathSum(int[][] grid) {
    //     this.memo = new HashMap<>();
    //     return minPathSum(0, 0, grid, grid.length, grid[0].length);
    // }

    public int minPathSum(int[][] grid) {
        // 尝试用迭代dp
        final int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        memo[0][0] = grid[0][0];
        for (int i = 1; i < m; ++i) {
            memo[i][0] = grid[i][0] + memo[i - 1][0];
        }
        for (int i = 1; i < n; ++i) {
            memo[0][i] = grid[0][i] + memo[0][i - 1];
        }
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                memo[row][col] = grid[row][col] + Math.min(memo[row - 1][col], memo[row][col - 1]);
            }
        }
        return memo[m - 1][n - 1];
    }
}
// @lc code=end

