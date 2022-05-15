/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 */

// @lc code=start
class Solution {
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length() + 1][t.length() + 1];
        // s有剩余但是t没剩余时，只要删除s的所有剩余，就是一个合法的匹配了
        for (int i = 0; i <= s.length(); ++i) {
            memo[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {
                memo[i][j] = memo[i - 1][j];
                if (s.charAt(s.length() - i) == t.charAt(t.length() - j)) {
                    // 如果相同，有匹配和删除两种选择
                    memo[i][j] += memo[i - 1][j - 1];
                }
            }
        }
        return memo[s.length()][t.length()];
    }
}
// @lc code=end

