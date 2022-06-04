import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 */

// @lc code=start
class Solution {
    // 暴力做法可以通过
    private static boolean _isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
    // public List<List<String>> partition(String s) {
    //     List<List<String>> res = new ArrayList<>(), rightSub;
    //     if (s.length() == 1) {
    //         res.add(new ArrayList<String>(){{add(s);}});
    //         return res;
    //     }
    //     if (_isPalindrome(s)) {
    //         res.add(new ArrayList<String>(){{add(s);}});
    //     }
    //     for (int i = 1; i < s.length(); ++i) {
    //         String left = s.substring(0, i), right = s.substring(i);
    //         if (!_isPalindrome(left)) {
    //             continue;
    //         }
    //         rightSub = partition(right);
    //         if (rightSub.isEmpty()){
    //             continue;
    //         }
    //         for (List<String> rs : rightSub) {
    //             List<String> e = new ArrayList<>();
    //             e.add(left);
    //             e.addAll(rs);
    //             res.add(e);
    //         }
    //     }
    //     return res;
    // }

    public List<List<String>> partition(String s) {
        // 改为迭代做，记录已经划分过的子串结果，以便重复使用
        // 使用了与本题的II变种类似的方式
        final int n = s.length();
        byte[] sb = s.getBytes();
        List<List<String>>[] memo = new List[n + 1];
        boolean[][] isPalindrome = new boolean[n + 1][n + 1];
        memo[0] = new ArrayList<>();
        memo[0].add(new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            isPalindrome[i][i] = isPalindrome[i][i + 1] = true;
        }
        for (int len = 1; len <= n; ++len) {
            memo[len] = new ArrayList<>();
            int addedIndex = len - 1;
            for (int left = addedIndex; left >= 0; --left) {
                if (sb[left] == sb[addedIndex] && (left == addedIndex || isPalindrome[left + 1][addedIndex])) {
                    isPalindrome[left][addedIndex + 1] = true;
                    for (List<String> subRes : memo[left]) {
                        List<String> neo = new ArrayList<>(subRes);
                        neo.add(s.substring(left, addedIndex + 1));
                        memo[len].add(neo);
                    }
                }
            }
        }
        return memo[n];
    }
}
// @lc code=end
