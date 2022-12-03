/*
 * @lc app=leetcode id=238 lang=java
 *
 * [238] Product of Array Except Self
 */

// @lc code=start
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // 好像剑指offer有原题
        int[] res = new int[nums.length];
        int help = 1;
        for (int i = 0; i < nums.length; ++i) {
            help *= nums[i];
            res[i] = help;
        }
        help = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            res[i] = (i - 1 < 0 ? 1 : res[i - 1]) * help;
            help *= nums[i];
        }
        return res;
    }
}
// @lc code=end

