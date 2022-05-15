import java.util.HashMap;

/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 */

// @lc code=start
class Solution {
    // 直观的memo加子问题
    // private static class State {
    // final int whoseTurn;
    // final String s1, s2, s3;
    // public State(int whoseTurn, String s1, String s2, String s3) {
    // this.whoseTurn = whoseTurn;
    // this.s1 = s1;
    // this.s2 = s2;
    // this.s3 = s3;
    // }
    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((s1 == null) ? 0 : s1.hashCode());
    // result = prime * result + ((s2 == null) ? 0 : s2.hashCode());
    // result = prime * result + ((s3 == null) ? 0 : s3.hashCode());
    // result = prime * result + whoseTurn;
    // return result;
    // }
    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj)
    // return true;
    // if (obj == null)
    // return false;
    // if (getClass() != obj.getClass())
    // return false;
    // State other = (State) obj;
    // if (s1 == null) {
    // if (other.s1 != null)
    // return false;
    // } else if (!s1.equals(other.s1))
    // return false;
    // if (s2 == null) {
    // if (other.s2 != null)
    // return false;
    // } else if (!s2.equals(other.s2))
    // return false;
    // if (s3 == null) {
    // if (other.s3 != null)
    // return false;
    // } else if (!s3.equals(other.s3))
    // return false;
    // if (whoseTurn != other.whoseTurn)
    // return false;
    // return true;
    // }

    // }
    // private static HashMap<State, Boolean> memo;
    // private boolean _isInterleave(String s1, String s2, String s3, int whoseTurn)
    // {
    // if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
    // return true;
    // }
    // State state = new State(whoseTurn, s1, s2, s3);
    // Boolean res = memo.get(state);
    // if (res != null) {
    // return res;
    // }
    // res = Boolean.FALSE;
    // String s = s1;
    // int nextTurn = 2;
    // if (whoseTurn == 2) {
    // s = s2;
    // nextTurn = 1;
    // }
    // for (int i = 0; i < s3.length() && i < s.length(); ++i) {
    // if (s.charAt(i) != s3.charAt(i)) {
    // break;
    // }
    // if (nextTurn == 1) {
    // if (_isInterleave(s1, s.substring(i + 1), s3.substring(i + 1), nextTurn)) {
    // res = Boolean.TRUE;
    // }
    // } else {
    // if (_isInterleave(s.substring(i + 1), s2, s3.substring(i + 1), nextTurn)) {
    // res = Boolean.TRUE;
    // }
    // }
    // if (res) {
    // break;
    // }
    // }
    // memo.put(state, res);
    // return res;
    // }
    // public boolean isInterleave(String s1, String s2, String s3) {
    // memo = new HashMap<>();
    // return _isInterleave(s1, s2, s3, 1) || _isInterleave(s1, s2, s3, 2);
    // }

    // 平方复杂度的
    // 不需要考虑interleave的先后顺序，因为连续在同一个字符串中取子串可以看作取一个较长的串
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] memo = new boolean[s1.length() + 1][s2.length() + 1];
        memo[0][0] = memo[0][0] = true;
        for (int s1l = 0; s1l <= s1.length(); ++s1l) {
            for (int s2l = 0; s2l <= s2.length(); ++s2l) {
                final int s3l = s1l + s2l;
                if (s3l == 0) {
                    continue;
                }
                if (s1l > 0 && s1.charAt(s1.length() - s1l) == s3.charAt(s3.length() - s3l) && memo[s1l - 1][s2l]) {
                    memo[s1l][s2l] = true;
                    continue;
                }
                if (s2l > 0 && s2.charAt(s2.length() - s2l) == s3.charAt(s3.length() - s3l) && memo[s1l][s2l - 1]) {
                    memo[s1l][s2l] = true;
                }
            }
        }
        return memo[s1.length()][s2.length()];
    }

}
// @lc code=end
