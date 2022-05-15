import java.util.Arrays;

/*
 * @lc app=leetcode id=151 lang=java
 *
 * [151] Reverse Words in a String
 */

// @lc code=start
class Solution {
    public String reverseWords(String s) {
        byte[] str = s.getBytes();
        int len = s.length();
        // TODO
        return new String(str,0, len);
    }
}
// @lc code=end

