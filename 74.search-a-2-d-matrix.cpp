/*
 * @lc app=leetcode id=74 lang=cpp
 *
 * [74] Search a 2D Matrix
 */

// @lc code=start
using namespace std;
#include <vector>
#include <algorithm>
class Solution
{
public:
    bool searchMatrix(vector<vector<int>> &matrix, int target)
    {
        auto start = lower_bound(matrix.begin(), matrix.end(), target, [](const vector<int> &vec, const int &num)
                                 { return vec[0] < num; });
        if (start == matrix.end()) {
            start -= 1;
            return binary_search((*start).begin(), (*start).end(), target);
        }
        if ((*start)[0] == target) {
            return true;
        }
        if (start == matrix.begin()) {
            return false;
        }
        start -= 1;
        return binary_search((*start).begin(), (*start).end(), target);
    }
};
// @lc code=end
