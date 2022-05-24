import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    // final int n = nums.length;
    // if (n < 2) {
    // return 0;
    // }
    // Arrays.sort(nums);
    // int maxGap = 0;
    // for (int i = 0; i < n - 1; ++i) {
    // maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
    // }
    // return maxGap;
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

    // public int maximumGap(int[] nums) {
    // // 如果想要计算复杂度为O(n)，那么树，堆就不能用了，
    // // 可以试试栈，并查集，哈希表?
    // // 还是用桶排？
    // // 应该不是桶排，速度比正常排序还慢
    // // 以前我写桶排都是直接用链表保存
    // // 这回用数组减少了内存和计算量
    // final int n = nums.length;
    // if (n <= 2) {
    // return n < 2 ? 0 : Math.abs(nums[0] - nums[1]);
    // }
    // int[] res = new int[n], tmp, counts = new int[10];
    // final int maxBase = (int)Math.pow(10, 9);
    // for (int base = 1; base <= maxBase; base *= 10) {
    // for (int num : nums) {
    // counts[(num / base) % 10]++;
    // }
    // for (int i = 1; i < 10; ++i) {
    // counts[i] += counts[i - 1];
    // }
    // for (int i = n - 1; i >= 0; --i) {
    // int num = nums[i];
    // res[--counts[(num / base) % 10]] = num;
    // }
    // for (int i = 1; i < 10; ++i) {
    // counts[i] = 0;
    // }
    // tmp = nums;
    // nums = res;
    // res = tmp;
    // }
    // int maxGap = 0;
    // for (int i = 0; i < n - 1; ++i) {
    // maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
    // }
    // return maxGap;
    // }

    public int maximumGap(int[] nums) {
        // 使用类似桶排的方法，利用鸽巢原理计算最大间距
        final int n = nums.length, MAX_VAL = (int)Math.pow(10, 9) + 1, MIN_VAL = -1;
        if (n <= 2) {
            return n < 2 ? 0 : Math.abs(nums[0] - nums[1]);
        }
        // Set<Integer> numSet = new HashSet<>(n);
        int max = -1, min = (int)Math.pow(10, 9) + 1;
        for (int num : nums) {
            // numSet.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (max == min) {
            // 防止全是重复元素的情况出现
            return 0;
        }
        // final int neoLen = numSet.size();
        final int neoLen = n;
        // if (neoLen < 2) {
        //     return 0;
        // } else if (neoLen == 2) {
        //     Integer[] ns = numSet.toArray(new Integer[0]);
        //     return Math.abs(ns[0] - ns[1]);
        // }
        int[] mins = new int[neoLen - 1], maxs = new int[neoLen - 1];
        Arrays.fill(mins, MAX_VAL);
        Arrays.fill(maxs, MIN_VAL);
        // for (int num : numSet) {
        for (int num : nums) {
            int idx = (int)(((long)num - min) * (neoLen - 1) / (max - min));
            // 最后一个区间要加入最大值闭区间
            idx = idx == neoLen - 1 ? neoLen - 2 : idx;
            mins[idx] = Math.min(mins[idx], num);
            maxs[idx] = Math.max(maxs[idx], num);
        }
        int maxGap = 0;
        // 由于区间是根据收集到的max和min所以第一个区间和最后一个区间一定有元素
        int prev = maxs[0];
        for (int i = 1; i < neoLen - 1; ++i) {
            if (mins[i] == MAX_VAL) {
                continue;
            }
            maxGap = Math.max(maxGap, mins[i] - prev);
            prev = maxs[i];
        }
        return maxGap;
    }
}
// @lc code=end
