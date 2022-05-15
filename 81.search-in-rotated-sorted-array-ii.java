/*
 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 */

// @lc code=start
class Solution {
    // 这么做会退化成n^2的，不好
    public boolean search(int[] nums, int target) {
        final int n = nums.length, last = n - 1;
        if (n == 1) {
            return target == nums[0];
        }
        int left = 0, right = last;
        // 找最小，如果相等向左搜索更小值，直到左边界
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[last]) {
                left = mid + 1;
                continue;
            } else if (nums[mid] < nums[last]) {
                right = mid;
                continue;
            }
            int cursor = mid;
            while (cursor >= left && nums[cursor] == nums[last]) {
                cursor--;
            }
            if (cursor < left) {
                left = mid + 1;
            } else if (nums[cursor] < nums[last]) {
                right = cursor;
            } else {
                left = mid + 1;
            }
        }
        // [2,2,2,3,2,2,2] 3 用例找到了最小值为2但是没找到正确的2的位置，需要向左遍历
        while (left != 0 && nums[left - 1] == nums[left]) {
            left--;
        }
        final int k = left;
        left = 0;
        right = last;
        while (left <= right) {
            int originalMid = (left + right) / 2;
            int mid = (originalMid + k) % n;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = originalMid - 1;
            } else {
                left = originalMid + 1;
            }
        }
        return false;
    }

}
// @lc code=end

