import java.util.Arrays;

import javax.swing.text.AbstractDocument.BranchElement;

/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {
    // public int minSubArrayLen(int target, int[] nums) {
    // // 计算复杂度O(n)，思路就是两个指针分别表示范围
    // // 当范围内的序列和不足target时右侧指针前进
    // // 当范围内的序列和大于target时，尝试更新满足条件的最短序列长度，并前进左指针
    // final int n = nums.length;
    // int left, right, curSum, minLen = Integer.MAX_VALUE;
    // for (left = 0, right = 0, curSum = 0; right < n; ++right) {
    // curSum += nums[right];
    // while (curSum >= target) {
    // minLen = Math.min(minLen, right - left + 1);
    // if (left < right) {
    // curSum -= nums[left++];
    // } else {
    // break;
    // }
    // }
    // if (minLen == 1) {
    // break;
    // }
    // }
    // return minLen == Integer.MAX_VALUE ? 0 : minLen;
    // }
    private static int upperBoundUsingBiSearch(int[] nums, int left, int right, int from) {
        // 找升序序列中小于等于from中最大的
        int idx = Arrays.binarySearch(nums, left, right + 1, from);
        if (idx >= 0) {
            return idx;
        }
        idx = -(idx + 1) - 1;
        return idx;
    }
    public int minSubArrayLen(int target, int[] nums) {
        // 计算给出数组的前len个元素的和
        // 这样根据题意可以得到一个单调递增序列
        // 并且subLenSum[j] - subLenSum[i] == nums[i] + nums[i + 1] + ... + nums[j - 1]
        int[] subLenSum = new int[nums.length + 1];
        for (int len = 1; len <= nums.length; ++len) {
            subLenSum[len] = subLenSum[len - 1] + nums[len - 1];
        } 
        int minLen = Integer.MAX_VALUE;
        // 通过subLenSum前后元素相减能得到子序列和，因此可以找到正好大于等于target的序列
        for (int len = 1; len <= nums.length; ++len) {
            if (subLenSum[len] < target) {
                continue;
            }
            // 要subLenSum[j] - subLenSum[i] >= target && subLenSum[j] - subLenSum[i + 1] < target
            // 处理不等式得subLenSum[j] - target >= subLenSum[i] && subLenSum[j] - target < subLenSum[i + 1]
            int leftSumExpect = subLenSum[len] - target;
            int leftLen = upperBoundUsingBiSearch(subLenSum, 0, len - 1, leftSumExpect);
            minLen = Math.min(minLen, len - leftLen);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
// @lc code=end
