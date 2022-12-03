import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 1;
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> memo = new HashMap<>(52);
        int lastStart = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (memo.containsKey(c)) {
                int lastLen = i - lastStart;
                res = Math.max(res, lastLen);
                int newStart = memo.get(c) + 1;
                for (; lastStart < newStart; ++lastStart) {
                    memo.remove(s.charAt(lastStart));
                }
            }
            memo.put(c, i);
        }
        return Math.max(res, memo.size());
    }
}
// @lc code=end
