
/*
 * @lc app=leetcode id=204 lang=java
 *
 * [204] Count Primes
 */

// @lc code=start
class Solution {
    // public int countPrimes(int n) {
    // // 超时，因为是O(n^2)的计算复杂度了
    // // 合数都是由质数的乘积得到的
    // int[] primes = new int[n];
    // int count = 0;
    // for (int num = 2; num < n; ++num) {
    // boolean isPrime = true;
    // for (int i = 0; i < count; ++i) {
    // if (num % primes[i] == 0) {
    // isPrime = false;
    // break;
    // }
    // }
    // if (isPrime) {
    // primes[count++] = num;
    // }
    // }
    // return count;
    // }
    public int countPrimes(int n) {
        // 质数筛选算法：The Sieve of Eratosthenes
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (!notPrime[i]) {
                count++;
                // for (long j = 2; i * j < n; ++j) {
                for (long j = i; i * j < n; ++j) { // 不需要每次从最小的数开始乘起，因为之前的乘法在较小的质数扩展时已经被访问过了
                    notPrime[(int) (i * j)] = true;
                }
            }
        }
        return count;
    }
}
// @lc code=end
