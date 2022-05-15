
/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
class Solution {
    // 子序列中包含0那一定乘积为0
    // 子序列包含奇数个负数时乘积小于等于0
    // 子序列包含偶数个负数时乘积大于等于0
    // 保证给出数组的所有前缀和所有后缀乘积不会overflow
    // 1增长了序列长度，但是不会提高乘积

    // 暴力O(n^2)超时了
    // public int maxProduct(int[] nums) {
    // int [] memo = new int[nums.length];
    // memo[0] = nums[0];
    // for (int i = 1; i < nums.length; ++i) {
    // memo[i] = Math.max(nums[i], memo[i - 1]);
    // int prod = nums[i];
    // for (int j = i - 1; j >= 0; --j) {
    // prod *= nums[j];
    // memo[i] = Math.max(memo[i], prod);
    // }
    // }
    // return memo[nums.length - 1];
    // }

    // public int maxProduct(int[] nums) {
    //     int maxProd = nums[0];
    //     int prod = nums[0];
    //     // 当前无0序列第一个负数出现时，产生了第一个负累乘子序列
    //     Integer firstNegSeq = null;
    //     // 当前未被0切断的序列的长度
    //     // 这个是用来区分只有一个负数的负累乘序列
    //     int seqLen = 1;
    //     for (int i = 1; i < nums.length; ++i) {
    //         if (prod < 0) {
    //             if (firstNegSeq == null) {
    //                 firstNegSeq = prod;
    //             }
    //         } else if (prod == 0) {
    //             prod = nums[i];
    //             // 因为这里序列更新后直接continue了
    //             // 所以要特意检查一下一下当前子序列最大值
    //             maxProd = Math.max(maxProd, prod);
    //             seqLen = 1;
    //             continue;
    //         }
    //         if (nums[i] == 0) {
    //             // 出现0代表序列被截断，要判断截断前部分的最大累乘
    //             if (firstNegSeq != null && seqLen > 1) {
    //                 // 主要为了排除序列只有一个负数的情况，因为这样做除法会出现一个不存在的1
    //                 maxProd = Math.max(maxProd, prod / firstNegSeq);
    //             }
    //             firstNegSeq = null;
    //             seqLen = 0;
    //             // 这里没有continue，因为如果累乘最大值可能为0
    //         }
    //         prod *= nums[i];
    //         seqLen++;
    //         maxProd = Math.max(maxProd, prod);
    //     }
    //     // 最后一个序列个最大值还未判断
    //     // 这里如果最后一个num使prod变为负数而firstNegSeq仍为null
    //     // 不用担心因为这说明最后一个num是当前序列中唯一一个负数，那么最大累乘即为去掉最后一个num的累乘而这个已经已经被检查过了
    //     if (firstNegSeq != null && seqLen > 1) {
    //         // 主要为了排除序列只有一个负数的情况，因为这样做除法会出现一个不存在的1
    //         maxProd = Math.max(maxProd, prod / firstNegSeq);
    //     }
    //     return Math.max(maxProd, prod);
    // }

    // 与上方同样思想
    public int maxProduct(int[] nums) {
        // 一个从左开始，一个从右开始，这样就不用特意舍掉一侧的第一段负累乘
        int leftProd = 0, rightProd = 0, maxProd = nums[0];
        for (int i = 0; i < nums.length; ++i) {
            leftProd = (leftProd == 0 ? 1 : leftProd) * nums[i];
            rightProd = (rightProd == 0 ? 1 : rightProd) * nums[nums.length - 1 - i];
            maxProd = Math.max(Math.max(leftProd, rightProd), maxProd);
        }
        return maxProd;
    }
}
// @lc code=end
