/*
 * @lc app=leetcode id=172 lang=java
 *
 * [172] Factorial Trailing Zeroes
 */

// @lc code=start
class Solution {
    public int trailingZeroes(int n) {
        // 由于尾部0是由因数10带来的
        // 10是由因数2和因数5乘积得到的
        // 因数2的数量一定比因数5的数量多
        // 所以只需要找到阶乘中所有因数5的数量
        // 如何计算阶乘中含有因数5的个数
        // 首先阶乘中，每5个数就会有一个5的倍数
        // 之后可以发现，每5^n个数都会额外再增加一个因数5
        int totalFiveCount = 0;
        while (n!= 0) {
            totalFiveCount += n / 5; // 先得出5的倍数的个数，接下类是25、125……5^n倍数的个数
            n /= 5;
        }
        return totalFiveCount;
    }
}
// @lc code=end

