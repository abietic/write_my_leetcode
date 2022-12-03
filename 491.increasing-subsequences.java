import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=491 lang=java
 *
 * [491] Increasing Subsequences
 */

// @lc code=start
class Solution {
    private static void findSubsequencesRecursive(int[] nums, int start, List<Integer> curPath, List<List<Integer>> res) {
        int take = nums[start];
        curPath.add(take);
        if (curPath.size() >= 2) {
            res.add(new ArrayList(curPath));
        }
        Set<Integer> used = new HashSet<>();
        for (start = start + 1; start < nums.length; ++start) {
            if (nums[start] >= take && used.add(nums[start])) {
                findSubsequencesRecursive(nums, start, curPath, res);
            }
        }
        curPath.remove(curPath.size() - 1);
        return;
    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        for (int start = 0; start < nums.length - 1; ++start) {
            if (used.add(nums[start])) {
                findSubsequencesRecursive(nums, start, curPath, res);
            }
        }
        return res;
    }
}
// @lc code=end
