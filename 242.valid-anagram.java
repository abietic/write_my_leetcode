import java.util.Arrays;

/*
 * @lc app=leetcode id=242 lang=java
 *
 * [242] Valid Anagram
 */

// @lc code=start
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] charCount = new int[26];
        for (byte c : s.getBytes()) {
            charCount[c - 'a']++;
        }
        for (byte c : t.getBytes()) {
            charCount[c - 'a']--;
        }
        return Arrays.stream(charCount).allMatch((ele)->{return ele == 0;});
    }
}
// @lc code=end

