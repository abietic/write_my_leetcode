import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
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

    // private static void postorderTraversalRecursive(TreeNode root, List<Integer>
    // res) {
    // if (root == null) {
    // return;
    // }
    // postorderTraversalRecursive(root.left, res);
    // postorderTraversalRecursive(root.right, res);
    // res.add(root.val);
    // }
    // public List<Integer> postorderTraversal(TreeNode root) {
    // List<Integer> res = new ArrayList<>();
    // postorderTraversalRecursive(root, res);
    // return res;
    // }

    // public List<Integer> postorderTraversal(TreeNode root) {
    //     List<Integer> res = new ArrayList<>();
    //     Stack<TreeNode> stack = new Stack<>();
    //     Stack<Boolean> visited = new Stack<>();
    //     stack.push(root);
    //     visited.push(false);
    //     while (!stack.empty()) {
    //         if (stack.peek() == null) {
    //             stack.pop();
    //             visited.pop();
    //             continue;
    //         }
    //         if (!visited.pop()) {
    //             TreeNode cur = stack.peek();
    //             visited.push(true);
    //             stack.push(cur.right);
    //             visited.push(false);
    //             stack.push(cur.left);
    //             visited.push(false);
    //         } else {
    //             res.add(stack.pop().val);
    //         }
    //     }
    //     return res;
    // }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        while (root != null) {
            res.add(0, root.val);
            TreeNode cur = root.right;
            if (cur != null) {
                while (cur.left != null && cur.left != root) {
                    cur = cur.left;
                }
                if (cur.left != root) {
                    cur.left = root;
                    root = root.right;
                } else {
                    cur.left = null;
                    root = root.left;
                    res.remove(0);
                }
            } else {
                root = root.left;
            }
        }
        return res;
    }
}
// @lc code=end
