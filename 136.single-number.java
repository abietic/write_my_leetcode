/*
 * @lc app=leetcode id=136 lang=java
 *
 * [136] Single Number
 */

// @lc code=start
class Solution {
    public int singleNumber(int[] nums) {
        int unique = 0;
        for (int n : nums) {
            unique ^= n;
        }
        return unique;
    }
}
// @lc code=end

