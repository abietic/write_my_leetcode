/*
 * @lc app=leetcode id=47 lang=cpp
 *
 * [47] Permutations II
 */

// @lc code=start
class Solution {
public:
    // vector<vector<int>> permuteUnique(vector<int>& nums) {
    //     vector<vector<int>> res;
    //     sort(nums.begin() , nums.end());
    //     res.push_back(nums);
    //     if (nums.size() <= 1)
    //         return res;
    //     while (true) {
    //         int i , j;
    //         for (i = nums.size() - 1 ; i > 0 && nums[i] <= nums[i - 1] ; i --);
    //         if (i == 0)
    //             break;
    //         for (j = nums.size() - 1 ; j > (i - 1) && nums[j] <= nums[i - 1] ; j --);
    //         swap(nums[i - 1] , nums[j]);
    //         sort(nums.begin() + i , nums.end());
    //         res.push_back(nums);
    //     }
    //     return res;
    // }

    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<vector<int>> res;
        sort(nums.begin() , nums.end());
        res.push_back(nums);
        if (nums.size() <= 1)
            return res;
        while (true) {
            int i , j;
            for (i = nums.size() - 1 ; i > 0 && nums[i] <= nums[i - 1] ; i --);
            if (i == 0)
                break;
            nextPermutation(nums);
            res.push_back(nums);
        }
        return res;
    }
private:
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

