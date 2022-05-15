import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

// @lc code=start
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int intervalCount = intervals.length;
        final int n = intervals.length;
        int startOverlap = -1, endOverlap = -1;
        int start,end;
        start = newInterval[0];
        end = newInterval[1];
        List<int[]> res = new ArrayList<>();
        int continuePos = n;
        boolean lastInsertion = true;
        for (int i = 0; i < n; ++i) {
            if (start > intervals[i][1]) {
                res.add(intervals[i]);
                continue;
            }
            if (start < intervals[i][0]) {
                // 开始位置在区间外侧
                for (int j = i; j < n; ++j) {
                    if (intervals[j][0] > end) {
                        continuePos = j;
                        break;
                    }
                    end = Math.max(end, intervals[j][1]);    
                }
                newInterval[0] = start;
                newInterval[1] = end;
                res.add(newInterval);
                lastInsertion = false;
                break;
            }
            // 开始位置在某区间内侧
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            for (int j = i + 1; j < n; ++j) {
                if (intervals[j][0] > end) {
                    continuePos = j;
                    break;
                }
                end = Math.max(end, intervals[j][1]);
            }
            newInterval[0] = start;
            newInterval[1] = end;
            res.add(newInterval);
            lastInsertion = false;
            break;
        }
        for (int i = continuePos; i < n; ++i) {
            res.add(intervals[i]);
        }
        if (lastInsertion) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][]);
    }
}
// @lc code=end

