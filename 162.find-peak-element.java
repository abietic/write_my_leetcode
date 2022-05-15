/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    // 在这里的限制条件中，保证了两个相邻元素不相等（除-1和n），这保证了一定会有解（应该还有文章做）
    // 用暴力方法，O(n)
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            long left = 0, right = 0, cur = nums[i];
            if (i == 0) {
                left = Long.MIN_VALUE;
            } else {
                left = nums[i - 1];
            }
            if (i == nums.length - 1) {
                right = Long.MIN_VALUE;
            } else {
                right = nums[i + 1];
            }
            if (cur > left && cur > right) {
                return i;
            }
        }
        return nums.length;
    }
}
// @lc code=end

