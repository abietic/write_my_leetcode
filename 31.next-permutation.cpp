/*
 * @lc app=leetcode id=31 lang=cpp
 *
 * [31] Next Permutation
 */

// @lc code=start
#include <vector>
#include <algorithm>
using namespace std;
class Solution
{
public:
    void nextPermutation(vector<int> &nums)
    {
        int boundary = nums.size() - 1;
        for (; boundary > 0; --boundary)
        {
            if (nums[boundary - 1] < nums[boundary])
            {
                break;
            }
        }
        sort(nums.begin() + boundary, nums.end());
        if (boundary != 0)
        {
            auto ex = upper_bound(nums.begin() + boundary, nums.end(), nums[boundary - 1]);
            iter_swap(ex, nums.begin() + boundary - 1);
            sort(nums.begin() + boundary, nums.end());
        }
    }
};
// @lc code=end
