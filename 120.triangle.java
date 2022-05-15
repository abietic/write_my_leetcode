import java.util.List;

/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 */

// @lc code=start
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        final int rowCount = triangle.size();
        for (int i = rowCount - 2; i >= 0; --i) {
            List<Integer> thisRow = triangle.get(i);
            List<Integer> previousRow = triangle.get(i+1);
            for (int j = 0; j < thisRow.size(); ++j) {
                thisRow.set(j, thisRow.get(j) + Math.min(previousRow.get(j), previousRow.get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
// @lc code=end

