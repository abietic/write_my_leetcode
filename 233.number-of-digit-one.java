/*
 * @lc app=leetcode id=233 lang=java
 *
 * [233] Number of Digit One
 */

// @lc code=start
class Solution {
    // 新的方法将所有的数值按位判断
    public int digitCounts(int k, int n) {
        // write your code here
        if (n < 10) {
            return n < k ? 0 : 1;
        }
        long mask = 1;
        int res = 0;
        while (mask <= n) {
            int curDigit = (n / (int)mask) % 10;
            int numHigherThanThisDigit = n / (int)mask / 10;
            int numLowerThanThisDigit = n % (int)mask;
            if (k != 0) {
                if (curDigit < k) {
                    // 如果当前位在最大值n上没有到达k,那么只能取其之前能达到k时的所有数值得个数之和(numHigherThanThisDigit - 1 + 1)减一是因为更大的数的那位小于k,加一是因为0也算一位占位
                    res += numHigherThanThisDigit * mask;
                } else if (curDigit == k) {
                    res += numHigherThanThisDigit * mask + numLowerThanThisDigit + 1;
                } else {
                    res += (numHigherThanThisDigit + 1) * mask;
                }
            } else {
                if (curDigit == 0) {
                    res += Math.max(numHigherThanThisDigit - 1, 0) * mask + numLowerThanThisDigit + 1;
                } else {
                    res += numHigherThanThisDigit * mask;
                }
            }
            mask *= 10;
        }
        return res + (k == 0 ? 1 : 0);
    }
    public int countDigitOne(int n) {
        return digitCounts(1, n);
        // // 观察可知, 每增长一位, 之前出现过的所有小一位的数字中有1的就都可以再出现一次
        // // 还有由于进位产生的第一个字, 1, 10, 100, 1000 ......
        // // 首先看给出的n有几位, 1位数有一个数字1存在,即1
        // // 2位数有20个数字1存在,即 1, 1开头的10-19(其中11含有两个1), 2-9开头的一位数有1的
        // // 3位数有300个数字1存在, 即所有0-9开头的所有两位数1的含量(10*20),再加上第三位的有1的100-199(100)
        // // 4位300*10+1000=4000,5位4000*10+10000=50000....
        // if (n < 10) {
        //     return n == 0 ? 0 : 1;
        // } 
        // return countDigitOneRecursice(n, 9);
    }
    // private int countDigitOneRecursice(int n, int dig) {
    //     if (dig == 0) {
    //         return 0;
    //     }
    //     int msk = (int)Math.pow(10, dig);
    //     int digNum = n / msk;
    //     if (digNum == 0) {
    //         return countDigitOneRecursice(n, dig - 1);
    //     } 
    //     if (digNum == 1) {
    //         return calDigs(dig - 1) + (n % msk)+1 + countDigitOne(n % msk);
    //     }
    //     int sumOneDigs = msk + calDigs(dig - 1);
    //     for (int cur = 1; cur < digNum; ++cur) {
    //         sumOneDigs += calDigs(dig - 1);
    //     }
    //     return sumOneDigs + countDigitOne(n % msk);
    // }
    // private static int calDigs(int digCnt) {
    //     if (digCnt == -1) {
    //         return 0;
    //     }
    //     if (digCnt == 0) {
    //         return 1;
    //     }
    //     return (digCnt+1) * (int)Math.pow(10, digCnt);
    // }
}
// @lc code=end

