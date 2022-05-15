/*
 * @lc app=leetcode id=134 lang=java
 *
 * [134] Gas Station
 */

// @lc code=start
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalConsumption = 0;
        final int n = gas.length;
        for (int i= 0; i < n; ++i) {
            totalConsumption += gas[i] - cost[i];
        }
        // 想要完成一次环游加油量至少要大于等于耗油量
        if (totalConsumption < 0) {
            return -1;
        }
        // 如果只有一条可行路径
        // 从这个起点出发油量收益的最大值，如果都不满足剩余相对油耗，那不可能完成环游
        totalConsumption = gas[n - 1] - cost[n - 1];
        int maxRemain = totalConsumption, startIndex = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            totalConsumption += gas[i] - cost[i];
            if (totalConsumption > maxRemain) {
                maxRemain = totalConsumption;
                startIndex = i;
            }
        }
        return startIndex;
    }
}
// @lc code=end

