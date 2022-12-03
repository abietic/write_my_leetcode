import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=454 lang=java
 *
 * [454] 4Sum II
 */

// @lc code=start
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        final int n = nums1.length;
        int ans = 0;
        Map<Integer, Integer> lMap = new HashMap<>(), rMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int l = nums1[i] + nums2[j];
                int r = nums3[i] + nums4[j];
                lMap.put(l, lMap.getOrDefault(l, 0) + 1);
                rMap.put(r, rMap.getOrDefault(r, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> lp : lMap.entrySet()) {
            Integer j = null;
            if ((j = rMap.get(Integer.valueOf(-lp.getKey().intValue()))) != null) {
                ans += j * lp.getValue();
            }
        }
        return ans;
    }
}
// @lc code=end

