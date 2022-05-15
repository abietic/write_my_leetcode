import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start
class Solution {
    private List<List<Integer>> _subsetsWithDup(int[] nums, int i) {
        List<List<Integer>> res = new ArrayList<>();
        if (i == nums.length) {
            res.add(new ArrayList<>());
            return res;
        }
        int ele = nums[i], counter = 1, j = i + 1;
        for (;j < nums.length; ++j) {
            if (nums[j] != ele) {
                break;
            }
            counter++;
        }
        List<List<Integer>> subSubset = _subsetsWithDup(nums, j);
        List<List<Integer>> addElements = new ArrayList<>();
        List<Integer> tempElements = new ArrayList<>();
        for (int count = 0; count < counter; ++count) {
            tempElements.add(ele);
            addElements.add(new ArrayList<>(tempElements));
        }
        for (List<Integer> subEle : subSubset) {
            for (List<Integer> addEle : addElements) {
                List<Integer> neoEle = new ArrayList<>(subEle);
                neoEle.addAll(addEle);
                res.add(neoEle);
            }
        }
        res.addAll(subSubset);
        return res;
    } 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return _subsetsWithDup(nums, 0);
    }
}
// @lc code=end

