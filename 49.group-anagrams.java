import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>(strs.length);
        for (String str : strs) {
            byte[] bs = str.getBytes();
            Arrays.sort(bs);
            String sorted = new String(bs);
            List<String> group = groups.get(sorted);
            if (group == null) {
                group = new ArrayList<>();
                groups.put(sorted, group);
            }
            group.add(str);
        }
        return new ArrayList<List<String>>(groups.values());
    }
}
// @lc code=end

