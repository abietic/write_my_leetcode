import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=89 lang=java
 *
 * [89] Gray Code
 */

// @lc code=start
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(1);
        if (n == 1) {
            return res;
        }
        int log2 = 1, mirrorPointer = 1, cur = 0b10;
        while (true) {
            res.add(cur | res.get(mirrorPointer--));
            if (mirrorPointer < 0) {
                log2++;
                if (log2 == n) {
                    break;
                }
                cur = 1 << log2;
                mirrorPointer = res.size() - 1;
            }
        }
        return res;
    }
}
// @lc code=end
