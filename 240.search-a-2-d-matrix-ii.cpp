/*
 * @lc app=leetcode id=240 lang=cpp
 *
 * [240] Search a 2D Matrix II
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
        if (matrix.empty())
        {
            return false;
        }
        return _searchMatrix(matrix, target, 0, 0, matrix.size() - 1, matrix[0].size() - 1);
    }

private:
    bool _searchMatrix(vector<vector<int>> &mat, int tar, int ul_r, int ul_c, int lr_r, int lr_c)
    {
        if (ul_r < 0 || ul_r > lr_r || ul_c < 0 || ul_c > lr_c) // 不再是矩阵
        {
            return false;
        }
        if (ul_r == lr_r) // 只有一行
        {
            if (ul_c == lr_c)
            {
                return mat[lr_r][lr_c] == tar;
            }
            else
            {
                return _bsearch(mat[lr_r], tar, ul_c, lr_c);
            }
        }
        if (ul_c == lr_c) // 只有一列
        {
            return _bsearch_c(mat, tar, ul_c, ul_r, lr_r);
        }
        if (tar > mat[lr_r][lr_c] || tar < mat[ul_r][ul_c]) // 比矩阵中的最大值大或比最小值小
        {
            return false;
        }
        int mid_r = (lr_r + ul_r) >> 1, mid_c = (lr_c + ul_c) >> 1;
        if (tar != mat[mid_r][mid_c])
        {
            if (_searchMatrix(mat, tar, ul_r, mid_c + 1, mid_r, lr_c) || _searchMatrix(mat, tar, mid_r + 1, ul_c, lr_r, mid_c))
            {
                return true;
            }
            if (tar < mat[mid_r][mid_c])
            {
                return _searchMatrix(mat, tar, ul_r, ul_c, mid_r, mid_c);
            }
            else
            {
                return _searchMatrix(mat, tar, mid_r + 1, mid_c + 1, lr_r, lr_c);
            }
        }
        else
        {
            return true;
        }
    }

    bool _bsearch(vector<int> &nums, int target, int from, int to)
    {
        // if (from < 0 || from > to) {
        //     return false;
        // }
        // if (from == to) {
        //     return nums[from] == target;
        // }
        // while (from >= 0 && from <= to) {
        //     if (from == to) {
        //         return nums[from] == target;
        //     }
        //     int mid = (from + to) >> 1;
        //     if (nums[mid] > target) {
        //         to = mid - 1;
        //     } else if (nums[mid] < target) {
        //         from = mid + 1;
        //     } else {
        //         return true;
        //     }
        // }
        // return false;
        return binary_search(nums.begin() + from, nums.begin() + to + 1, target);
    }
    bool _bsearch_c(vector<vector<int>> &mat, int target, int col, int from, int to)
    {
        // if (from < 0 || from > to) {
        //     return false;
        // }
        // if (from == to) {
        //     return mat[from][col] == target;
        // }
        // while (from >= 0 && from <= to) {
        //     if (from == to) {
        //         return mat[from][col] == target;
        //     }
        //     int mid = (from + to) >> 1;
        //     if (mat[mid][col] > target) {
        //         to = mid - 1;
        //     } else if (mat[mid][col] < target) {
        //         from = mid + 1;
        //     } else {
        //         return true;
        //     }
        // }
        // return false;
        auto bound = lower_bound(mat.begin() + from, mat.begin() + to + 1, target, [=](const vector<int> &vec, const int &t)
                             { return vec[col] < t; });
        return (bound == (mat.begin() + to + 1)) ? false : ((*bound)[col] == target);
    }
};
// @lc code=end
