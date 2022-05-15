import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
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

    // private int _isBalanced(TreeNode root) {
    // if (root == null) {
    // return 0;
    // }
    // int leftHeight = _isBalanced(root.left), rightHeight =
    // _isBalanced(root.right);
    // if (leftHeight == -1 || rightHeight == -1) {
    // return -1;
    // }
    // if (Math.abs(leftHeight - rightHeight) <= 1) {
    // return Math.max(leftHeight, rightHeight) + 1;
    // }
    // return -1;
    // }
    public boolean isBalanced(TreeNode root) {
        // return _isBalanced(root) != -1;
        // TreeNodeWithHeight lastVisitedNode = null;
        TreeNode pushPointer = root, lastVisitedNode = null;
        Stack<TreeNodeWithHeight> stack = new Stack<>();
        while (!stack.empty() || pushPointer != null) {
            if (pushPointer != null) {
                stack.push(new TreeNodeWithHeight(pushPointer));
                pushPointer = pushPointer.left;
            } else {
                TreeNodeWithHeight curVisit = stack.peek();
                if (curVisit.tree.right == null || curVisit.tree.right == lastVisitedNode) {
                    stack.pop();
                    if (Math.abs(curVisit.leftHeight - curVisit.rightHeight) > 1) {
                        return false;
                    }
                    int visitedHeight = Math.max(curVisit.leftHeight, curVisit.rightHeight) + 1;
                    if (!stack.empty()) {
                        TreeNodeWithHeight maybeYourRoot = stack.peek();
                        if (maybeYourRoot.tree.left == curVisit.tree) {
                            maybeYourRoot.leftHeight = visitedHeight;
                        } else {
                            maybeYourRoot.rightHeight = visitedHeight;
                        }
                    }
                    lastVisitedNode = curVisit.tree;
                } else {
                    pushPointer = curVisit.tree.right;
                }
            }
        }
        return true;
    }

    private static class TreeNodeWithHeight {
        public final TreeNode tree;
        public int leftHeight, rightHeight;

        public TreeNodeWithHeight(TreeNode tree) {
            this.tree = tree;
        }
    }
}
// @lc code=end
