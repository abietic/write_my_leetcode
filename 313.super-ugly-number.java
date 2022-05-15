import java.util.Arrays;

/*
 * @lc app=leetcode id=313 lang=java
 *
 * [313] Super Ugly Number
 */

// @lc code=start
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] memo = new int[n];
        memo[0] = 1;
        int[] t = new int[primes.length];
        for (int i = 1; i < n; ++i) {
            memo[i] = primes[0] * memo[t[0]];
            for (int j = 1; j < primes.length; ++j) {
                memo[i] = Math.min(memo[i], primes[j] * memo[t[j]]);
            }
            for (int j = 0; j < primes.length; ++j) {
                if (memo[i] == primes[j] * memo[t[j]]) {
                    t[j]++;
                }
            }
        }
        return memo[n - 1];
    }
}
// @lc code=end

