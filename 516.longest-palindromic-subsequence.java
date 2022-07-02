import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 */

// @lc code=start
class Solution {

    // private int _longestPalindromeSubseq(String s, int left, int right,
    // Integer[][] memo) {
    // if (left >= right) {
    // return (left == right ? 1 : 0);
    // }
    // Integer initLen;
    // if ((initLen = memo[left][right]) != null) {
    // return initLen;
    // }
    // initLen = 1;
    // if (s.charAt(left) == s.charAt(right)) {
    // initLen = Math.max(initLen, _longestPalindromeSubseq(s, left + 1, right - 1,
    // memo) + 2);
    // } else {
    // initLen = Math.max(initLen, _longestPalindromeSubseq(s, left + 1, right - 1,
    // memo));
    // initLen = Math.max(initLen, _longestPalindromeSubseq(s, left + 1, right,
    // memo));
    // initLen = Math.max(initLen, _longestPalindromeSubseq(s, left, right - 1,
    // memo));
    // }
    // maxLen = Math.max(maxLen, initLen);
    // memo[left][right] = initLen;
    // return initLen;
    // }

    // private static int maxLen = 1;
    // private Integer[][] memo;

    // public int longestPalindromeSubseq(String s) {
    // // 假设有一个存在的回文，它的外侧字符不再构成回文
    // // 那么有几种方式尝试继续构建回文
    // // 1. 删除一个左侧字符再尝试
    // // 2. 删除一个右侧字符再尝试
    // // 3. 同时删除两侧字符再尝试
    // maxLen = 1;
    // memo = new Integer[s.length()][s.length()];
    // _longestPalindromeSubseq(s, 0, s.length() - 1, memo);
    // return maxLen;
    // }

    // public int longestPalindromeSubseq(String s) {
    //     // 假设有一个存在的回文，它的外侧字符不再构成回文
    //     // 那么有几种方式尝试继续构建回文
    //     // 1. 删除一个左侧字符再尝试
    //     // 2. 删除一个右侧字符再尝试
    //     // 3. 同时删除两侧字符再尝试
    //     int[][] memo = new int[s.length()][s.length()];
    //     for (int i = 0; i < s.length(); ++i) {
    //         memo[i][i] = 1;
    //     }
    //     for (int span = 2; span <= s.length(); ++span) {
    //         for (int start = 0; start + span <= s.length(); ++start) {
    //             int left = start, right = start + span - 1, len = 1;
    //             if (s.charAt(left) == s.charAt(right)) {
    //                 len = Math.max(len, (span == 2 ? 0 : memo[left + 1][right - 1]) + 2);
    //             } else {
    //                 // len = Math.max(len, (span == 2 ? 0 : memo[left + 1][right - 1]));
    //                 len = Math.max(len, memo[left + 1][right]);
    //                 len = Math.max(len, memo[left][right - 1]);
    //             }
    //             memo[left][right] = len;
    //         }
    //     }
    //     return memo[0][s.length() - 1];
    // }

    public int longestPalindromeSubseq(String s) {
        int[][] memo = new int[3][s.length()];
        Arrays.fill(memo[0], 1);
        int cur = 1, prev = 0, prevPrev = -1;
        for (int span = 2; span <= s.length(); ++span) {
            for (int start = 0; start + span <= s.length(); ++start) {
                int left = start, right = start + span - 1, len = 1;
                if (s.charAt(left) == s.charAt(right)) {
                    len = (span == 2 ? 0 : memo[prevPrev][start + 1]) + 2;
                } else {
                    len = Math.max(memo[prev][start], memo[prev][start + 1]);
                }
                memo[cur][start] = len;
            }
            prevPrev = prev;
            prev = cur;
            cur = (cur + 1) % 3;
        }
        return memo[prev][0];
    }
}
// @lc code=end
