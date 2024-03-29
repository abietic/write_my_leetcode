import java.util.HashMap;

/*
 * @lc app=leetcode id=62 lang=java
 *
 * [62] Unique Paths
 */

// @lc code=start
class Solution {
    // // 这个算组合数的方法不行，直接增大计算复杂度
    // // private static int[][] memo;
    // // private static boolean[][] memoAvailable;
    // // private static final int NUM_MAX = 110;
    // // static {
    // //     memo = new int[NUM_MAX][];
    // //     memoAvailable = new boolean[NUM_MAX][];
    // //     for (int i = 0; i < NUM_MAX; ++i) {
    // //         memo[i] = new int[NUM_MAX];
    // //         memoAvailable[i] = new boolean[NUM_MAX];
    // //     }
    // // }
    // private int combinatorial(int m, int n) {
    //     // if (n - m < m) {
    //     //     m = n - m;
    //     // }
    //     // if (m == 1) {
    //     //     return n;
    //     // }
    //     // if (m == 0) {
    //     //     return 1;
    //     // }
    //     // if (n == m) {
    //     //     return 1;
    //     // }
    //     // if (memoAvailable[m][n]) {
    //     //     return memo[m][n];
    //     // }
    //     // int res = combinatorial(m - 1, n - 1) + combinatorial(m, n - 1);
    //     // if (!memoAvailable[m][n]) {
    //     //     memoAvailable[m][n] =true;
    //     //     memo[m][n] = res;
    //     // }
    //     // return res;
    //     long res = 1;
    //     for (int i = 0; i < m; ++i) {
    //         res *= (n - i);
    //         res /= (i + 1);
    //     }
    //     return (int)res;
    // }
    // // 用分割子问题的方法做也行
    // // uniquePaths(m, n) = uniquePaths(m - 1, n) + uniquePaths(m, n - 1)
    // // 然后做递归记忆
    // public int uniquePaths(int m, int n) {
    //     int ml = m - 1, nl = n + m - 1 - 1;
    //     if (nl - ml < ml) {
    //         ml = nl - ml;
    //     }
    //     if (ml == 1) {
    //         return nl;
    //     }
    //     return combinatorial(ml, nl);
    // }

    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        // 尝试用dp的思想去做
        int[][] memo = new int[m][n];
        // 由于只能向右或向下走,所以两条直走的路都是没有其它走法的
        for (int i = 1; i < m; ++i) {
            memo[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) {
            memo[0][i] = 1;
        }
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                memo[row][col] = memo[row - 1][col] + memo[row][col - 1];
            }
        }
        return memo[m - 1][n - 1];
    }
}
// @lc code=end

