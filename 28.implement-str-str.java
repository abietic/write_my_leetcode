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
        // 如果在这个索引上发送失败了，那再次匹配时已经匹配完的索引是多少
        int[] fail = new int[pn];
        fail[0] = -1;
        // 现在要匹配的字符对应的索引
        int cur = 0;
        for (int i = 1; i < pn; ++i) {
            if (needle.charAt(cur) == needle.charAt(i)) {
                cur += 1;
            } else {
                // 如果当前的匹配的失败了，要知道需要后退到哪种状态
                cur = fail[cur];
            }
            fail[i] = cur;
        }
    }
}
// @lc code=end

