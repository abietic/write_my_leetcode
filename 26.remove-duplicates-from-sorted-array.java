/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int place = 0, rec = 1;
        for (; rec < nums.length; ++rec) {
            if (nums[place] == nums[rec]) {
                continue;
            }
            nums[++place] = nums[rec];
        }
        return place + 1;
    }
}
// @lc code=end

