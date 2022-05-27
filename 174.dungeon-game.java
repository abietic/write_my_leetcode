/*
 * @lc app=leetcode id=174 lang=java
 *
 * [174] Dungeon Game
 */

// @lc code=start
class Solution {
    // 错了
    public int calculateMinimumHP(int[][] dungeon) {
        // 由于是保证整条路径上没有出现累加后小于等于0的情况
        // 因此要记录路径累加的最小情况，在所有路径中再选那个路径最小情况最大的那个
        final int m = dungeon.length, n = dungeon[0].length;
        int[][] memo = new int[m][n], memoCur = new int[m][n];
        int cur = 0, curMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            cur += dungeon[0][i];
            curMin = Math.min(curMin, cur);
            memo[0][i] = curMin;
            memoCur[0][i] = cur;
        }
        cur = 0;
        curMin = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            cur += dungeon[i][0];
            curMin = Math.min(curMin, cur);
            memo[i][0] = curMin;
            memoCur[i][0] = cur;
        }
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                int leftCur = memoCur[row][col - 1] + dungeon[row][col], upCur = memoCur[row - 1][col] + dungeon[row][col];
                int leftMin = Math.min(memo[row][col - 1], leftCur), upMin = Math.min(memo[row - 1][col], upCur);
                if (leftMin > upMin) {
                    memo[row][col] = leftMin;
                    memoCur[row][col] = leftCur;
                } else if (leftMin < upMin) {
                    memo[row][col] = upMin;
                    memoCur[row][col] = upCur;
                } else {
                    memo[row][col] = upMin;
                    memoCur[row][col] = Math.max(leftCur, upCur);
                }
            }
        }
        return memo[m - 1][n - 1] >= 0 ? 1 : -memo[m - 1][n - 1] + 1;
    }
}
// @lc code=end

