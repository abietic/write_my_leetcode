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
                // 匹配了状态前进
                cur++;
            } else {
                // 没匹配，使用已经生成的fail回退并尝试匹配
                while (cur != 0 && needle.charAt(cur) != needle.charAt(i)) {
                    cur = fail[cur];
                }
                if (needle.charAt(cur) == needle.charAt(i)) {
                    cur++;
                }
            }
        }
        // for (int i = 1; i < pn; ++i) {
        //     fail[i] = cur;
        //     if (needle.charAt(cur) == needle.charAt(i)) {
        //         cur++;
        //     } else {
        //         cur = fail[cur];
        //     }
        // }
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

    // public int strStr(String haystack, String needle) {
    // // 暴力求pattern数组O(m^2)
    // final int nl = needle.length();
    // if (nl == 0) {
    // return 0;
    // }
    // int[] fail = new int[nl];
    // byte[] p = needle.getBytes();
    // for (int i = 1; i < nl; ++i) {
    // for (int j = 1; j < i; ++j) {
    // boolean matched = true;
    // for (int k = j, idx = 0; k < i; ++k, ++idx) {
    // if (p[idx] != p[k]) {
    // matched = false;
    // break;
    // }
    // }
    // if (matched) {
    // fail[i] = i - j;
    // break;
    // }
    // }
    // }
    // int cur = 0;
    // for (int i = 0; i < haystack.length(); ++i) {
    // if (haystack.charAt(i) == needle.charAt(cur)) {
    // cur++;
    // if (cur == needle.length()) {
    // return i - needle.length() + 1;
    // }
    // } else if (cur != 0){
    // cur = fail[cur];
    // --i;
    // }
    // }
    // return -1;
    // }
}
// @lc code=end
