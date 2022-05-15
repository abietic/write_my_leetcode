import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 */

// @lc code=start
class Solution {
    // 暴力，超时
    // public boolean wordBreak(String s, List<String> wordDict) {
    // if (s.isEmpty()) {
    // return true;
    // }
    // for (String pat : wordDict) {
    // if (s.startsWith(pat)) {
    // if (wordBreak(s.substring(pat.length()), wordDict)) {
    // return true;
    // }
    // }
    // }
    // return false;
    // }
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将字典中单词由短到长排序
        Collections.sort(wordDict, (a, b)->{return a.length() - b.length();});
        // 当前子字符串长度下是否可以满足要求
        boolean[] memo = new boolean[s.length() + 1];
        memo[0] = true;
        // 检查的子字符串不断长，前缀匹配后，剩下的子字符串是否还满足条件
        for (int i = s.length() - 1, len = 1; i >= 0; --i, ++len) {
            for (String pat : wordDict) {
                if (pat.length() > len) {
                    break;
                }
                if (s.startsWith(pat, i)) {
                    if (memo[len - pat.length()]) {
                        memo[len] = true;
                        break;
                    }
                }
            }
        }
        return memo[s.length()];
    }
}
// @lc code=end
