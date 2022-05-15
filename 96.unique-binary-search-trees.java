/*
 * @lc app=leetcode id=96 lang=java
 *
 * [96] Unique Binary Search Trees
 */

// @lc code=start
class Solution {
    public int numTrees(int n) {
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                memo[i] += memo[j] * memo[i - j - 1];
            }
        }
        return memo[n];
    }
}
// @lc code=end

