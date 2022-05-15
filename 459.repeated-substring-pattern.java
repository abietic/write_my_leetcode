/*
 * @lc app=leetcode id=459 lang=java
 *
 * [459] Repeated Substring Pattern
 */

// @lc code=start
class Solution {
    // 暴力解法O(n^2)
    // public boolean repeatedSubstringPattern(String s) {
    //     if (s.length() == 1) {
    //         return false;
    //     }
    //     final int maxPatterLen = s.length() / 2;
    //     for (int i = 1; i <= maxPatterLen; ++i) {
    //         if (s.length() % i != 0) {
    //             continue;
    //         }
    //         String pattern = s.substring(0, i);
    //         boolean repeated = true;
    //         for (int j = i; j < s.length(); j += i) {
    //             if (!s.substring(j, j + i).equals(pattern)) {
    //                 repeated = false;
    //                 break;
    //             }
    //         }
    //         if (repeated) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    // 这个方法由于使用字符串搜索算法O(mn)而这里的m=n，n=2n-2
    // 所以最差执行时间可能还是O(n^2)的
    // 这个方法的思想是，如果s符合要求那么s一定是pattern重复至少两次得到的
    // 也就是两个s拼接至少有连续4个pattern
    // 这时舍弃最左和最右的pattern应该仍至少能找到一个s
    public boolean repeatedSubstringPattern(String s) {
        String ds = s + s;
        return ds.substring(1, ds.length() - 1).contains(s);
    }
}
// @lc code=end

