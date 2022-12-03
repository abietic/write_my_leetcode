/*
 * @lc app=leetcode id=213 lang=java
 *
 * [213] House Robber II
 */

// @lc code=start
class Solution {
    // public int rob(int[] nums) {
    // if (nums.length <= 2) {
    // return nums.length == 1 ? nums[0] : Math.max(nums[0], nums[1]);
    // }
    // // 与之前的区别是，首位是否相邻
    // // 那么最直观的想法是，计算一定选择第一个，一定选择最后一个，和一定两个都不选中的最大值
    // int[] definitFirst = new int[nums.length];
    // definitFirst[1] = nums[0];
    // definitFirst[2] = nums[0];
    // for (int len = 3; len < nums.length; ++ len) {
    // definitFirst[len] = Math.max(definitFirst[len - 2] + nums[len - 1],
    // definitFirst[len - 1]);
    // }
    // final int definitFirstMax = definitFirst[nums.length - 1];
    // int[] definitLast = new int[nums.length];
    // definitLast[1] = nums[nums.length - 1];
    // definitLast[2] = nums[nums.length - 1];
    // for (int len = 3, idx = nums.length - 3; len < nums.length; ++len, --idx) {
    // definitLast[len] = Math.max(definitLast[len - 2] + nums[idx], definitLast[len
    // - 1]);
    // }
    // final int definitLastMax = definitLast[nums.length - 1];
    // int[] bothNotChosen = new int[nums.length];
    // for (int len = 2; len < nums.length; ++len) {
    // bothNotChosen[len] = Math.max(bothNotChosen[len - 2] + nums[len - 1],
    // bothNotChosen[len - 1]);
    // }
    // final int bothNotChosenMax = bothNotChosen[nums.length - 1];
    // return Math.max(bothNotChosenMax, Math.max(definitFirstMax, definitLastMax));
    // }

    // public int rob(int[] nums) {
    //     if (nums.length <= 2) {
    //         return nums.length == 1 ? nums[0] : Math.max(nums[0], nums[1]);
    //     }
    //     // 可以看出3次循环可以合并为一个循环计算
    //     int lenMinus2Max, lenMinus1Max, curLenMax;
    //     lenMinus2Max = lenMinus1Max = curLenMax = nums[0];
    //     for (int len = 3, idx = 2; len < nums.length; ++len, ++idx) {
    //         curLenMax = Math.max(lenMinus2Max + nums[idx], lenMinus1Max);
    //         lenMinus2Max = lenMinus1Max;
    //         lenMinus1Max = curLenMax;
    //     }
    //     final int definitFirstMax = curLenMax;
    //     lenMinus2Max = lenMinus1Max = curLenMax = nums[nums.length - 1];
    //     for (int len = 3, idx = nums.length - 3; len < nums.length; ++len, --idx) {
    //         curLenMax = Math.max(lenMinus2Max + nums[idx], lenMinus1Max);
    //         lenMinus2Max = lenMinus1Max;
    //         lenMinus1Max = curLenMax;
    //     }
    //     final int definitLastMax = curLenMax;
    //     lenMinus1Max = lenMinus2Max = curLenMax = 0;
    //     for (int len = 2, idx = 1; len < nums.length; ++len, ++idx) {
    //         curLenMax = Math.max(lenMinus2Max + nums[idx], lenMinus1Max);
    //         lenMinus2Max = lenMinus1Max;
    //         lenMinus1Max = curLenMax;
    //     }
    //     final int bothNotChosenMax = curLenMax;
    //     return Math.max(bothNotChosenMax, Math.max(definitFirstMax, definitLastMax));
    // }


    private static int _originRob(int[] nums, int start, int numslen) {
        int lenMinus1Max = nums[start], lenMinus2Max = 0, curLenMax = nums[start];
        for (int len = 2, idx = start + 1; len <= numslen; ++len, ++idx) {
            curLenMax = Math.max(lenMinus2Max + nums[idx], lenMinus1Max);
            lenMinus2Max = lenMinus1Max;
            lenMinus1Max = curLenMax;
        }
        return curLenMax;
    }

    public int rob(int[] nums) {
        // 上面所述的三种情况可以简化为两种情况
        // 即去掉首的原始情况和去掉尾的原始情况
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(_originRob(nums, 1, nums.length - 1), _originRob(nums, 0, nums.length - 1));
    }
}
// @lc code=end
