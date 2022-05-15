import java.util.Arrays;

/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        int intervalCount = intervals.length;
        final int n = intervals.length;
        // 先对区间起始位置进行排序
        Arrays.sort(intervals, (a, b)->{return a[0] - b[0];});
        for (int i = 0, k = 0; i < n; ++i, ++k) {
            // 当前要处理的区间i
            int[] interval = intervals[i];
            int back = interval[1];
            int j = i + 1;
            for (; j < n; ++j) {
                // 如果区间没有overlap
                if (back < intervals[j][0]) {
                    break;
                }
                // 如果有overlap，更新区间结束位置
                back = Math.max(back, intervals[j][1]);
                // 合并使区间数量减少一个
                intervalCount--;
            }
            // 放到实际应该保存的位置k
            intervals[k][0] = interval[0];
            intervals[k][1] = back;
            // 新的要检查区间的位置，由于i还有++，所以这里取j-1
            i = j - 1;
        }
        int[][] res = new int[intervalCount][2];
        for (int i = 0; i < intervalCount; ++i) {
            res[i][0] = intervals[i][0];
            res[i][1] = intervals[i][1];
        }
        return res;
    }
}
// @lc code=end

