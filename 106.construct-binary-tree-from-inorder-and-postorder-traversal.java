import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
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
    // private TreeNode _buildTree(List<Integer> inorder, List<Integer> postorder) {
    //     if (inorder.size() == 0) {
    //         return null;
    //     }
    //     int rootVal = postorder.get(postorder.size() - 1);
    //     int rootPos = inorder.indexOf(rootVal), leftSubTreeLength = rootPos, rightSubTreeLength = inorder.size() - leftSubTreeLength - 1;
    //     TreeNode left = _buildTree(inorder.subList(0, leftSubTreeLength), postorder.subList(0, leftSubTreeLength));
    //     TreeNode right = _buildTree(inorder.subList(rootPos + 1, inorder.size()), postorder.subList(leftSubTreeLength, postorder.size() - 1));
    //     return new TreeNode(rootVal, left, right);
    // } 
    // public TreeNode buildTree(int[] inorder, int[] postorder) {
    //     List<Integer> in = new ArrayList<>(inorder.length), post = new ArrayList<>(postorder.length);
    //     for (int n : inorder) {
    //         in.add(n);
    //     }   
    //     for (int n : postorder) {
    //         post.add(n);
    //     }
    //     return _buildTree(in, post);
    // }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; --i) {
            TreeNode curRoot = stack.peek();
            if (curRoot.val != inorder[inorderIndex]) {
                TreeNode rightSubTree = new TreeNode(postorder[i]);
                curRoot.right = rightSubTree;
                stack.push(rightSubTree);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    curRoot = stack.pop();
                    inorderIndex--;
                }
                TreeNode leftSubTree = new TreeNode(postorder[i]);
                curRoot.left =leftSubTree;
                stack.push(leftSubTree);
            }
        }
        return root;
    }
}
// @lc code=end

