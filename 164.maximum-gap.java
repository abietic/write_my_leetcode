import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 */

// @lc code=start
class Solution {
    // 直接使用排序是nlogn的时间复杂度
    // 数组中的元素没有负数
    // 排序后相邻说明中间没有其他元素了
    // 怎么找到排序后相邻的元素


    // 这个直接排序显然是不符合要求的，O(nlogn)的
    // public int maximumGap(int[] nums) {
    //     final int n = nums.length;
    //     if (n < 2) {
    //         return 0;
    //     }
    //     Arrays.sort(nums);
    //     int maxGap = 0;
    //     for (int i = 0; i < n - 1; ++i) {
    //         maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
    //     }
    //     return maxGap;
    // }

    // 这个方法是O(n^2)的因为插入操作是O(n)的，效率还不如直接排序，直接超时了
    // public int maximumGap(int[] nums) {
    // final int n = nums.length;
    // if (n < 2) {
    // return 0;
    // }

    // Map<Integer, Integer> theLessNeighbour = new HashMap<>(), theMoreNeighbour =
    // new HashMap<>();
    // theLessNeighbour.put(nums[0], -1);
    // theMoreNeighbour.put(nums[0], -1);
    // for (int i = 1; i < n; ++i) {
    // if (theLessNeighbour.containsKey(nums[i])) {
    // continue;
    // }
    // if (nums[i] < nums[i - 1]) {
    // int cur = nums[i - 1];
    // int less = theLessNeighbour.get(cur);
    // while (less != -1) {
    // if (less > nums[i]) {
    // cur = less;
    // } else {
    // break;
    // }
    // less = theLessNeighbour.get(cur);
    // }
    // theLessNeighbour.put(nums[i], less);
    // theLessNeighbour.put(cur, nums[i]);
    // theMoreNeighbour.put(nums[i], cur);
    // if (less != -1) {
    // theMoreNeighbour.put(less, nums[i]);
    // }
    // } else {
    // int cur = nums[i - 1];
    // int more = theMoreNeighbour.get(cur);
    // while (more != -1) {
    // if (more < nums[i]) {
    // cur = more;
    // } else {
    // break;
    // }
    // more = theMoreNeighbour.get(cur);
    // }
    // theMoreNeighbour.put(nums[i], more);
    // theMoreNeighbour.put(cur, nums[i]);
    // theLessNeighbour.put(nums[i], cur);
    // if (more != -1) {
    // theLessNeighbour.put(more, nums[i]);
    // }
    // }
    // }
    // int maxGap = 0;
    // for (int num : nums) {
    // if (theLessNeighbour.get(num) == -1) {
    // continue;
    // } else {
    // maxGap = Math.max(maxGap, num - theLessNeighbour.get(num));
    // }
    // }
    // return maxGap;
    // }


    public int maximumGap(int[] nums) {
        // 如果想要计算复杂度为O(n)，那么树，堆就不能用了，
        // 可以试试栈，并查集，哈希表
    }
}
// @lc code=end
