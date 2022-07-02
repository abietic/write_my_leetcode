/*
 * @lc app=leetcode id=174 lang=java
 *
 * [174] Dungeon Game
 */

// @lc code=start
class Solution {
    // 错了
    public int calculateMinimumHP(int[][] dungeon) {
        final int m = dungeon.length, n = dungeon[0].length;
        // 如果从该节点进入，骑士所需的最小初始生命值
        int[][] memo = new int[m][n];
        memo[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? 1 - dungeon[m - 1][n - 1] : 1/* 进入节点之前一定要是正数，因此终点为正数时为1即可 */;
        for (int row = m - 2; row >= 0; --row) {
            memo[row][n - 1] = memo[row + 1][n - 1] - dungeon[row][n - 1];
            if (memo[row][n - 1] <= 0) {
                memo[row][n - 1] = 1;
            }
        }
        for (int col = n - 2; col >= 0; --col) {
            memo[m - 1][col] = memo[m - 1][col + 1] - dungeon[m - 1][col];
            if (memo[m - 1][col] <= 0) {
                memo[m - 1][col] = 1;
            }
        }
        for (int row = m - 2; row >= 0; --row) {
            for (int col = n - 2; col >= 0; --col) {
                memo[row][col] = Math.min(memo[row + 1][col], memo[row][col + 1]) - dungeon[row][col];
                if (memo[row][col] <= 0) {
                    memo[row][col] = 1;
                }
            }
        }
        return memo[0][0];
    }
}
// @lc code=end

