import java.util.Stack;

/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
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
    // int val;
    // TreeNode left;
    // TreeNode right;

    // TreeNode() {
    // }

    // TreeNode(int val) {
    // this.val = val;
    // }

    // TreeNode(int val, TreeNode left, TreeNode right) {
    // this.val = val;
    // this.left = left;
    // this.right = right;
    // }
    // }
    // private static final class Range {
    // public final TreeNode first, last;

    // public Range(TreeNode first, TreeNode last) {
    // this.first = first;
    // this.last = last;
    // }

    // }
    // private Range _flatten(TreeNode root) {
    // if (root == null) {
    // return new Range(null, null);
    // }
    // if (root.left == null && root.right == null) {
    // return new Range(root, root);
    // }
    // Range lRange = _flatten(root.left), rRange = _flatten(root.right);
    // root.left = null;
    // if (lRange.first == null) {
    // root.right = rRange.first;
    // return new Range(root, rRange.last);
    // }
    // root.right = lRange.first;
    // lRange.last.right = rRange.first;
    // if (rRange.first == null) {
    // return new Range(root, lRange.last);
    // }
    // return new Range(root, rRange.last);
    // }
    // public void flatten(TreeNode root) {
    // // _flatten(root);
    // if (root == null) {
    // return;
    // }
    // Stack<TreeNode> stack = new Stack<>();
    // TreeNode lastVisited = root;
    // if (root.right != null) {
    // stack.push(root.right);
    // }
    // if (root.left != null) {
    // stack.push(root.left);
    // root.left = null;
    // }
    // while (!stack.empty()) {
    // TreeNode cur = stack.pop();
    // if (cur.right != null) {
    // stack.push(cur.right);
    // }
    // if (cur.left != null) {
    // stack.push(cur.left);
    // cur.left = null;
    // }
    // lastVisited.right = cur;
    // lastVisited = cur;
    // }
    // }
    public void flatten(TreeNode root) {
        TreeNode cur = null, head = root;
        while (head != null) {
            if (head.left != null) {
                cur = head.left;
                while (cur.right != null) {
                    cur = cur.right;
                }
                cur.right = head.right;
                TreeNode left = head.left;
                head.right = head.left;
                head.left = null;
                head = left;
            } else {
                // print head
                head = head.right;
            }
        }
    }
}
// @lc code=end
