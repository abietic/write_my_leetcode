import java.util.Arrays;

/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    final static char ISLAND = '1';
    final static char COLORED_ISLAND = '2';
    final static char WATER = '0';
    // private static void coloring (char[][] grid, int row, int col) {
    // if (grid[row][col] != ISLAND) {
    // return;
    // }
    // grid[row][col] = COLORED_ISLAND;
    // int[] steps = {-1, 1};
    // for (int step : steps) {
    // if (row + step >= 0 && row + step < grid.length) {
    // coloring(grid, row + step, col);
    // }
    // if (col + step >= 0 && col + step < grid[0].length) {
    // coloring(grid, row, col + step);
    // }
    // }
    // }
    // public int numIslands(char[][] grid) {
    // int count = 0;
    // for (int row = 0; row < grid.length; ++row) {
    // for (int col = 0; col < grid[0].length; ++col) {
    // if (grid[row][col] == ISLAND) {
    // coloring(grid, row, col);
    // count++;
    // }
    // }
    // }
    // return count;
    // }

    private static int axis2Idx(int row, int col, int m, int n) {
        return row * n + col;
    }

    private int find(int[] mergeSet, int idx) {
        int parent = mergeSet[idx];
        if (parent < 0) {
            return idx;
        } else {
            parent = find(mergeSet, parent);
            mergeSet[idx] = parent;
            return parent;
        }
    }

    private void merge(int[] mergeSet, int idx1, int idx2) {
        int parent1 = find(mergeSet, idx1), parent2 = find(mergeSet, idx2);
        if (parent1 == parent2) {
            return;
        }
        // 因为路径压缩的原因记录的最大权值长度没什么用
        if (mergeSet[parent1] < mergeSet[parent2]) {
            int rl = mergeSet[parent2];
            mergeSet[parent2] = parent1;
            mergeSet[parent1] = Math.min(rl - 1, mergeSet[parent1]);
        } else {
            int rl = mergeSet[parent1];
            mergeSet[parent1] = parent2;
            mergeSet[parent2] = Math.min(rl - 1, mergeSet[parent2]);
        }
    }

    public int numIslands(char[][] grid) {
        final int m = grid.length, n = grid[0].length;
        int[] mergeSet = new int[m * n];
        Arrays.fill(mergeSet, -1);
        int[] steps = { -1, 1 };
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                if (grid[row][col] == ISLAND) {
                    int idx1 = axis2Idx(row, col, m, n);
                    for (int step : steps) {
                        if (row + step >= 0 && row + step < m && grid[row + step][col] == ISLAND) {
                            int idx2 = axis2Idx(row + step, col, m, n);
                            merge(mergeSet, idx1, idx2);
                        }
                        if (col + step >= 0 && col + step < n && grid[row][col + step] == ISLAND) {
                            int idx2 = axis2Idx(row, col + step, m, n);
                            merge(mergeSet, idx1, idx2);
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                int idx1 = axis2Idx(row, col, m, n);
                if (grid[row][col] == ISLAND && mergeSet[idx1] < 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
// @lc code=end
