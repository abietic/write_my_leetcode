import java.util.HashMap;

/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

// @lc code=start
class Solution {
    // private static final class MemoKey {
    //     public final String w1, w2;

    //     public MemoKey(String w1, String w2) {
    //         this.w1 = w1;
    //         this.w2 = w2;
    //     }

    //     @Override
    //     public int hashCode() {
    //         final int prime = 31;
    //         int result = 1;
    //         result = prime * result + ((w1 == null) ? 0 : w1.hashCode());
    //         result = prime * result + ((w2 == null) ? 0 : w2.hashCode());
    //         return result;
    //     }

    //     @Override
    //     public boolean equals(Object obj) {
    //         if (this == obj)
    //             return true;
    //         if (obj == null)
    //             return false;
    //         if (getClass() != obj.getClass())
    //             return false;
    //         MemoKey other = (MemoKey) obj;
    //         if (w1 == null) {
    //             if (other.w1 != null)
    //                 return false;
    //         } else if (!w1.equals(other.w1))
    //             return false;
    //         if (w2 == null) {
    //             if (other.w2 != null)
    //                 return false;
    //         } else if (!w2.equals(other.w2))
    //             return false;
    //         return true;
    //     }

    // }

    // private HashMap<MemoKey, Integer> memo;


    // public int _minDistance(String word1, String word2) {
    //     // 已经相同不需要操作
    //     if (word1.equals(word2)) {
    //         return 0;
    //     }
    //     if (word2.isEmpty()) {
    //         // 多余的部分全部删除
    //         return word1.length();
    //     }
    //     if (word1.isEmpty()) {
    //         // 缺少部分全部加入
    //         return word2.length();
    //     }
    //     MemoKey key = new MemoKey(word1, word2);
    //     Integer possible = this.memo.get(key);
    //     if (possible != null) {
    //         return possible;
    //     }
    //     int res = word1.length() + word2.length(); // 比最坏实际操作数差
    //     // if (word1.charAt(0) == word2.charAt(0)) {
    //     // // 有相等的不用动肯定最好
    //     // res = Math.min(res, minDistance(word1.substring(1), word2.substring(1)));
    //     // } else {
    //     // 不相同的部分有三种方案
    //     int sub_res = 0;
    //     do {
    //         // 做插入操作造成匹配
    //         sub_res = minDistance(word1, word2.substring(1));
    //         if (sub_res == 0) {
    //             res = 1;
    //             break;
    //         }
    //         res = Math.min(res, sub_res + 1);
    //         // 不做匹配删除一个字符
    //         sub_res = minDistance(word1.substring(1), word2);
    //         if (sub_res == 0) {
    //             res = 1;
    //             break;
    //         }
    //         res = Math.min(res, sub_res + 1);
    //         // 做替换操作造成匹配，或有一个字符匹配
    //         sub_res = minDistance(word1.substring(1), word2.substring(1));
    //         if (sub_res == 0) {
    //             res = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
    //             break;
    //         }
    //         res = word1.charAt(0) == word2.charAt(0) ? Math.min(res, sub_res) : Math.min(res, sub_res + 1);
    //     } while (false);
    //     // }
    //     this.memo.put(key, res);
    //     return res;
    // }

    // public int minDistance(String word1, String word2) {
    //     this.memo = new HashMap<>();
    //     return this._minDistance(word1, word2);
    // }
    // public int minDistance(String word1, String word2) {
    //     final int w1l = word1.length(), w2l = word2.length();
    //     final int[][] memo = new int[w1l + 1][w2l + 1];
        
    //     // 初始化memo中已经可以推得的修改数
    //     for (int i = 0; i <= w1l;++i) {
    //         memo[i][0] = i;
    //     }
    //     for (int i = 0; i <= w2l; ++i) {
    //         memo[0][i] = i;
    //     }
    //     // 代表剩余字符串长度的变量
    //     for (int w1r = 1; w1r <= w1l; ++w1r) {
    //         for (int w2r = 1; w2r <= w2l; ++w2r) {
    //             if (word1.charAt(w1l - w1r) == word2.charAt(w2l - w2r)) {
    //                 memo[w1r][w2r] = memo[w1r - 1][w2r - 1];
    //             } else {
    //                 memo[w1r][w2r] = Math.min(Math.min(memo[w1r - 1][w2r], memo[w1r][w2r - 1]), memo[w1r - 1][w2r - 1]) + 1;
    //             }
    //         }
    //     }
    //     return memo[w1l][w2l];
    // }

    public int minDistance(String word1, String word2) {
        // f(word1Len, word2Len) = min{f(word1Len - 1, word2Len - 1) if 末尾相等, f(word1Len - 1, word2Len - 1) + 1 if replace, f(word1Len - 1, word2Len) + 1 if delete, f(word1Len, word2Len - 1) + 1 if insert}
        // f(0, 0) = 0 f(0, x) = x f(x, 0) = x
        final int MAX_EDIT = Math.max(word1.length(), word2.length());
        if (word1.length() == 0 || word2.length() == 0) {
            return MAX_EDIT;
        }
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int word1Len = 1; word1Len <= word1.length(); ++word1Len) {
            memo[word1Len][0] = word1Len;
        }
        for (int word2Len = 1; word2Len <= word2.length(); ++word2Len) {
            memo[0][word2Len] = word2Len;
        }
        for (int word1Len = 1; word1Len <= word1.length(); ++word1Len) {
            for (int word2Len = 1; word2Len <= word2.length(); ++word2Len) {
                memo[word1Len][word2Len] = MAX_EDIT;
                if (word1.charAt(word1Len - 1) == word2.charAt(word2Len - 1)) {
                    memo[word1Len][word2Len] = memo[word1Len - 1][word2Len - 1];
                }
                // replace
                memo[word1Len][word2Len] = Math.min(memo[word1Len][word2Len], memo[word1Len - 1][word2Len - 1] + 1);
                // delete
                memo[word1Len][word2Len] = Math.min(memo[word1Len][word2Len], memo[word1Len - 1][word2Len] + 1);
                // insert
                memo[word1Len][word2Len] = Math.min(memo[word1Len][word2Len], memo[word1Len][word2Len - 1] + 1);
            }
        }
        return memo[word1.length()][word2.length()];
    }
}
// @lc code=end
