/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        if (s.isEmpty()) {
            if (p.isEmpty()) {
                return true;
            } else {
                if (p.length() >= 2 && p.charAt(1) == '*') {
                    return isMatch(s, p.substring(2));
                }
                return false;
            }
        }
        if (p.isEmpty()) {
            return false;
        }
        if (p.length() >= 2 && p.charAt(1) == '*') {
            if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            }
            return isMatch(s, p.substring(2));
        } else {
            if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }
    }
}
// @lc code=end

