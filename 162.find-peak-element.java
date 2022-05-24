/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    // // 在这里的限制条件中，保证了两个相邻元素不相等（除-1和n），这保证了一定会有解
    // // 用暴力方法，O(n)
    // public int findPeakElement(int[] nums) {
    // for (int i = 0; i < nums.length; ++i) {
    // long left = 0, right = 0, cur = nums[i];
    // if (i == 0) {
    // left = Long.MIN_VALUE;
    // } else {
    // left = nums[i - 1];
    // }
    // if (i == nums.length - 1) {
    // right = Long.MIN_VALUE;
    // } else {
    // right = nums[i + 1];
    // }
    // if (cur > left && cur > right) {
    // return i;
    // }
    // }
    // return nums.length;
    // }

    public int findPeakElement(int[] nums) {
        // 可能向学习高数时找极值点一样，用类似二分的方法，取左中右三个点通过大小关系比较
        final int n = nums.length;
        // 只有一个元素，是否有峰在边界
        if (n == 1 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        // 要找nums[x] > nums[x + 1] && nums[x] > nums[x - 1]的元素
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // left和right的内侧元素都是比left,right对应的元素大
            // 左右两个边界内侧都是一个上坡才能保证内部至少有一个峰
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                // 当前的mid就是一个峰
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                // (nums[mid] < nums[mid - 1])
                right = mid;
            }
        }
        // 因为一定有解不应该到这里
        return -1;
    }
}
// @lc code=end
