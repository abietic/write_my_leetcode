/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 */

// @lc code=start
class Solution {
    private final static char flipChar = 'Y';
    private final static char originChar = 'O';
    private final static char noChar= 'X';
    private static void contaminate(char[][] board, int row, int col, boolean flip) {
        final int rows = board.length, cols = board[0].length;
        board[row][col] = flip ? originChar : flipChar;
        int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[]delta : deltas) {
            final int dRow = row + delta[0], dCol = col + delta[1];
            if (dRow < 0 || dRow >= rows || dCol < 0 || dCol >= cols) {
                continue;
            }
            if (flip && board[dRow][dCol] != flipChar) {
                continue;
            } else if (!flip && board[dRow][dCol] != originChar) {
                continue;
            }
            contaminate(board, dRow, dCol, flip);
        }
    }
    public void solve(char[][] board) {
        final int rows = board.length, cols = board[0].length;
        int[] rowBorders = {0, rows - 1}, colBorders = {0, cols - 1};
        for (int row : rowBorders) {
            for (int col = 0; col < cols; ++col) {
                if (board[row][col] == originChar) {
                    contaminate(board, row, col, false);
                }
            }
        }
        for (int col : colBorders) {
            for (int row = 0; row < rows; ++row) {
                if (board[row][col] == originChar) {
                    contaminate(board, row, col, false);
                }
            }
        }
        for (int row = 0; row < rows; ++ row) {
            for (int col = 0; col < cols; ++col) {
                if (board[row][col] != flipChar) {
                    board[row][col] = noChar;
                } else {
                    board[row][col] = originChar;
                }
            }
        }
        // for (int row : rowBorders) {
        //     for (int col = 0; col < cols; ++col) {
        //         if (board[row][col] == flipChar) {
        //             contaminate(board, row, col, true);
        //         }
        //     }
        // }
        // for (int col : colBorders) {
        //     for (int row = 0; row < rows; ++row) {
        //         if (board[row][col] == flipChar) {
        //             contaminate(board, row, col, true);
        //         }
        //     }
        // }
    }
}
// @lc code=end

