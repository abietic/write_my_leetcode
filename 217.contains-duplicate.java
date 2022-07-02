import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=217 lang=java
 *
 * [217] Contains Duplicate
 */

// @lc code=start
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> unique = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!unique.add(num)) {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end

