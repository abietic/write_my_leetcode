import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=228 lang=java
 *
 * [228] Summary Ranges
 */

// @lc code=start
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length;) {
            int len = 1;
            for (; i + len < nums.length; ++len) {
                if (nums[i + len] != nums[i + len - 1] + 1) {
                    break;
                }
            }
            if (len == 1) {
                res.add(Integer.toString(nums[i]));
            } else {
                res.add(""+ nums[i] + "->" + nums[i + len - 1]);
            }
            i += len;
        }
        return res;
    }
}
// @lc code=end

