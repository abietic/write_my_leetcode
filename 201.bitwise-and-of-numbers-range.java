/*
 * @lc app=leetcode id=201 lang=java
 *
 * [201] Bitwise AND of Numbers Range
 */

// @lc code=start
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // 数据范围为正整数
        // 位操作AND的特点是，只有参与AND操作的所有元素在指定位上全部为1时才得1
        // 因此在范围所有发生变动的位，都会不符合要求，留下未发生变动的前缀
        int mask = 1 << 31, i;
        // 记录没有改变的前缀的长度
        for (i = 0; i < 32; ++i,mask >>>= 1) {
            int lm = left & mask, rm = right & mask;
            if (lm != rm) {
                break;
            }
        }
        // 为了只留下未改变过的前缀，生成一个mask
        mask = ~((1 << (32 - i)) - 1);
        return (left & right) & mask;
    }
}
// @lc code=end

