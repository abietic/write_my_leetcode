import java.util.Arrays;
/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input Array Is Sorted
 */

// @lc code=start
class Solution {
    // private static int biSearch(int[] nums, int left, int right, int target) {
    //     while (left <= right) {
    //         int mid = (left + right) / 2;
    //         if (target == nums[mid]) {
    //             return mid;
    //         } else if (target > nums[mid]) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return -1;
    // }
    // public int[] twoSum(int[] numbers, int target) {
    //     int[] res = {0, 0};
    //     for (int i = 0; i < numbers.length - 1; ++i) {
    //         res[0] = i + 1;
    //         res[1] = biSearch(numbers, i + 1, numbers.length - 1, target - numbers[i]) + 1;
    //         if (res[1] > 0) {
    //             break;
    //         }
    //     }
    //     return res;
    // }
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        int[] res = {0, 0};
        while (left < right) {
            int val = numbers[left] + numbers[right];
            if (val == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            } else if (val > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
// @lc code=end

