/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    // public void rotate(int[] nums, int k) {
    // // 使用常数额外空间的方法，还没有弄明白数学原理
    // final int numLen = nums.length;
    // k %= numLen;
    // if (k == 0) {
    // return;
    // }
    // int moveCnt = 0;
    // for (int startIdx = 0; moveCnt < numLen; ++startIdx) {
    // int iter = (startIdx + k) % numLen, cur = nums[startIdx], nxt;
    // while (iter != startIdx) {
    // nxt = nums[iter];
    // nums[iter] = cur;
    // cur = nxt;
    // moveCnt++;
    // iter = (iter + k) % numLen;
    // // [-1,-100,3,99]
    // }
    // nums[startIdx] = cur;
    // moveCnt++;
    // }
    // }
    public void rotate(int[] nums, int k) {
        final int numLen = nums.length;
        k %= numLen;
        if (k == 0) {
            return;
        }
        int[] rotated = new int[numLen];
        for (int i = 0; i < numLen; ++i) {
            int to = (i + k) % numLen;
            rotated[to] = nums[i];
        }
        for (int i = 0; i < numLen; ++i) {
            nums[i] = rotated[i];
        }
    }
}
// @lc code=end
