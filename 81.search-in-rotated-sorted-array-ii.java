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

    // 上边的性能反倒更好
    // public boolean search(int[] nums, int target) {
    //     int left = 0, right = nums.length - 1;
    //     // 跳过最左边的重复元素
    //     while (left < right && nums[left] == nums[left + 1]) {
    //         left++;
    //     }
    //     // 如果全是重复元素
    //     if (left == right) {
    //         return nums[0] == target;
    //     }
    //     // 跳过最右边的重复元素
    //     while (right > left && (nums[right] == nums[right - 1] || nums[right] == nums[left])) {
    //         right--;
    //     }
    //     // 剩下的数组长度和起始位置
    //     final int neoLen = right - left + 1, neoBase = left;
    //     // 找到剩下数组中的最小值
    //     int rightMost = nums[right];
    //     while (left < right) {
    //         int mid = (left + right) / 2;
    //         if (nums[mid] > rightMost) {
    //             left = mid + 1;
    //         } else {
    //             right = mid;
    //         }
    //     }
    //     // 最小值最早出现的位置
    //     while (left > neoBase && nums[left] == nums[left - 1]) {
    //         left--;
    //     }
    //     final int rotate = left - neoBase;
    //     left = 0;
    //     right = neoLen - 1;
    //     while (left <= right) {
    //         int originMid = (left + right) / 2;
    //         int nowMid = ((originMid + rotate) % neoLen) + neoBase;
    //         if (nums[nowMid] == target) {
    //             return true;
    //         } else if (nums[nowMid] < target) {
    //             left = originMid + 1;
    //         } else {
    //             right = originMid - 1;
    //         }
    //     }
    //     return false;
    // }
}
// @lc code=end

