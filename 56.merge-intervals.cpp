/*
 * @lc app=leetcode id=56 lang=cpp
 *
 * [56] Merge Intervals
 */

// @lc code=start
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        if (intervals.empty()) {
            return {};
        }
        sort(intervals.begin(),
             intervals.end(), 
             [](vector<int>& i1, vector<int>& i2){
                 return i1[0] < i2[0];
             });
        vector<vector<int>> result;
        for (int i = 0; i < intervals.size();) {
            int left = intervals[i][0];
            int cur_right = intervals[i][1];
            if (i == intervals.size() - 1) {
                result.push_back({left, cur_right});
                break;
            }
            for (int j = i + 1; j < intervals.size(); ++j) {
                int next_left = intervals[j][0];
                int next_right = intervals[j][1];
                // 如果两个区间完全不重叠
                if (next_left > cur_right) {
                    i = j;
                    result.push_back({left, cur_right});
                    break;
                } else {
                    // 如果一个区间包含另一个区间
                    if (next_right <= cur_right) {
                        // 不扩大区间
                    } else {
                        // 如果只是部分重叠
                        // 扩大区间右界
                        cur_right = next_right;
                    }
                    if (j == intervals.size() - 1) {
                        result.push_back({left, cur_right});
                        i = j + 1;
                        break;
                    }
                }
            }
        }
        return result;
        // list<vector<int>> il(intervals.begin(), intervals.end());
        // for (auto i = il.begin(); i != --il.end(); ) {
        //     vector<int>& left = *i;
        //     vector<int>& right = *++i;
        //     if (left[1] >= right[0]) {
        //         if (left[1] < right[1]) {
        //             left[1] = right[1];
        //         }
        //         i = --il.erase(i);
        //     } else {
        //         ++i;
        //     }
        // }
        // return vector<vector<int>>(il.begin(), il.end());
    }
};
// @lc code=end

