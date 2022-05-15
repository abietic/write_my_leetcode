/*
 * @lc app=leetcode id=60 lang=cpp
 *
 * [60] Permutation Sequence
 */

// @lc code=start
#include <string>
#include <algorithm>
using namespace std;
class Solution
{
public:
    void nextPermutation(string &nums)
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
    string _getPermutation(string res, int k) {
        int n = res.size();
        int extracted = 1;
        for (int pro = 1; extracted <= n; ++extracted) {
            pro *= extracted;
            if (pro > k) {
                // 这个排列的影响范围介于extracted 和 extracted - 1 之间
                int fac = pro / extracted;
                int pick = (k - 1) / fac;
                int rem = (k % fac) ? (k % fac) : fac;
                iter_swap(res.begin() + res.size() - extracted, res.begin() + res.size() - extracted + pick);
                string nxt = res.substr(res.size() - extracted + 1);
                sort(nxt.begin(), nxt.end());
                return res.substr(0, res.size() - extracted + 1) + _getPermutation(nxt, rem);
            } else if (pro == k) {
                // 等于的情况代表正好由一个升序数组和一个降序数组拼接而成
                sort(res.end() - extracted, res.end(), greater<char>());
                return res;
            }
        }
        // 不应该出现的情况
        return "";
    }
    string getPermutation(int n, int k)
    {
        string res;
        for (int i = 0; i < n; ++i)
        {
            res.push_back(i + '1');
        }
        
        // for (int i = 1; i < k; ++i)
        // {
        //     nextPermutation(res);
        // }
        // return res;
        return _getPermutation(res, k);
    }
};
// @lc code=end
