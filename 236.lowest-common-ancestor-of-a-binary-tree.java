import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 对整个树自底向上直到有一个节点包含了两个目标节点,就知道它们的最矮公共根
        List<TreeNode> children = new ArrayList<>();
        children.add(p);        children.add(q);
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        TreeNode cur;
        // 先边遍历边记录父节点
        while (!stack.isEmpty()) {
            cur = stack.pollLast();
            if (children.contains(cur)) {
                children.remove(cur);
                if (children.isEmpty()) {
                    break;
                }
            }
            if (cur.left != null) {
                parents.put(cur.left, cur);
                stack.addLast(cur.left);
            }
            if (cur.right != null) {
                parents.put(cur.right, cur);
                stack.addLast(cur.right);
            }
        }
        // 遍历到两个目标节点后,从后向前找第一个相交节点
        Set<TreeNode> visited = new HashSet<>();
        TreeNode[] curs = {p,q};
        while (true) {
            for (int i = 0; i < curs.length; ++i) {
                if (curs[i] != null) {
                    if (!visited.add(curs[i])) {
                        return curs[i];
                    }
                    curs[i] = parents.get(curs[i]);
                }
            }
        }
    }
}
// @lc code=end

