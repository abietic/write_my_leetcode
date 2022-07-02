/*
 * @lc app=leetcode id=191 lang=java
 *
 * [191] Number of 1 Bits
 */

// @lc code=start
class Solution {
    // // you need to treat n as an unsigned value
    // public int hammingWeight(int n) {
    //     int count = 0;
    //     while (n != 0) {
    //         count++;
    //         // 这个操作将整数的最后一个1bit置位为0，这个位后面的bit全部置1 --> (n - 1)
    //         // 然后通过和原数做与就可以将尾部新生成1去掉，同时之前最后一个1bit的位置也被置0
    //         // n - 1 产生的现象对于负数也成立
    //         // 例如：1010为-6进行减一操作，即与-1 1111相加得到的数值，-7 1001也符合这个规律
    //         // 对于负数溢出的情况同样使用 -8 1000  -->  0111
    //         int tmp = n & (n - 1);
    //         n = tmp;
    //     }
    //     return count;
    // }

    public int hammingWeight(int n) {
        // 这是一种直接在原数据上记录1bit个数的方法
        // 对于两位数一共有4种组合 00,01,10,11 分别对应置1个数 00,01,01,10
        // 由此可知将高位右移加到低位上即可得到两位的1bit数，这样就可以在原数字上计算1bit的个数了
        int _2Count = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        // 有了两位时的1bit数，这时数字中留下的就不再是位而是而是指定范围内的1bit个数了，再通过不断合并统计范围得到最终统计结果
        int _4Count = (_2Count & 0x33333333) + ((_2Count >>> 2) & 0x33333333);
        int _8Count = (_4Count & 0x0f0f0f0f) + ((_4Count >>> 4) & 0x0f0f0f0f);
        int _16Count = (_8Count & 0x00ff00ff) + ((_8Count >>> 8) & 0x00ff00ff);
        int _32Count = (_16Count & 0x0000ffff) + ((_16Count >>> 16) & 0x0000ffff);
        return _32Count;
        // 由于统计1bit数的数字占位范围在统计范围增大后会小于统计范围的占位，可以通过这个特性再优化掉一些位操作
    }
}
// @lc code=end

