

/*
 * @lc app=leetcode id=135 lang=java
 *
 * [135] Candy
 */

// @lc code=start
class Solution {
    // // 暴力做，先满足第一个条件为每个child都分配一个糖果
    // // 然后检查每个child的相邻children，如果相邻的children有分数比child小且candy数比child大的数，更新child的candy数以保证条件2，不断重复上述动作直到没有更新发生
    // // 正确性不知道怎么证明
    // public int candy(int[] ratings) {
    //     final int n = ratings.length;
    //     int[] candies = new int[n];
    //     boolean hasChanges = true;
    //     while (hasChanges) {
    //         hasChanges = false;
    //         for (int i = 0; i < n; ++i) {
    //             if (i > 0 && ratings[i] > ratings[i - 1]) {
    //                 if (candies[i] <= candies[i - 1]) {
    //                     hasChanges = true;
    //                     candies[i] = candies[i - 1] + 1;
    //                 }
    //             }
    //             if (i < n - 1 && ratings[i] > ratings[i + 1]) {
    //                 if (candies[i] <= candies[i + 1]) {
    //                     hasChanges = true;
    //                     candies[i] = candies[i + 1] + 1;
    //                 }
    //             }
    //         }
    //     }
    //     return Arrays.stream(candies).reduce(n, (a, b)->{return a+ b;});
    // }
    
    // 从理念上应该和上面一样，但是从两个方向检查，不用多次遍历了
    // 因为之前单向的时候另一侧的增长是最右逐个向左传递的
    public int candy(int[] ratings) {
        final int n = ratings.length;
        int[] candies = new int[n];
        for (int i = 0; i < n - 1; ++i) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            if (ratings[i] < ratings[i + 1] && candies[i] >= candies[i + 1]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        int total = n;
        for (int i = n - 1; i > 0; --i) {
            if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                candies[i]= candies[i - 1] + 1;
            }
            if (ratings[i] < ratings[i - 1] && candies[i] >= candies[i - 1]) {
                candies[i - 1]= candies[i] + 1;
            }
            total += candies[i];
        }
        return total + candies[0];
    }
}
// @lc code=end

