/*
 * @lc app=leetcode id=377 lang=java
 *
 * [377] Combination Sum IV
 */

// @lc code=start
class Solution {
    // 这个递归暴力解应该是正确的,但是会超时
    private static void comboRec(int[] nums, final int target, int cur, int[] res) {
        if (cur == target) {
            res[0]++;
            return;
        }
        for (int n : nums) {
            if (n + cur <= target) {
                comboRec(nums, target, cur + n, res);
            }
        }
    }
    public int combinationSum4(int[] nums, int target) {
        // f(t) = sum {f(a) * f(b) | a + b == t} 这个式子是错误的,因为会重复计数一些序列
        // 比如说[1,2,3] 4中给出的结果是14而正确结果是7
        // 下面是重复的情形
        // 1 : (1)
        // 2 : (1, 1) | (2)
        // 3 : (1, 1, 1) (1, 2) | (1, 1, 1) (2, 1) | (3)
        // 4 : (1, 1, 1, 1) (1, 1, 2) (1, 1, 1, 1) (1, 2, 1) (1, 3) | (1, 1, 1, 1) (1, 1, 2) (2, 1, 1) (2, 2) | (1, 1, 1, 1) (1, 2, 1) (1, 1, 1, 1) (2, 1, 1) (3, 1)
        // f(t) =  sum {f(a) | a + b == t && b in nums} 这个式子是与上面的递归方法是一致的,只是加入了记忆,也就是下面的方法
        int[] memo = new int[1001];
        for (int n : nums) {
            memo[n] = 1;
        }
        for (int i = 1; i <= target; ++i) {
            for (int n : nums) {
                if (i - n >= 0) {
                    memo[i] += memo[i - n];
                }
            }
        }
        return memo[target];
    }
}
// @lc code=end

