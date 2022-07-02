import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=219 lang=java
 *
 * [219] Contains Duplicate II
 */

// @lc code=start
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 最直观的方法，用一个hash map存储所有出现的数字的出现位置
        // 其实应该不用存储所有的出现情况，因为我们的遍历是顺序向前的与之前出现过的元素的距离是不断增大的，如果之前不行
        // 前进后之前的元素更不行，所以只要存储最近出现的一次即可
        // Map<Integer, List<Integer>> locs = new HashMap<>(nums.length);
        // for (int i = 0; i < nums.length; ++i) {
        //     List<Integer> idxs = locs.getOrDefault(nums[i], new ArrayList<>());
        //     for (int idx : idxs) {
        //         if (Math.abs(idx - i) <= k) {
        //             return true;
        //         }
        //     }
        //     idxs.add(i);
        //     if (idxs.size() == 1) {
        //         locs.put(nums[i], idxs);
        //     }
        // }
        // return false;

        // Map<Integer, Integer> locs = new HashMap<>(nums.length);
        // for (int i = 0; i < nums.length; ++i) {
        //     Integer prevAppear = locs.get(nums[i]);
        //     if (prevAppear == null) {
        //         locs.put(nums[i], i);
        //     } else if (Math.abs(prevAppear - i) <= k){
        //         return true;
        //     } else {
        //         locs.put(nums[i], i);
        //     }
        // }
        // return false;

        // 按上一个解法的思想，其实只需要记录k+1长度范围内出现的元素即可，如果这个范围内有重复即可返回真
        Set<Integer> windowEles = new HashSet<>(nums.length);
        k++;
        int len = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (len == k) {
                windowEles.remove(nums[i - k]);
                len--;
            }
            if (!windowEles.add(nums[i])) {
                return true;
            }
            len++;
        }
        return false;
    }
}
// @lc code=end

