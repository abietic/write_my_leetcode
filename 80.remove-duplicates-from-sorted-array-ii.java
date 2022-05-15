/*
 * @lc app=leetcode id=80 lang=java
 *
 * [80] Remove Duplicates from Sorted Array II
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int place = 0, rec = 1, counter = 1;
        for (; rec < nums.length;++rec) {
            if (nums[place] == nums[rec]) {
                if (counter < 2) {
                    counter++;
                    nums[++place] = nums[rec];
                } 
                continue;
            }
            nums[++place] = nums[rec];
            counter = 1;
        }
        return place + 1;
    }
}
// @lc code=end

