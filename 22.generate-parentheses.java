import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

// @lc code=start
class Solution {
    public static void genRec(int n, int depth, String cur, List<String> res) {
        if (n == 0 && depth == 0) {
            res.add(cur);
        }
        if (depth != 0) {
            genRec(n, depth - 1, cur + ")", res);
        }
        if (n != 0) {
            genRec(n - 1, depth + 1, cur + "(", res);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ar = new ArrayList<>();
        genRec(n, 0, "", ar);
        return ar;
    }
}
// @lc code=end

