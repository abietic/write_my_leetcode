import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;



/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
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
    // // 递归实现很简单
    // private static void binaryTreePaths(TreeNode root, List<String> res, String curPath) {
    //     if (root.left == null && root.right == null) {
    //         curPath = curPath + root.val;
    //         res.add(curPath);
    //         return;
    //     }
    //     curPath = curPath + root.val + "->";
    //     if (root.left != null) {
    //         binaryTreePaths(root.left, res, curPath);
    //     }
    //     if (root.right != null) {
    //         binaryTreePaths(root.right, res, curPath);
    //     }
    // }
    public List<String> binaryTreePaths(TreeNode root) {
        // List<String> res = new ArrayList<>();
        // binaryTreePaths(root, res, "");
        // return res;

        List<String> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        // // 中根序遍历加记忆父节点
        // Map<TreeNode, TreeNode> parent = new HashMap<>();
        // TreeNode cur = root;
        // while (cur != null || !stack.isEmpty()) {
        //     while (cur != null) {
        //         stack.addLast(cur);
        //         cur = cur.left;
        //         if (cur != null) {
        //             parent.put(cur, stack.peekLast());
        //         }
        //     }
        //     cur = stack.pollLast();
        //     if (cur.right != null) {
        //         parent.put(cur.right, cur);
        //     }
        //     if (cur.left == null && cur.right == null) {
        //         String s = "";
        //         TreeNode tmp = cur;
        //         while (tmp != null) {
        //             s = "->" + tmp.val + s;
        //             tmp = parent.get(tmp);
        //         }
        //         res.add(s.substring(2));
        //     }
        //     cur = cur.right;
        // }
        // return res;

        // 后根序遍历可以直接得到path
        TreeNode cur = root, lastVisited = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.addLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            if (cur.left == null && cur.right == null) {
                String s = "";
                for (TreeNode t : stack) {
                    s = s + t.val + "->";
                }
                s += cur.val;
                res.add(s);
            }
            if (cur.right == null || cur.right == lastVisited) {
                lastVisited = cur;
                cur = null;
            } else {
                lastVisited = null;
                stack.addLast(cur);
                cur = cur.right;
            }
        }
        return res;
    }
}
// @lc code=end

