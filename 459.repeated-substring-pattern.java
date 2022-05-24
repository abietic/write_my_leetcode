/*
 * @lc app=leetcode id=459 lang=java
 *
 * [459] Repeated Substring Pattern
 */

// @lc code=start
class Solution {
    // 暴力解法O(n^2)
    // public boolean repeatedSubstringPattern(String s) {
    // if (s.length() == 1) {
    // return false;
    // }
    // final int maxPatterLen = s.length() / 2;
    // for (int i = 1; i <= maxPatterLen; ++i) {
    // if (s.length() % i != 0) {
    // continue;
    // }
    // String pattern = s.substring(0, i);
    // boolean repeated = true;
    // for (int j = i; j < s.length(); j += i) {
    // if (!s.substring(j, j + i).equals(pattern)) {
    // repeated = false;
    // break;
    // }
    // }
    // if (repeated) {
    // return true;
    // }
    // }
    // return false;
    // }
    // 这个方法由于使用字符串搜索算法O(mn)而这里的m=n，n=2n-2
    // 所以最差执行时间可能还是O(n^2)的
    // 这个方法的思想是，如果s符合要求那么s一定是pattern重复至少两次得到的
    // 也就是两个s拼接至少有连续4个pattern
    // 这时舍弃最左和最右的pattern应该仍至少能找到一个s
    // public boolean repeatedSubstringPattern(String s) {
    // String ds = s + s;
    // return ds.substring(1, ds.length() - 1).contains(s);
    // }

    public boolean repeatedSubstringPattern(String s) {
        // kmp的fail数组记录了在当前子串匹配失败时，匹配长度回退到的匹配长度
        // 生成的方式是检索pattern串除去第一个字符到匹配失败位置的子串，计算匹配到结尾时匹配到的最长pattern子串
        // 如果字符串是由一个子串重复rp次得到的，那么它的kmp fail数组的最后一个状态必然是子串重复rp-1次时的长度
        // 这样就可以得到重复子串的单位长度，由于字符串是由重复子串重复rp次生成的，因此应该可以整除
        final int n = s.length();
        byte[] str = s.getBytes();
        int[] fail = new int[n + 1];
        int cur = 0;
        for (int i = 1; i < n; ++i) {
            fail[i] = cur;
            if (str[i] == str[cur]) {
                cur++;
            } else {
                while (cur != 0 && str[cur] != str[i]) {
                    cur = fail[cur];
                }
                if (str[cur] == str[i]) {
                    cur++;
                }
            }
        }
        fail[n] = cur;
        if (cur == 0) {
            // 没有重复子串，不符合条件
            return false;
        }
        // 重复子串的单位长度
        final int patternLen = n - cur;
        if (n % patternLen != 0) {
            // 不是由整数个单位长度重复子串组成的，不符合条件
            return false;
        }
        return true;
    }
}
// @lc code=end
