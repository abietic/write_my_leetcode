import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=220 lang=java
 *
 * [220] Contains Duplicate III
 */

// @lc code=start
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // // 找k+1范围内，元素差值绝对值在指定范围内的元素
        // // 从数据范围来看应该可以支持O(nk)的复杂度，但是实时上超时了
        // for (int i = 0; i < nums.length; ++i) {
        // for (int len = 1; len <= k && i - len >= 0; ++len) {
        // if (Math.abs((long)nums[i] - nums[i - len]) <= t) {
        // return true;
        // }
        // }
        // }
        // return false;

        // // 转换思维，维护一个范围为k的子序列的最大和最小值
        // // 通过检查序列的下一个邻接元素的检查范围t是否与最大值最小值组成的范围有相交判断
        // // 通过二叉搜索树保证维护和搜索维持在klogk
        // // 这个解法其实不用记录重复的数字出现次数，以为t一定大于等于0，如果在k范围中有相等元素了，一定已经满足条件返回真了
        // TreeMap<Long, Integer> bst = new TreeMap<>();
        // int len = 0;
        // for (int i = 0; i < nums.length; ++i) {
        //     if (len > k) {
        //         if (bst.get((long)nums[i - k - 1]) == 1) {
        //             bst.remove((long)nums[i - k - 1]);
        //         } else {
        //             bst.put((long)nums[i - k - 1], bst.get((long)nums[i - k - 1]) - 1);
        //         }
        //         len--;
        //     }
        //     if (!bst.isEmpty()) {
        //         long ll = (long) nums[i] - t, lr = (long) nums[i] + t;
        //         Long insiderLow = bst.ceilingKey(ll), insiderHigh = bst.floorKey(lr);
        //         if ((insiderLow != null && insiderLow <= lr) || (insiderHigh != null && insiderHigh >= ll)) {
        //             return true;
        //         }
        //     }
        //     if (bst.containsKey((long)nums[i])) {
        //         bst.put((long)nums[i], bst.get((long)nums[i]) + 1);
        //     } else {
        //         bst.put((long)nums[i], 1);
        //     }
        //     len++;
        // }
        // return false;


        // 还有用范围桶来存储一定范围内的数值的，每个范围桶的覆盖范围为t + 1
        // 这样就可以认为有可能满足数值大小范围的数字落在同一个桶中，或者可能在邻接的桶中
        // 因此每个范围的桶中，只需要存一个具体值
        // 一开始我只想到用一个数组存这些桶，但这显然不现实，因此应该使用hash表，这样才不会引发空间问题，也不用转换负数
        int len = 0;
        Map<Long, Long> buckets = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            // 这里为了防止在正负分界处出现问题如{-3,3} k = 2, t = 4，这里使用将负数全部转换为正数的方式解决
            // 另一种解决方案为 nums[i] < 0 ? (nums[i] + 1) / (1 + t) - 1 : nums[i] / (1 + t)
            // 为什么可以解决这种问题呢？首先要知道java在做除法时取整是向0方向取整的即 -3 / 4 == 0
            // 这就带来了正负之间的取整不统一，正数向较小的方向取整，负数向较大的方向取整
            // 这种问题在比较同号的元素时没有影响，但是在比较异号的元素时就会出现问题了
            // 因此，为了解决这个问题，需要将负数的取整方式也统一起来，就有了上面的方法
            // 值得注意的是，python在进行除法时，都是统一向小的方向取整，因此没有这样的问题
            long idx = ((long)nums[i] + (long)Integer.MAX_VALUE + 1) / (1 + t);
            if (len > k) {
                long prevIdx = ((long)nums[i - k - 1] + (long)Integer.MAX_VALUE + 1) / (1 + t);
                buckets.remove(prevIdx);
                --len;
            }
            if (buckets.containsKey(idx)) {
                return true;
            } else if (buckets.containsKey(idx - 1) && t >= Math.abs(buckets.get(idx - 1) - nums[i])) {
                return true;
            } else if (buckets.containsKey(idx + 1) && t >= Math.abs(buckets.get(idx + 1) - nums[i])) {
                return true;
            }
            buckets.put(idx, (long)nums[i]);
            ++len;
        }
        return false;
    }
}
// @lc code=end
