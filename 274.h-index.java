import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        // TreeMap<Integer, Integer> stat = new TreeMap<>((i1, i2)->i2-i1);
        // for (int cite : citations) {
        //     stat.compute(cite, (c,cnt)->cnt==null?1:cnt+1);
        // }
        // int h = 0, hCnt = 0;
        // for (Map.Entry<Integer, Integer> he : stat.entrySet()) {
        //     if (he.getKey() < hCnt + he.getValue()) {
        //         if (he.getKey() > hCnt) {
        //             hCnt = he.getKey();
        //         }
        //         break;
        //     }
        //     h = he.getKey();
        //     hCnt += he.getValue();
        // }
        // return hCnt;
        Arrays.sort(citations);
        int h = 0;
        for (; h < citations.length; ++h) {
            int idx = citations.length - h - 1;
            if (citations[idx] <= h) {
                break;
            }
        }
        return h;
    }
}
// @lc code=end

