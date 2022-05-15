import java.util.HashMap;

/*
 * @lc app=leetcode id=137 lang=java
 *
 * [137] Single Number II
 */

// @lc code=start
class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 想要用位操作找到unique数
        // 就要构造其他数出现次数为偶数，unique数出现次数为奇数的情形
        // 因为数组中的元素有出现次数上的规律 3n+1
        // 说明除了目标数字以外的数字统计它们每个位上出现1的个数时一定是3的倍数
        // 直接统计每个位1的出现次数，如果是3的倍数，说明目标数字在这一位上没有置一
        int[] bitCount = new int[Integer.SIZE];
        for (int n : nums) {
            for (int i = 0; i < Integer.SIZE; ++i) {
                if ((n & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        int target = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            if (bitCount[i] % 3 == 1) {
                target |= 1 << i;
            }
        }
        return target;
    }
}
// @lc code=end
