import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 */

// @lc code=start
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Collections.sort(wordDict, (a, b) -> {
            return a.length() - b.length();
        });
        List<String>[] memo = new List[s.length() + 1];
        memo[0] = new ArrayList<>();
        memo[0].add("");
        for (int i = s.length() - 1, len = 1; i >= 0; --i, ++len) {
            List<String> neo = new ArrayList<>();
            for (String pat : wordDict) {
                if (pat.length() > len) {
                    break;
                }
                if (s.startsWith(pat, i)) {
                    int remainLen = len - pat.length();
                    if (!memo[remainLen].isEmpty()) {
                        for (String subRes : memo[remainLen]) {
                            String neoSubRes = pat + ' ' +subRes;
                            if (subRes.isEmpty()) {
                                neoSubRes = pat;
                            }
                            neo.add(neoSubRes);
                        }
                    }
                }
            }
            memo[len] = neo;
        }
        return memo[s.length()];
    }
}
// @lc code=end
