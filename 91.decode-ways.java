/*
 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        memo[0] = 1;
        if (s.charAt(s.length() - 1) == '0') {
            memo[1] = 0;
        } else {
            memo[1] = 1;
        }
        // 这里从右往左找
        for (int i = 2; i <= s.length(); ++i) {
            int curIndex = s.length() - i, prevIndex = s.length() - i + 1;
            char curChar = s.charAt(curIndex), prevChar = s.charAt(prevIndex);
            if (curChar == '0') {
                memo[i] = 0;
            } else if (curChar == '1') {
                memo[i] = memo[i - 1] + memo[i - 2];
            } else if (curChar == '2' && prevChar <= '6') {
                memo[i] = memo[i - 1] + memo[i - 2];
            } else {
                memo[i] = memo[i - 1];
            }
        }
        return memo[s.length()];
    }
}
// @lc code=end
