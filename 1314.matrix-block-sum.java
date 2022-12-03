/*
 * @lc app=leetcode id=1314 lang=java
 *
 * [1314] Matrix Block Sum
 */

// @lc code=start
class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        final int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        // int[][] paddedMat = new int[m + 2 * k][n + 2 * k];
        // for (int i = 0; i < m; ++i) {
        // for (int j = 0; j < n; ++j) {
        // paddedMat[i + k][j + k] = mat[i][j];
        // }
        // }
        // for (int i = k; i < m + k; ++i) {
        // for (int j = k; j < n + k; ++j) {
        // for (int r = i - k; r <= i + k; ++r) {
        // for (int c = j - k; c <= j + k; ++c) {
        // ans[i - k][j - k] += paddedMat[r][c];
        // }
        // }
        // }
        // }
        // return ans;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    // 计算第一个值
                    for (int r = Math.max(0, i - k); r <= Math.min(m - 1, i + k); ++r) {
                        for (int c = Math.max(0, j - k); c <= Math.min(n - 1, j + k); ++c) {
                                ans[i][j] += mat[r][c];
                        }
                    }
                } else if (j == 0) {
                    // 如果换行了,使用头顶的值
                    ans[i][j] = ans[i - 1][j];
                    if (i - 1 - k >= 0) {
                        for (int c = Math.max(0, j - k); c <= Math.min(n - 1, j + k); ++c) {
                            ans[i][j] -= mat[i - 1 - k][c];
                        }
                    }
                    if (i + k <= m - 1) {
                        for (int c = Math.max(0, j - k); c <= Math.min(n - 1, j + k); ++c) {
                            ans[i][j] += mat[i + k][c];
                        }
                    }
                } else {
                    // 换到下一列
                    ans[i][j] = ans[i][j - 1];
                    if (j - 1 - k >= 0) {
                        for (int r = Math.max(0, i - k); r <= Math.min(m - 1, i + k); ++r) {
                            ans[i][j] -= mat[r][j - 1 - k];
                        }
                    }
                    if (j + k <= n - 1) {
                        for (int r = Math.max(0, i - k); r <= Math.min(m - 1, i + k); ++r) {
                            ans[i][j] += mat[r][j + k];
                        }
                    }
                }

            }
        }
        return ans;
    }
}
// @lc code=end
