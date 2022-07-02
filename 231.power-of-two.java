/*
 * @lc app=leetcode id=231 lang=java
 *
 * [231] Power of Two
 */

// @lc code=start
class Solution {
    public boolean isPowerOfTwo(int n) {
        // 排除一定不可行的情况
        if (n <= 0) {
            return false;
        }
        // 如果整数的二进制表示只有一个1bit位，成立
        if ((n & (n - 1)) == 0) {
            return true;
        }
        // 否则不成立
        return false;
    }
}
// @lc code=end

