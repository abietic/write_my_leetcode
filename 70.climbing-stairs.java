/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (i - 2 >= 0) {
                memo[i] += memo[i - 2];
            }
            if (i - 1 >= 0) {
                memo[i] += memo[i - 1];
            }
        }
        return memo[n];
    }
}
// @lc code=end

