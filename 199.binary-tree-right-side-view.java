import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import java.util.Deque;

/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 最暴力的做法是使用层次遍历，取队列中的最后一个元素作为最右元素
        Deque<TreeNode> queue = new ArrayDeque<>(), tmp = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur.left != null) {
                tmp.addLast(cur.left);
            }
            if (cur.right != null) {
                tmp.addLast(cur.right);
            }
            if (queue.isEmpty()) {
                res.add(cur.val);
                queue = tmp;
                tmp = new ArrayDeque<>();
            }
        }
        return res;
    }
}
// @lc code=end
