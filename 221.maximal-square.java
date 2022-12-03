/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */

// @lc code=start
class Solution {
    // public int maximalSquare(char[][] matrix) {
    // final int m = matrix.length, n = matrix[0].length;
    // // int[][] memo = new int[m + 1][n + 1];
    // int res = 0;
    // int[][] squareRightBot = new int[m][n]; // 记录指定坐标为右下角的方形的长度
    // for (int rowLen = 1; rowLen <= m; ++rowLen) {
    // for (int colLen = 1; colLen <= n; ++colLen) {
    // int diagSquare = 0;
    // diagSquare = matrix[rowLen - 1][colLen - 1] == '1' ? 1 : 0;
    // int prevDiagSquare = 0;
    // if (diagSquare > 0 && rowLen - 2 >= 0 && colLen - 2 >= 0 && (prevDiagSquare =
    // squareRightBot[rowLen - 2][colLen - 2]) > 0) {
    // int rl = 0, cl = 0;
    // // 这种方法每次检查右下角的扩展的方法存在重复计算没有很好的利用上历史属性但是复杂度来到了n^3不超时了
    // for (int row = rowLen - 1; row >= 0 && rl < prevDiagSquare + 1; ++rl, --row)
    // {
    // if (matrix[row][colLen - 1] != '1') {
    // break;
    // }
    // }
    // for (int col = colLen - 1; col >= 0 && cl < prevDiagSquare + 1; ++cl, --col)
    // {
    // if (matrix[rowLen - 1][col] != '1') {
    // break;
    // }
    // }
    // diagSquare = Math.min(rl, cl);
    // }
    // // 这种源于最大矩形的计算方法时间复杂度为n^4会超时
    // // for (; diagSquare <= Math.min(rowLen, colLen); ++diagSquare) {
    // // boolean isSquare = true;
    // // if (rowLen - 1 - diagSquare < 0 || colLen - 1 - diagSquare < 0) {
    // // break;
    // // }
    // // for (int row = rowLen - 1 - diagSquare; row < rowLen; ++row) {
    // // if (matrix[row][colLen - 1 - diagSquare] != '1') {
    // // isSquare = false;
    // // break;
    // // }
    // // }
    // // if (!isSquare) {
    // // break;
    // // }
    // // for (int col = colLen - 1 - diagSquare; col < colLen; ++col) {
    // // if (matrix[rowLen - 1 - diagSquare][col] != '1') {
    // // isSquare = false;
    // // break;
    // // }
    // // }
    // // if (!isSquare) {
    // // break;
    // // }
    // // }
    // // memo[rowLen][colLen] = Math.max(memo[rowLen][colLen - 1],
    // // Math.max(memo[rowLen - 1][colLen], Math.max(memo[rowLen - 1][colLen - 1],
    // diagSquare)));
    // res = Math.max(diagSquare, res);
    // squareRightBot[rowLen - 1][colLen - 1] = diagSquare;
    // }
    // }
    // return res * res;
    // }

    // 下面是正经dp的做法
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        final int m = matrix.length, n = matrix[0].length;
        // 记录以对应下标为方形右下角的方形的长度
        int[][] memo = new int[m][n];
        for (int row = 0; row < m; ++row) {
            if (matrix[row][0] == '1') {
                res = 1;
                memo[row][0] = 1;
            }
        }
        for (int col = 0; col < n; ++col) {
            if (matrix[0][col] == '1') {
                res = 1;
                memo[0][col] = 1;
            }
        }
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                if (matrix[row][col] != '1') {
                    // 如果右下角
                    memo[row][col] = 0;
                    continue;
                }
                // 其实把临近右下角的几个方形画出来就能够更直观的看出这个式子的含义
                // 一个以右下角的点为右下角的矩形肯定是由包围它的三个已计算出来的矩形的大小决定的
                memo[row][col] = Math.min(memo[row - 1][col], Math.min(memo[row][col - 1], memo[row - 1][col - 1])) + 1;
                res = Math.max(res, memo[row][col]);
            }
        }
        return res * res;
    }
}
// @lc code=end
