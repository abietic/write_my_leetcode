import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
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
    //  private static class TreeNode {
    //          int val;
    //          TreeNode left;
    //          TreeNode right;
    //          TreeNode() {}
    //          TreeNode(int val) { this.val = val; }
    //          TreeNode(int val, TreeNode left, TreeNode right) {
    //              this.val = val;
    //              this.left = left;
    //              this.right = right;
    //          }
    //      }
    // 层次遍历？
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>(), flipQueue = new ArrayDeque<>(), tmp;
        int level = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur.left == null && cur.right == null) {
                break;
            }
            if (cur.left != null) {
                flipQueue.addLast(cur.left);
            }
            if (cur.right != null) {
                flipQueue.addLast(cur.right);
            }
            if (queue.isEmpty()) {
                tmp = queue;
                queue = flipQueue;
                flipQueue = tmp;
                level++;
            }
        }
        return level;
    }
}
// @lc code=end

