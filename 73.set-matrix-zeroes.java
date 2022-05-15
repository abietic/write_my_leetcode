/*
 * @lc app=leetcode id=73 lang=java
 *
 * [73] Set Matrix Zeroes
 */

// @lc code=start
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int lastRowIndex = m - 1;
        boolean lastRowHasZero = false;
        for (int num : matrix[lastRowIndex]) {
            if (num == 0) {
                lastRowHasZero = true;
                break;
            }
        }
        for (int row = 0; row < lastRowIndex; ++row) {
            boolean thisRowHasZero = false;
            for (int col = 0; col < n; ++col) {
                if (matrix[row][col] == 0) {
                    thisRowHasZero = true;
                    matrix[lastRowIndex][col] = 0;
                }
            }
            if (thisRowHasZero) {
                for (int i = 0; i < n; ++i) {
                    matrix[row][i] = 0;
                }
            }
        }
        for (int col = 0; col < n; ++col) {
            if (matrix[lastRowIndex][col] == 0) {
                for (int row = 0; row < m; ++row) {
                    matrix[row][col] = 0;
                }
            } else if (lastRowHasZero) {
                matrix[lastRowIndex][col] = 0;
            }
        }
    }
}
// @lc code=end

