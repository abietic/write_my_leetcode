
/*
 * @lc app=leetcode id=135 lang=java
 *
 * [135] Candy
 */

// @lc code=start
class Solution {
    // // 暴力做，先满足第一个条件为每个child都分配一个糖果
    // //
    // 然后检查每个child的相邻children，如果相邻的children有分数比child小且candy数比child大的数，更新child的candy数以保证条件2，不断重复上述动作直到没有更新发生
    // // 正确性不知道怎么证明
    // public int candy(int[] ratings) {
    // final int n = ratings.length;
    // int[] candies = new int[n];
    // boolean hasChanges = true;
    // while (hasChanges) {
    // hasChanges = false;
    // for (int i = 0; i < n; ++i) {
    // if (i > 0 && ratings[i] > ratings[i - 1]) {
    // if (candies[i] <= candies[i - 1]) {
    // hasChanges = true;
    // candies[i] = candies[i - 1] + 1;
    // }
    // }
    // if (i < n - 1 && ratings[i] > ratings[i + 1]) {
    // if (candies[i] <= candies[i + 1]) {
    // hasChanges = true;
    // candies[i] = candies[i + 1] + 1;
    // }
    // }
    // }
    // }
    // return Arrays.stream(candies).reduce(n, (a, b)->{return a+ b;});
    // }

    // // 从理念上应该和上面一样，但是从两个方向检查，不用多次遍历了
    // // 因为之前单向的时候另一侧的增长是最右逐个向左传递的
    // public int candy(int[] ratings) {
    // final int n = ratings.length;
    // int[] candies = new int[n];
    // // 从左到右更新分发糖果数
    // for (int i = 0; i < n - 1; ++i) {
    // if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
    // candies[i] = candies[i + 1] + 1;
    // }
    // if (ratings[i] < ratings[i + 1] && candies[i] >= candies[i + 1]) {
    // candies[i + 1] = candies[i] + 1;
    // }
    // }
    // int total = n;
    // // 从右到左更新分发糖数
    // for (int i = n - 1; i > 0; --i) {
    // if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
    // candies[i]= candies[i - 1] + 1;
    // }
    // if (ratings[i] < ratings[i - 1] && candies[i] >= candies[i - 1]) {
    // candies[i - 1]= candies[i] + 1;
    // }
    // total += candies[i];
    // }
    // return total + candies[0];
    // }

    public int candy(int[] ratings) {
        // 上一个已知坡顶的元素坡度
        int level = 1;
        int sum = 1;
        // 上一个已知坡底的元素坡度
        int down = 0;
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                // 面临一个上坡
                if (down < 0) {
                    // 面临上坡之前是一个下坡
                    // 那么需要做一些处理
                    // 首先是对下坡中经过的元素进行数量的补全
                    for (int j = -down; j > 1; --j) {
                        sum += j - 1;
                    }
                    // 然后看之前上坡的最高坡度是否无法满足下坡要求
                    if ((-down) >= level) {
                        // 如果没有满足，之前的最高坡度的置值不够大，要加值
                        sum -= level;
                        sum -= down;
                        sum++;
                    }
                    // 重置下坡
                    down = 0;
                    // 增加上坡坡度
                    level = 2;
                    // 加上当前坡度
                    sum += level;
                } else {
                    // 持续的上坡或是刚开始的上坡
                    level++;
                    sum += level;
                }
            } else if (ratings[i] < ratings[i - 1]) {
                // 面临一个下坡
                sum += 1;
                down--;
            } else {
                // 一个平地
                if (down < 0) {
                    // 之前经历了一个下坡
                    for (int j = -down; j > 1; --j) {
                        sum += j - 1;
                    }
                    // 然后看之前上坡的最高坡度是否无法满足下坡要求
                    if ((-down) >= level) {
                        // 如果没有满足，之前的最高坡度的置值不够大，要加值
                        sum -= level;
                        sum -= down;
                        sum++;
                    }
                    // 重置下坡
                    down = 0;
                }
                // 重置上坡坡度
                level = 1;
                // 加上当前坡度
                sum += level;
            }
        }
        if (down < 0) {
            // 之前经历了一个下坡
            for (int j = -down; j > 1; --j) {
                sum += j - 1;
            }
            // 然后看之前上坡的最高坡度是否无法满足下坡要求
            if ((-down) >= level) {
                // 如果没有满足，之前的最高坡度的置值不够大，要加值
                sum -= level;
                sum -= down;
                sum++;
            }
            // 重置下坡
            down = 0;
        }
        return sum;
    }
}
// @lc code=end
