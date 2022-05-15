import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=168 lang=java
 *
 * [168] Excel Sheet Column Title
 */

// @lc code=start
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        List<Character> cs = new LinkedList<>();
        while (columnNumber > 0) {
            char dig = (char)(columnNumber % 27 - 1 + 'A');
            columnNumber /= 26; 
            cs.add(0, dig);
        }
        for (char c : cs) {
            sb.append(c);
        }
        return sb.toString();
    }
}
// @lc code=end

