/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int leftCursor = m - 1, rightCursor = m + n - 1, array2Cursor = n - 1;
        while (leftCursor >= 0 && array2Cursor >= 0) {
            if (nums1[leftCursor] > nums2[array2Cursor]) {
                nums1[rightCursor--] = nums1[leftCursor--];
            } else {
                nums1[rightCursor--] = nums2[array2Cursor--];
            }
        }
        while (array2Cursor >= 0) {
            nums1[rightCursor--] = nums2[array2Cursor--];
        }
    }
}
// @lc code=end

