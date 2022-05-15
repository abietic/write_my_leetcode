import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // private static class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;

    //     TreeNode() {
    //     }

    //     TreeNode(int val) {
    //         this.val = val;
    //     }

    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }
    private List<TreeNode> _generateTrees(List<Integer> remains) {
        List<TreeNode> res = new ArrayList<>();
        if (remains.isEmpty()) {
            res.add(null);
            return res;
        }
        for (int i = 0; i < remains.size(); ++i) {
            List<TreeNode> lefty = _generateTrees(remains.subList(0, i));
            List<TreeNode> righty = _generateTrees(remains.subList(i + 1, remains.size()));
            for (TreeNode l : lefty) {
                for (TreeNode r : righty) {
                    // 这里左子树和右子树应该复制一份的
                    res.add(new TreeNode(remains.get(i), l, r));
                }
            }
        }
        return res;
    }  
    public List<TreeNode> generateTrees(int n) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            tmp.add(i);
        }
        return _generateTrees(tmp);
    }
}
// @lc code=end
