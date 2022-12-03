import java.util.Arrays;
import java.util.Map;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=337 lang=java
 *
 * [337] House Robber III
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 可能类似最少摄像头数那道题968？使用贪心思想？
    // 检查一个树底部层
    // 如果选中了倒数第二层的节点。那么，它的儿子和父亲都不能选了，因此这个被选中的节点至少要比它的儿子的和大
    // 结果好像是一个深度优先搜索？
    // 这题没太懂
    private static int[] whatIf(TreeNode root) {
        int[] res = {0, 0}, leftSub, rightSub;
        if (root == null) {
            return res;
        }
        int valUseRoot = root.val, valNotUseRoot = 0;
        leftSub = whatIf(root.left);
        rightSub = whatIf(root.right);
        // 如果使用了root两个子树只能不使用子节点
        valUseRoot += (leftSub[1] + rightSub[1]);
        int[] cands = {leftSub[0] + rightSub[0], leftSub[0] + rightSub[1], leftSub[1] + rightSub[0], leftSub[1] + rightSub[1]};
        Arrays.sort(cands);
        valNotUseRoot = cands[cands.length - 1];
        res[0] = valUseRoot;
        res[1] = valNotUseRoot;
        return res;
    }
    public int rob(TreeNode root) {
        int[] res = whatIf(root);
        return Math.max(res[0], res[1]);
    }
}
// @lc code=end

