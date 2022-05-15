/*
 * @lc app=leetcode id=154 lang=java
 *
 * [154] Find Minimum in Rotated Sorted Array II
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 将左侧的重复跳过
        while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
            left++;
        }
        // 如果所有元素都是一个返回这个元素就行
        if (left == nums.length - 1) {
            return nums[0];
        }
        // 将右侧的重复元素跳过
        while (nums[right] == nums[left] || nums[right] == nums[right - 1]) {
            right--;
        }
        // 这时没有元素会与当前的nums[rightBound]重复了可以用回不重复时的方法了
        final int rightBound = right;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[rightBound]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
// @lc code=end

