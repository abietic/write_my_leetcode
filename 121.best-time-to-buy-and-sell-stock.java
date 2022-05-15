/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int curMin = prices[0];
        int curProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (curMin > prices[i]) {
                curMin = prices[i];
            } else if (prices[i] - curMin > curProfit){
                curProfit = prices[i] - curMin;
            }
        }
        return curProfit;
    }
}
// @lc code=end

