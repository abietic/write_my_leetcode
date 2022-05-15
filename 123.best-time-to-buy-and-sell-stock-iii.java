import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */

// @lc code=start
class Solution {
    // private static final class Slope {
    // final int buyDate, buyPrice, profitLoss;
    // int sellDate, sellPrice, profit;
    // Slope prev, next;

    // public Slope(int buyDate, int sellDate, int[] prices, Slope prev) {
    // this.buyDate = buyDate;
    // this.sellDate = sellDate;
    // buyPrice = prices[buyDate];
    // sellPrice = prices[sellDate];
    // profit = sellPrice - buyPrice;
    // this.prev = prev;
    // if (prev == null) {
    // profitLoss = 0;
    // } else {
    // this.prev.next = this;
    // profitLoss = this.prev.sellPrice - this.buyPrice;
    // }
    // }

    // public boolean mergeWithPreviousSlope() {
    // if (prev == null) {
    // return false;
    // }
    // if (profitLoss <= 0) {
    // return false;
    // }
    // final int newProfit = sellPrice - prev.buyPrice;
    // if (newProfit >= profit && newProfit >= prev.profit) {
    // prev.next = next;
    // if (next != null) {
    // next.prev = prev;
    // }
    // prev.sellDate = sellDate;
    // prev.sellPrice = sellPrice;
    // prev.profit = newProfit;
    // return true;
    // }
    // return false;
    // }
    // }

    // private static final class ProfitComparator implements Comparator<Slope> {

    // @Override
    // public int compare(Solution.Slope arg0, Solution.Slope arg1) {
    // // TODO Auto-generated method stub
    // return arg1.profit - arg0.profit;
    // }

    // }

    // private static final class ProfitLossComparator implements Comparator<Slope>
    // {

    // @Override
    // public int compare(Solution.Slope arg0, Solution.Slope arg1) {
    // // TODO Auto-generated method stub
    // return arg0.profitLoss - arg1.profitLoss;
    // }

    // }

    // private PriorityQueue<Slope> minProfitLossHeap, maxProfitHeap;
    // private int slopeCount;

    // private Slope buildSlopList(int[] prices) {
    // Slope cur = null, head = null;
    // int fromIndex = 0, toIndex = 0;
    // for (int i = 1; i < prices.length; ++i) {
    // if (prices[i] >= prices[toIndex]) {
    // // 单调非减
    // toIndex++;
    // } else {
    // if (fromIndex != toIndex && prices[toIndex] - prices[fromIndex] > 0) {
    // Slope newSlope = new Slope(fromIndex, toIndex, prices, cur);
    // cur = newSlope;
    // minProfitLossHeap.add(newSlope);
    // maxProfitHeap.add(newSlope);
    // slopeCount++;
    // if (head == null) {
    // head = newSlope;
    // }
    // }
    // toIndex++;
    // fromIndex = toIndex;
    // }
    // }
    // if (fromIndex != toIndex && prices[toIndex] - prices[fromIndex] > 0) {
    // Slope newSlope = new Slope(fromIndex, toIndex, prices, cur);
    // cur = newSlope;
    // minProfitLossHeap.add(newSlope);
    // maxProfitHeap.add(newSlope);
    // slopeCount++;
    // if (head == null) {
    // head = newSlope;
    // }
    // }
    // return head;
    // }

    // private int curBestTrade() {
    // int totalProfit = 0;
    // if (maxProfitHeap.size() < 1) {
    // return 0;
    // } else if (maxProfitHeap.size() < 2) {
    // return maxProfitHeap.peek().profit;
    // }
    // Slope maxProfitSlope = maxProfitHeap.poll();
    // totalProfit += maxProfitSlope.profit;
    // totalProfit += maxProfitHeap.peek().profit;
    // maxProfitHeap.add(maxProfitSlope);
    // return totalProfit;
    // }

    // public int maxProfit(int[] prices) {
    // this.maxProfitHeap = new PriorityQueue<>(new ProfitComparator());
    // this.minProfitLossHeap = new PriorityQueue<>(new ProfitLossComparator());
    // this.slopeCount = 0;
    // Slope slopes = buildSlopList(prices);
    // int maxTotalProfit = curBestTrade();
    // boolean hasMerge;
    // while (!minProfitLossHeap.isEmpty()) {
    // Slope slope = minProfitLossHeap.poll();
    // hasMerge = slope.mergeWithPreviousSlope();
    // if (hasMerge) {
    // slopeCount--;
    // maxProfitHeap.remove(slope.prev);
    // maxProfitHeap.remove(slope);
    // maxProfitHeap.add(slope.prev);
    // int curBestProfit = curBestTrade();
    // if (maxTotalProfit < curBestProfit) {
    // maxTotalProfit = curBestProfit;
    // }
    // if (slope.prev.next != null) {
    // minProfitLossHeap.add(slope.prev.next);
    // }
    // }
    // }

    // return maxTotalProfit;
    // }

    // 可行的dp方法
    // private static Integer[][][] memo;

    // private int _maxProfit(int[] prices, int start, int canBuy, int remainTradeCount) {
    //     if (remainTradeCount == 0) { // 如果交易次数已经达到限制，没有收益可以再产生了
    //         return 0;
    //     }
    //     if (start == prices.length) { // 如果已经耗尽所有天数了，不能进行交易，无法产生收益了
    //         return 0;
    //     }
    //     Integer memoVal = memo[canBuy][remainTradeCount][start];
    //     if (memoVal != null) {
    //         return memoVal;
    //     }
    //     int res;
    //     if (canBuy == 1) { // 如果当前为可以购买股票状态，即当前没有持有股票
    //         res = Math.max(-prices[start] + _maxProfit(prices, start + 1, 0, remainTradeCount), // 可以选择购买当天的股票，看可以获得的最大收益
    //                 _maxProfit(prices, start + 1, 1, remainTradeCount)); // 选择不购买当天的股票，看剩余的天数可以获得多少收益
    //         memo[canBuy][remainTradeCount][start] = res;
    //     } else { // 如果当前已经持有股票，不能再购入股票
    //         res = Math.max(prices[start] + _maxProfit(prices, start + 1, 1, remainTradeCount - 1), // 选择在这天卖出股票，并看接下来剩余的天数和交易次数可以再获得多少收益
    //                 _maxProfit(prices, start + 1, 0, remainTradeCount));  // 不选择今天卖出持有股票，看后续操作和交易
    //         memo[canBuy][remainTradeCount][start] = res;
    //     }
    //     return res;
    // }

    // public int maxProfit(int[] prices) {
    //     memo = new Integer[2][3][prices.length];
    //     return _maxProfit(prices, 0, 1, 2);
    // }

    // 上面方法的循环做法
    // public int maxProfit(int[] prices) {
    //     final int n = prices.length;
    //     int[][][] memo = new int[2][3][n+1];
    //     for (int start = n - 1; start >= 0; --start) {
    //         for (int remainTradeCount = 1; remainTradeCount <= 2; ++remainTradeCount) {
    //             for (int canBuy = 0; canBuy <= 1; ++canBuy) {
    //                 int res;
    //                 if (canBuy == 1) {
    //                     res = Math.max(-prices[start] + memo[0][remainTradeCount][start + 1], memo[1][remainTradeCount][start + 1]);
    //                 } else {
    //                     res = Math.max(prices[start] + memo[1][remainTradeCount - 1][start + 1], memo[0][remainTradeCount][start + 1]);
    //                 }
    //                 memo[canBuy][remainTradeCount][start] = res;
    //             }
    //         }
    //     }
    //     return memo[1][2][0];
    // }
    public int maxProfit(int[] prices) {
        int propertyAfterFirstBuy = Integer.MIN_VALUE; // 第一次买入后你的资产，这个值是负的，因为一开始你没有资产
        int propertyAfterSecondBuy = Integer.MIN_VALUE; // 第二次买入后你的资产，可能是正的也可能是负的，因为你的第一次交易可能有收益
        int propertyAfterFirstSell = 0; // 第一次卖出后你的资产，收益至少为0否则没有交易的意义
        int propertyAfterSecondSell = 0; // 第二次卖出后你的资产，就是两次交易后的总收益，至少为0
        for (int price : prices) {
            // 这个数据更新的顺序保证了，第二次交易在第一次交易之后，且两次交易不重叠
            propertyAfterSecondSell = Math.max(propertyAfterSecondBuy + price, propertyAfterSecondSell);
            propertyAfterSecondBuy = Math.max(propertyAfterFirstSell - price, propertyAfterSecondBuy);
            propertyAfterFirstSell = Math.max(propertyAfterFirstBuy + price, propertyAfterFirstSell);
            propertyAfterFirstBuy = Math.max(-price, propertyAfterFirstBuy);
        }
        return propertyAfterSecondSell;
    }
}
// @lc code=end
