/*
 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 */

// @lc code=start
class Solution {
    // // 简单的dp思路可以很直观的解决，但是需要O(n)的额外空间复杂度
    // public int rob(int[] nums) {
    // // 当n_x被选择时，接下来的可选项为n_{x+2}或n_{x+3}
    // // f(len) = max{x + f(len - 2), f(len - 1)}, f(0) = 0
    // final int n = nums.length;
    // if (n == 1) {
    // return nums[0];
    // }
    // int[] memo = new int[n + 1];
    // memo[1] = nums[0];
    // for (int len = 2; len <= n; ++len) {
    // memo[len] = Math.max(nums[len - 1] + memo[len - 2], memo[len - 1]);
    // }
    // return memo[n];
    // }

    public int rob(int[] nums) {
        // 是否可以使用贪心的思想解决这个问题呢？
        // 假设从左往右窃取，已经截取了在位置x上的人家，接下来有两种选择
        // 选择x+2或是选择x+3，因为如果选择更往后的人家，就会出现有的人家明明可以偷，却没偷，造成不必要的损失
        // 如果选择了x+2，接下来可以选择的是x+4, x+5
        // 如果选择了x+3，接下来可以选择的是x+5, x+6
        int cur = -2, sum = 0;
        for (; cur < nums.length;) {
            
        }
    }
}
// @lc code=end
