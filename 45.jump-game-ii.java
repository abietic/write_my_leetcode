/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        final int n = nums.length;
        int[] memo = new int[n + 1];
        for (int i = n - 2; i >= 0; --i) {
            // 因为下面比较时有加一操作，不能直接用max
            memo[i] = Integer.MAX_VALUE - 1;
            for (int j = 0; j < nums[i] && i + 1 + j < n; ++j) {
                memo[i] = Math.min(memo[i], memo[i + 1 + j] + 1);
            }
        }
        return memo[0];
    }
}
// @lc code=end

