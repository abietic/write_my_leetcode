/*
 * @lc app=leetcode id=171 lang=java
 *
 * [171] Excel Sheet Column Number
 */

// @lc code=start
class Solution {
    public int titleToNumber(String columnTitle) {
        // 和168正好互为反问题
        int sum = 0;
        for (int i = 0; i < columnTitle.length(); ++i) {
            int dig = columnTitle.charAt(i) - 'A' + 1;
            sum = sum * 26 + dig;
        }
        return sum;
    }
}
// @lc code=end

