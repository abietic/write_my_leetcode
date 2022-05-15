/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int curHold = prices[0];
        int curProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] < curHold) {
                curHold = prices[i];
            } else {
                curProfit += prices[i] - curHold;
                curHold = prices[i];
            }
        }
        return curProfit;
    }
}
// @lc code=end

