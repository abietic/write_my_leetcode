import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=188 lang=java
 *
 * [188] Best Time to Buy and Sell Stock IV
 */

// @lc code=start
class Solution {
    // private static class MemoKey {
    //     public final int remainingTrade, curDate;
    //     public final boolean canSell;
    //     public MemoKey(int remainingTrade, int curDate, boolean canSell) {
    //         this.remainingTrade = remainingTrade;
    //         this.curDate = curDate;
    //         this.canSell = canSell;
    //     }
    //     @Override
    //     public int hashCode() {
    //         final int prime = 31;
    //         int result = 1;
    //         result = prime * result + (canSell ? 1231 : 1237);
    //         result = prime * result + curDate;
    //         result = prime * result + remainingTrade;
    //         return result;
    //     }
    //     @Override
    //     public boolean equals(Object obj) {
    //         if (this == obj)
    //             return true;
    //         if (obj == null)
    //             return false;
    //         if (getClass() != obj.getClass())
    //             return false;
    //         MemoKey other = (MemoKey) obj;
    //         if (canSell != other.canSell)
    //             return false;
    //         if (curDate != other.curDate)
    //             return false;
    //         if (remainingTrade != other.remainingTrade)
    //             return false;
    //         return true;
    //     }
        
    // }
    // private static Map<MemoKey, Integer> memo;
    // private static int _maxProfit(int remainingTrade, int[] prices, int curDate, boolean canSell) {
    //     if (remainingTrade <= 0 || curDate >= prices.length) {
    //         return 0;
    //     }
    //     MemoKey key = new MemoKey(remainingTrade, curDate, canSell);
    //     Integer res;
    //     if ((res = memo.get(key)) != null) {
    //         return res;
    //     }
    //     if (canSell) {
    //         int doTheSell = prices[curDate] + _maxProfit(remainingTrade - 1, prices, curDate + 1, false);
    //         int laterSell = _maxProfit(remainingTrade, prices, curDate + 1, true);
    //         res = Math.max(doTheSell, laterSell);
    //     } else {
    //         int doTheBuy = -prices[curDate] + _maxProfit(remainingTrade, prices, curDate + 1, true);
    //         int laterBuy = _maxProfit(remainingTrade, prices, curDate + 1, false);
    //         res = Math.max(doTheBuy, laterBuy);
    //     }
    //     memo.put(key, res);
    //     return res;
    // }
    // public int maxProfit(int k, int[] prices) {
    //     memo = new HashMap<>();
    //     return _maxProfit(k, prices, 0, false);
    // }

    // public int maxProfit(int k, int[] prices) {
    //     final int n = prices.length;
    //     if (k == 0 || n <= 1) {
    //         return 0;
    //     }
    //     int[][][] memo = new int[2][k + 1][prices.length];
    //     for (int i = 1; i <= k; ++i) {
    //         memo[1][i][n - 1] = prices[n - 1];
    //     }
    //     for (int curDate = n - 2; curDate >= 0; --curDate) {
    //         for (int canSell = 0; canSell <= 1; canSell++) {
    //             for (int i = 1; i <= k; ++i) {
    //                 int res = 0;
    //                 if (canSell == 1) {
    //                     int sellNow = prices[curDate] + memo[0][i - 1][curDate + 1];
    //                     int sellLater = memo[1][i][curDate + 1];
    //                     res = Math.max(sellLater, sellNow);
    //                 } else {
    //                     int buyNow = -prices[curDate] + memo[1][i][curDate + 1];
    //                     int buyLater = memo[0][i][curDate + 1];
    //                     res = Math.max(buyNow, buyLater);
    //                 }
    //                 memo[canSell][i][curDate] = res;
    //             }
    //         }
    //     }
    //     return memo[0][k][0];
    // }

    public int maxProfit(int k, int[] prices) {
        final int n = prices.length;
        if (k == 0 || n <= 1) {
            return 0;
        }
        int[] profitAfterBuy = new int[k], profitAfterSell = new int[k];
        Arrays.fill(profitAfterBuy, -1001);
        int maxProfit = 0;
        for (int price : prices) {
            profitAfterBuy[0] = Math.max(profitAfterBuy[0], -price);
            for (int tradeNo = 0; tradeNo < k; ++tradeNo) {
                profitAfterSell[tradeNo] = Math.max(profitAfterSell[tradeNo], profitAfterBuy[tradeNo] + price);
                // maxProfit = Math.max(maxProfit, profitAfterSell[tradeNo]);
                if (tradeNo != k - 1) {
                    profitAfterBuy[tradeNo + 1] = Math.max(profitAfterBuy[tradeNo + 1], profitAfterSell[tradeNo] - price);
                }
            }
        }
        // return maxProfit;
        return profitAfterSell[k - 1];
    }
}
// @lc code=end

