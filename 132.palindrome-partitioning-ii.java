/*
 * @lc app=leetcode id=132 lang=java
 *
 * [132] Palindrome Partitioning II
 */

// @lc code=start
class Solution {
    // 核心是回文左右两边去掉相同数量的字符仍是回文
    // 意味着回文左右两边各加一个相同字符也是回文


    // 最直观的想法，立方复杂度，超时
    // private static boolean _isPalindrome(int i, int j, char[] s, int[][] memo) {
    //     if (memo[i + 1][j - 1] == 0 && s[i] == s[j - 1]) {
    //         return true;
    //     }
    //     return false;
    // }
    // public int minCut(String s) {
    //     final int n = s.length();
    //     final char[] sc = s.toCharArray();
    //     int[][] memo = new int[n + 1][n + 1];
    //     // f(i, j) = if s[i:j] is palindrome {0} else {min(f(i,x) + f(x,j)) + 1}
    //     for (int len = 2; len <= n; ++len) {
    //         for (int i = 0; i <= n - len; ++ i) {
    //             final int j = i + len;
    //             if (_isPalindrome(i, j, sc, memo)) {
    //                 memo[i][j] = 0;
    //             } else {
    //                 int minCutSum = len - 2;
    //                 for (int x = i + 1; x < j; ++x) {
    //                     minCutSum = Math.min(minCutSum, memo[i][x] + memo[x][j]);
    //                 }
    //                 memo[i][j] = minCutSum + 1;
    //             }
    //         }
    //     }
    //     return memo[0][n];
    // }

    // 一个字符串加入一个字符，要么与右侧构成回文，变成1+(删去回文部分剩余的字符串的最小切分数)
    // 要么与右侧无法构成回文，变成1+
    public int minCut(String s) {
        final int n = s.length();
        final char[] cs = s.toCharArray();
        final int[] memo = new int[n + 1];
        final boolean[][] isPalindrome = new boolean[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            isPalindrome[i][i] = isPalindrome[i][i + 1] = true;
        }
        for (int len = 2; len <= n; ++len) {
            // 新加字符位置
            final int addedCharIndex = n - len;
            // 最差切分数，每个字符都切出来
            int minCutSum = len - 1;
            for (int j = n - 1; j > addedCharIndex; --j) {
                if (cs[addedCharIndex] == cs[j] && isPalindrome[addedCharIndex + 1][j]) {
                    if (j == n - 1) {
                        // 整个字符串都变成回文，不需要切分
                        minCutSum = 0;
                    }
                    // 与新加字符有构成回文的部分
                    // 更新已有回文记录
                    isPalindrome[addedCharIndex][j + 1] = true;
                    // 这时的切分数为 1+(删去回文部分后剩余字符串的最小切分数)
                    minCutSum = Math.min(minCutSum, 1 + memo[n - j - 1]);
                }
            }
            // 或者新加的字符无法与右侧字符串构成回文将其切分并加上之前的最小切分数
            minCutSum = Math.min(minCutSum, 1 + memo[len - 1]);
            memo[len] = minCutSum;
        }
        return memo[n];
    }
}
// @lc code=end

