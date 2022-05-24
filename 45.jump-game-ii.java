/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */

// @lc code=start
class Solution {
    // public int jump(int[] nums) {
    // final int n = nums.length;
    // int[] memo = new int[n + 1];
    // for (int i = n - 2; i >= 0; --i) {
    // // 因为下面比较时有加一操作，不能直接用max
    // memo[i] = Integer.MAX_VALUE - 1;
    // for (int j = 0; j < nums[i] && i + 1 + j < n; ++j) {
    // memo[i] = Math.min(memo[i], memo[i + 1 + j] + 1);
    // }
    // }
    // return memo[0];
    // }

    public int jump(int[] nums) {
        // 贪心？
        // 每次都选择可以使前进可及性达到最大的走法
        // 算法正确性？
        if (nums.length == 1) {
            return 0;
        }
        int maxReach = 0, step = 0, cur = 0;
        while (maxReach < nums.length - 1) {
            int nxtCand = cur;
            for (int pace = 0; pace < nums[cur]; ++pace) {
                int nxt = pace + cur + 1;
                if (nxt >= nums.length - 1) {
                    return step + 1;
                }
                // 这一步和下一步加起来的最大可能跨度
                // 这个值由当前步选择的跨度和其跨到的位置上的允许跨度相加得到
                // 可以证明选择最大的跨度一定能找到最优解
                // 用反证法，首先计算这个跨度假设进行了两次跨步
                // 如果最优的跨法计算的得到的跨度和小于最大跨度和的跨法
                // 那么说明要么1.这种非最大和的最优跨法成功走到最后
                // 要么2.最优跨法在下一跨的可选位置中存在最优解需要到达的位置
                // 那么对于最大和的跨法满足条件1.因为跨度和比最优解大一定可以同样两步到达
                // 2.由于跨度和更大，下一步的可选范围一定包含在最大和的跨法内
                if (maxReach < nxt + nums[nxt]) {
                    // 跨度和相等的时候，选第一步跨度小的
                    // 保证下一步的可选位置数量
                    nxtCand = nxt;
                    maxReach = nxt + nums[nxt];
                }
            }
            cur = nxtCand;
            step++;
        }
        return step + 1;
    }
}
// @lc code=end
