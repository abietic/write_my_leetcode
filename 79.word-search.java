import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 */

// @lc code=start
class Solution {
    private static class Axis {
        public final int row, col;

        public Axis(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Axis other = (Axis) obj;
            if (col != other.col)
                return false;
            if (row != other.row)
                return false;
            return true;
        }

        List<Axis> canMove(int m, int n) {
            List<Axis> res = new ArrayList<>();
            if (row != 0) {
                res.add(new Axis(row - 1, col));
            }
            if (row != m - 1) {
                res.add(new Axis(row + 1, col));
            }
            if (col != 0) {
                res.add(new Axis(row, col - 1));
            }
            if (col != n - 1) {
                res.add(new Axis(row, col + 1));
            }
            return res;
        }
    }

    private boolean _exist(char[][] board, String word, Axis axis, int m, int n, Set<Axis> memo) {

        if (word.length() == 1) {
            return true;
        }
        String nextWord = word.substring(1);
        char nextChar = nextWord.charAt(0);
        memo.add(axis);
        for (Axis ax : axis.canMove(m, n)) {
            if (memo.contains(ax)) {
                continue;
            }
            if (board[ax.row][ax.col] == nextChar && _exist(board, nextWord, ax, m, n, memo)) {
                return true;
            }
        }
        memo.remove(axis);
        return false;
    }

    public boolean exist(char[][] board, String word) {
        final int m = board.length, n = board[0].length;
        char firstChar = word.charAt(0), lastChar = word.charAt(word.length() - 1);
        String reverseWord = new StringBuilder(word).reverse().toString();
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                if (board[row][col] == firstChar) {
                    if (_exist(board, word, new Axis(row, col), m, n, new HashSet<>())) {
                        return true;
                    }
                }
                if (board[row][col] == lastChar) {
                    if (_exist(board, reverseWord, new Axis(row, col), m, n, new HashSet<>())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
// @lc code=end
