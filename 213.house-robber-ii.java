/*
 * @lc app=leetcode id=213 lang=java
 *
 * [213] House Robber II
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 2) {
            return nums.length == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }
        // 与之前的区别是，首位是否相邻
        // 那么最直观的想法是，计算一定选择第一个，一定选择最后一个，和一定两个都不选中的最大值
        int[] definitFirst = new int[nums.length];
        definitFirst[1] = nums[0];
        definitFirst[2] = nums[0];
        for (int len = 3; len < nums.length; ++ len) {
            definitFirst[len] = Math.max(definitFirst[len - 2] + nums[len - 1], definitFirst[len - 1]);
        }
        final int definitFirstMax = definitFirst[nums.length - 1];
        int[] definitLast = new int[nums.length];
        definitLast[1] = nums[nums.length - 1];
        definitLast[2] = nums[nums.length - 1];
        for (int len = 3, idx = nums.length - 3; len < nums.length; ++len, --idx) {
            definitLast[len] = Math.max(definitLast[len - 2] + nums[idx], definitLast[len - 1]);
        }
        final int definitLastMax = definitLast[nums.length - 1];
        int[] bothNotChosen = new int[nums.length];
        for (int len = 2; len < nums.length; ++len) {
            bothNotChosen[len] = Math.max(bothNotChosen[len - 2] + nums[len - 1], bothNotChosen[len - 1]);
        }
        final int bothNotChosenMax = bothNotChosen[nums.length - 1];
        return Math.max(bothNotChosenMax, Math.max(definitFirstMax, definitLastMax));
    }
}
// @lc code=end

