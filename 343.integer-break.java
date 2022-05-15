/*
 * @lc app=leetcode id=343 lang=java
 *
 * [343] Integer Break
 */

// @lc code=start
class Solution {
    public int integerBreak(int n) {
        int[] memo = new int[n + 1];
        memo[2] = memo[1] = 1;
        for (int i = 3; i <= n; ++i) {
            for (int j = i - 1, k = 1; j >= 2; --j,++k) {
                int left = Math.max(j, memo[j]);
                int right = Math.max(k, memo[k]);
                memo[i] = Math.max(memo[i], left * right);
            }
        }
        return memo[n];
    }
}
// @lc code=end

