/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */

// @lc code=start
class Solution {
    final static int RED = 0, WHITE = 1, BLUE = 2;
    public void sortColors(int[] nums) {
        int redPointer = 0, whitePointer = 0, bluePointer = nums.length - 1;
        while (whitePointer <= bluePointer) {
            if (nums[whitePointer] == RED) {
                nums[whitePointer] = nums[redPointer];
                nums[redPointer] = RED;
                whitePointer++;
                redPointer++;
                continue;
            }
            if (nums[whitePointer] == WHITE) {
                whitePointer++;
                continue;
            }
            if (nums[whitePointer] == BLUE) {
                nums[whitePointer] = nums[bluePointer];
                nums[bluePointer] = BLUE;
                bluePointer--;
                continue;
            }
        }
    }
    // public void sortColors(int[] nums) {
    //     final int RED = 0, WHITE = 1, BLUE = 2;
    //     int[] bucket = new int[3];
    // }
}
// @lc code=end

