import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 */

// @lc code=start
class Solution {
    // 可能是dp？
    // f(row, col) = max[f(row, col - 1), f(row - 1, col), 从新产生顶点出发生成的方形面积]
    private int checkDiagonal(char [][] matrix, int row, int col) {
        row--;
        col--;
        if (matrix[row][col] == '0') {
            return 0;
        }
        int colCeil = 0;
        for (int i = col; i >= 0; --i) {
            if (matrix[row][i] == '0') {
                break;
            }
            colCeil++;
        }
        int lasTime = 1, maxSquare = colCeil;
        for (int i = row - 1; i >= 0; --i) {
            int curColLen = 0;
            for (int j = col; j >= 0; --j) {
                if (matrix[i][j] == '0') {
                    break;
                }
                curColLen++;
            }
            if (curColLen < colCeil) {
                if (colCeil * lasTime > maxSquare) {
                    maxSquare = colCeil * lasTime;
                }
                colCeil = curColLen;
            }
            lasTime++;
            if (colCeil == '0') {
                break;
            }
        }
        if (colCeil * lasTime > maxSquare) {
            maxSquare = colCeil * lasTime;
        }
        return maxSquare;
    }
    public int maximalRectangle(char[][] matrix) {
        final int m = matrix.length, n = matrix[0].length;
        final int[][] state = new int[m + 1][n + 1];
        for (int row = 1; row <= m; ++row) {
            for (int col = 1; col <= n; ++col) {
                state[row][col] = Math.max(state[row - 1][col], Math.max(state[row][col - 1], checkDiagonal(matrix, row, col)));
            }
        }
        return state[m][n];
    }
}
// @lc code=end

