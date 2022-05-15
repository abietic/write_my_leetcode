import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
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
    // private TreeNode _buildTree(List<Integer> preorder, List<Integer>inorder) {
    //     if (preorder.isEmpty()) {
    //         return null;
    //     }
    //     int rootVal = preorder.get(0);
    //     int rootPos = inorder.indexOf(rootVal), leftSubTreeLength = rootPos, rightSubTreeLength = inorder.size() - leftSubTreeLength - 1;
    //     return new TreeNode(rootVal, _buildTree(preorder.subList(1, leftSubTreeLength + 1), inorder.subList(0, leftSubTreeLength)), _buildTree(preorder.subList(leftSubTreeLength + 1, preorder.size()), inorder.subList(rootPos + 1, inorder.size())));
    // }
    // public TreeNode buildTree(int[] preorder, int[] inorder) {
    //     List<Integer> pre = new ArrayList<>(preorder.length), in = new ArrayList<>(inorder.length);
    //     for (int i = 0; i < preorder.length; ++i) {
    //         pre.add(preorder[i]);
    //     } 
    //     for (int i = 0; i < inorder.length; ++i) {
    //         in.add(inorder[i]);
    //     }
    //     return _buildTree(pre, in);
    // }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int preorderIndex = 1, inorderIndex = 0;
        for (; preorderIndex < preorder.length; ++preorderIndex) {
            TreeNode cur = stack.peek();
            if (cur.val != inorder[inorderIndex]) {
                cur.left = new TreeNode(preorder[preorderIndex]);
                stack.push(cur.left);
            } else {
                while (cur.val == inorder[inorderIndex]) {
                    stack.pop();
                    inorderIndex++;
                    if (stack.empty() || (stack.peek().val) != inorder[inorderIndex]) {
                        break;
                    }
                    cur = stack.peek();
                }
                cur.right = new TreeNode(preorder[preorderIndex]);
                stack.push(cur.right);
            }
        }
        return root;
    }
}
// @lc code=end

