import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 */

// @lc code=start
class Solution {
    private static boolean _isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>(), rightSub;
        if (s.length() == 1) {
            res.add(new ArrayList<String>(){{add(s);}});
            return res;
        }
        if (_isPalindrome(s)) {
            res.add(new ArrayList<String>(){{add(s);}});
        }
        for (int i = 1; i < s.length(); ++i) {
            String left = s.substring(0, i), right = s.substring(i);
            if (!_isPalindrome(left)) {
                continue;
            }
            rightSub = partition(right);
            if (rightSub.isEmpty()){
                continue;
            }
            for (List<String> rs : rightSub) {
                List<String> e = new ArrayList<>();
                e.add(left);
                e.addAll(rs);
                res.add(e);
            }
        }
        return res;
    }
}
// @lc code=end
