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
        // 从上面的方法可以看出，其实不需要这么多备忘空间，只需要知道前两个的情况即可
        int lenMinus1Max = nums[0], lenMinus2Max = 0, curLenMax = nums[0];
        for (int len = 2; len <= nums.length; ++len) {
            curLenMax = Math.max(lenMinus2Max + nums[len - 1], lenMinus1Max);
            lenMinus2Max = lenMinus1Max;
            lenMinus1Max = curLenMax;
        }
        return curLenMax;
    }
}
// @lc code=end
