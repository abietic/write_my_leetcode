/*
 * @lc app=leetcode id=190 lang=java
 *
 * [190] Reverse Bits
 */

// @lc code=start
class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32; ++i) {
            rev = (rev << 1) | ((n & (1 << i)) != 0 ? 1 : 0);
        }
        return rev;
    }
}
// @lc code=end

