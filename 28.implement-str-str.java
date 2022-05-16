/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 */

// @lc code=start
class Solution {
    public int strStr(String haystack, String needle) {
        // 只包含26种小写字母
        final int pn = needle.length();
        if (pn == 0) {
            return 0;
        }
        // 如果在这个索引上匹配失败了，那再次匹配时应该从哪个索引开始匹配
        int[] fail = new int[pn];
        int cur = 0;
        for (int i = 1; i < pn; ++i) {
            fail[i] = cur;
            if (needle.charAt(cur) == needle.charAt(i)) {
                cur++;
            } else {
                cur = fail[cur];
            }
        }
        cur = 0;
        for (int i = 0; i < haystack.length(); ++i) {
            if (needle.charAt(cur) == haystack.charAt(i)) {
                cur++;
                if (cur == pn) {
                    return i - pn + 1;
                }
            } else if (cur != 0) {
                --i;
                cur = fail[cur];
            }
        }
        return -1;
    }
}
// @lc code=end
