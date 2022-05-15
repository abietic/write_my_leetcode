import java.util.Stack;

/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
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

    // 第一次发生逆序时是前一个节点有问题
    // 第二次发生逆序时是第二个节点有问题
    // 只发生一次逆序的情况是发生逆序的两个节点有问题
    // public void recoverTree(TreeNode root) {
    //     Stack<TreeNode> stack = new Stack<>();
    //     TreeNode cur = root, prev = null, bigger = null, smaller = null;
    //     int errorCount = 0;
    //     while (cur != null) {
    //         stack.push(cur);
    //         cur = cur.left;
    //     }
    //     while (!stack.empty()) {
    //         prev = cur;
    //         cur = stack.pop();
    //         if (prev == null) {
    //             prev = cur;
    //         }
    //         if (prev.val > cur.val) {
    //             if (errorCount == 0) {
    //                 bigger = prev;
    //                 smaller = cur;
    //             } else {
    //                 smaller = cur;
    //                 break;
    //             }
    //             errorCount++;
    //         }
    //         if (cur.right != null) {
    //             TreeNode it = cur.right;
    //             while (it != null) {
    //                 stack.push(it);
    //                 it = it.left;
    //             }
    //         }
    //     }
    //     int tmp = bigger.val;
    //     bigger.val = smaller.val;
    //     smaller.val = tmp;
    // }
    public void recoverTree(TreeNode root) {
        TreeNode cur = null, prev = null, bigger = null, smaller = null, temp;
        int errorCount = 0;
        while (root != null) {
            if (root.left == null) {
                if (cur == null) {
                    cur = prev = root;
                } else {
                    prev = cur;
                    cur = root;
                    if (prev.val > cur.val) {
                        if (errorCount == 0) {
                            bigger = prev;
                            smaller = cur;
                        } else {
                            smaller = cur;
                        }
                        errorCount++;
                    }
                }
                root = root.right;
            } else {
                temp = root.left;
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }
                if (temp.right == root) {
                    if (cur == null) {
                        cur = prev = root;
                    } else {
                        prev = cur;
                        cur = root;
                        if (prev.val > cur.val) {
                            if (errorCount == 0) {
                                bigger = prev;
                                smaller = cur;
                            } else {
                                smaller = cur;
                            }
                            errorCount++;
                        }
                    }
                    temp.right = null;
                    root = root.right;
                } else {
                    temp.right = root;
                    root = root.left;
                }
            }
        }
        int tmp = bigger.val;
        bigger.val = smaller.val;
        smaller.val = tmp;
    }
}
// @lc code=end
