import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=994 lang=java
 *
 * [994] Rotting Oranges
 */

// @lc code=start
class Solution {
    //  BFS
    public static final int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int orangesRotting(int[][] grid) {
        int steps = 0;
        Set<List<Integer>> points = new HashSet<>(), tmp = new HashSet<>();
        Set<List<Integer>> freshes = new HashSet<>();
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                List<Integer> axis = new ArrayList<>();
                axis.add(row);
                axis.add(col);
                switch (grid[row][col]) {
                    case 1 : freshes.add(axis);break;
                    case 2 : points.add(axis);break;
                }
            }
        }
        while (!points.isEmpty()) {
            boolean hasPorp = false;
            for (List<Integer> axis : points) {
                for (int[] dir : dirs) {
                    int row = axis.get(0) + dir[0], col = axis.get(1) + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
                        List<Integer> neoAxis = new ArrayList<>();
                        neoAxis.add(row);
                        neoAxis.add(col);
                        if (freshes.remove(neoAxis)) {
                            tmp.add(neoAxis);
                            hasPorp = true;
                        }
                    }
                }
            }
            if (!hasPorp) {
                break;
            }
            points = tmp;
            tmp = new HashSet<>();
            steps++;
        }
        if (!freshes.isEmpty()) {
            return -1;
        }
        return steps;
    }
}
// @lc code=end
