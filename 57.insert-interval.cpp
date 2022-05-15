/*
 * @lc app=leetcode id=57 lang=cpp
 *
 * [57] Insert Interval
 */

// @lc code=start
using namespace std;
#include <vector>
class Solution
{
public:
    vector<vector<int>> insert(vector<vector<int>> &intervals, vector<int> &newInterval)
    {
        // 区间的start和end可以相等
        // 给出的intervals区间之间没有相交
        // 新区间不相交的情况有3种，在中间不相交，在两侧不相交
        // 相交的情况，完全包含在一个区间内，与多个区间相交
        int inter_s = newInterval[0];
        int inter_e = newInterval[1];
        int sz = intervals.size();
        int i; // 找新区间start右侧离它最近的完整区间，并判断其是否在某一个区间内
        bool start_inside_interval = false;
        bool start_inside_current = false;
        for (i = 0; i < sz; ++i)
        {
            if (intervals[i][0] >= inter_s) // 这个区间的开始值已经在start的右侧了
            {
                if (inter_s == intervals[i][0])
                { // start与这个开始区间重合了，在这个区间i内
                    start_inside_interval = true;
                    start_inside_current = true;
                }
                else
                {
                    if (i > 0)
                    { // 检查是否在上一个区间内
                        if (inter_s >= intervals[i - 1][0] && inter_s <= intervals[i - 1][1])
                        {
                            start_inside_interval = true;
                        }
                    }
                }
                break;
            }
        }
        if (i == sz && !intervals.empty())
        {
            if (inter_s >= intervals[sz - 1][0] && inter_s <= intervals[sz - 1][1])
            {
                start_inside_interval = true;
            }
        }
        int j; // 找新区间end左侧离它最近的完整区间，并判断其是否在某一个区间内
        bool end_inside_interval = false;
        bool end_inside_current = false;
        for (j = 0; j < sz; ++j)
        {
            if (intervals[j][1] >= inter_e) // 这个区间的结束值已经在end的右侧了
            {
                if (intervals[j][0] <= inter_e)
                { // end 正好在这个区间j内部
                    end_inside_interval = true;
                    if (intervals[j][1] == inter_e)
                    {
                        end_inside_current = true; // 区间j正好是end左侧的完整空间
                    }
                    else
                    {
                        j -= 1; // 否则上一个区间是其左侧的第一个完成区间，注意这里的j是可以为-1的，这时表示左侧没有区间
                    }
                }
                else
                {
                    j -= 1; // 不与区间j相交，上一个区间是其左侧的第一个完成区间，注意这里的j是可以为-1的，这时表示左侧没有区间
                }
                break;
            }
        }
        // start 和 end 都没有插在其它区间内部
        if (start_inside_interval == false && end_inside_interval == false)
        {
            // 现在看它们之间有没有区间
            if ((i - 1) == j)
            {
                // start 和 end 之间没有 interval，直接插入不相交的newInterval即可，本情况包含了在开始不相交
                intervals.insert(intervals.begin() + i, newInterval);
                return intervals;
            }
            if (i == j && i == sz)
            {
                // 在结束不相交，两者都没有进入if块内，i，j都为sz
                intervals.insert(intervals.begin() + i, newInterval);
                return intervals;
            }
            // start 和 end 之间有区间，所以将它们之间的区间删去，并插入新区间
            auto ip = (j != sz) ? intervals.erase(intervals.begin() + i, intervals.begin() + j + 1) : intervals.erase(intervals.begin() + i, intervals.end());
            intervals.insert(ip, newInterval);
            return intervals;
        }
        // 如果star或end在某个区间内情况稍微复杂
        int neo_start = inter_s;
        int neo_end = inter_e;
        if (start_inside_interval)
        {
            if (!start_inside_current)
            {
                // 需要改变新区间范围，并且多删除一个左侧的区间
                neo_start = intervals[i - 1][0];
                i -= 1;
            }
        }
        if (end_inside_interval)
        {
            if (!end_inside_current)
            {
                neo_end = intervals[j + 1][1];
                j += 1;
            }
        }
        auto ip = (j != sz) ? intervals.erase(intervals.begin() + i, intervals.begin() + j + 1) : intervals.erase(intervals.begin() + i, intervals.end());
        intervals.insert(ip, {neo_start, neo_end});
        return intervals;
    }
};
// @lc code=end
