/*
 * @lc app=leetcode id=1201 lang=java
 *
 * [1201] Ugly Number III
 */

// @lc code=start
class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        // 由于题目的限制条件里给出了值的取值范围
        // 通过最大值范围内包含几个符合条件的数
        // 通过二分搜索找到目标位置的符合条件的数字
        long left = 1, right = 2 * 1000000000, result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long includeCount = _countUnglyNumbers(mid, a, b, c);
            if (includeCount >= n) {
                result = mid; // 这里大于和等于都更新result和right
                right = mid - 1; // 这是因为即使在当前mid数字内包含的合规数字满足条件，不代表mid本身是合规数字，需要让它逼近它能到达的最小值
            } else {
                left = mid + 1;
            }
        }
        return (int)result;
    }

    private static long _countUnglyNumbers(long num, long a, long b, long c) {
        return num / a // 含有a倍数的数量
                + num / b // 含有b倍数的数量
                + num / c // 含有c倍数的数量
                - num / _lcm(a, b) // 减去重复加的a与b公倍数的数量
                - num / _lcm(a, c) // 减去重复加的a与c公倍数的数量
                - num / _lcm(b, c) // 减去重复加的b与c公倍数的数量
                + num / _lcm(a, _lcm(b, c)); // 加上重复减的a、b、c的公倍数
    }

    private static long _lcm(long a, long b) {
        return a * (b / _gcd(a, b));
    }

    private static long _gcd(long a, long b) {
        long tmp;
        if (a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        while (a != 0) {
            tmp = b % a;
            b = a;
            a = tmp;
        }
        return b;
    }
}
// @lc code=end
