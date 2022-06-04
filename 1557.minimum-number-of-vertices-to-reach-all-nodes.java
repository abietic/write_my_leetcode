import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/*
 * @lc app=leetcode id=1557 lang=java
 *
 * [1557] Minimum Number of Vertices to Reach All Nodes
 */

// @lc code=start
class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // 只要找所有入度为0的节点就行了？
        boolean[] canTraverse = new boolean[n];
        for (List<Integer> edge : edges) {
            int tar = edge.get(1);
            canTraverse[tar] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!canTraverse[i]) {
                res.add(i);
            }
        }
        return res;
    }
}
// @lc code=end

