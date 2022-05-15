import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
class Solution {
    private List<List<Integer>> _subsets(int i, int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (i == nums.length) {
            res.add(new ArrayList<>());
            return res;
        }
        List<List<Integer>> subSub = _subsets(i + 1, nums);
        for (List<Integer> subsub : subSub) {
            List<Integer> neoSubSub = new ArrayList<>(subsub);
            neoSubSub.add(nums[i]);
            res.add(neoSubSub);
        }
        res.addAll(subSub);
        return res;
    } 
    public List<List<Integer>> subsets(int[] nums) {
        return _subsets(0, nums);
    }
}
// @lc code=end

