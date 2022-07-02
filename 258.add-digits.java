/*
 * @lc app=leetcode id=258 lang=java
 *
 * [258] Add Digits
 */

// @lc code=start
class Solution {
    // public int addDigits(int num) {
    // // 先用最直观的方法做
    // // 如何不用递归或循环呢？
    // // if (num < 10) {
    // // return num;
    // // }
    // while (num >= 10) {
    // int nxt = 0;
    // while (num != 0) {
    // nxt += num % 10;
    // num /= 10;
    // }
    // num = nxt;
    // }
    // // return addDigits(nxt);
    // return num;
    // }
    public int addDigits(int num) {
        // 首先数值在变化中一定是逐渐减小的?
        // 其次得出的结果一定在0-9这十个数中产生
        // 首先0只能在原数也为0时出现
        // 接下来要总结存在的规律 10-18 会被映射到结果 1-9
        //                      19-27 会被映射到结果 1-9
        //                      28-36 会被映射到结果 1-9
        //                      37-45 会被映射到结果 1-9
        //                      .
        //                      .
        //                      .
        //                      100-108 会被映射到结果 1-9
        // 总结来说应该是，映射的结果1-9应该会不断地循环出现，因此
        // 只要减10求余9应该就能得到
        // if (num < 10) {
        //     return num;
        // }
        // return ((num - 10) % 9) + 1;
        // 又因为个位数 1-9 也满足上述要求，并且正好紧接着就是10，因此其实除了0要特殊处理外，其他的直接减1求余9再加1就行了
        return (num == 0) ? 0 : ((num-1) % 9 + 1);
    }
}
// @lc code=end
