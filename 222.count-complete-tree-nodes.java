/*
 * @lc app=leetcode id=222 lang=java
 *
 * [222] Count Complete Tree Nodes
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
    public int countNodes(TreeNode root) {
        // 在不管任何二叉树上直接遍历树O(n)复杂度是可以完成的
        // 但是没有用上完全二叉树的特性, 完全二叉树根据其定义
        // 只有最后一层可以不满,并且左侧连续,即倒数第二层,有右子一定有左子
        // 因此要统计节点数,需要直到树高度和最后一层长度
        // 可以使用类似二分的方法计算最后一层的长度
        // 目前的想法是,对于一个子树,走到它的最左侧和最右侧,如果高度一致,认为全满
        // 否则,检测子树的两个左右子树的最右节点和最左节点高度
        // 如果左右与最左一样高到右子树递归操作,否则在左子树递归操作
        if (root == null) {
            // 如果为空树直接返回
            return 0;
        }
        // 首先计算整个树的高度,并判断其最后一层是否为满
        TreeNode left = root.left, right = root.right;
        int height = 0;
        while (left != null) {
            height++;
            left = left.left;
        }
        // 得到当前子树最大高度
        final int lastHeight = height;
        height = 0;
        while (right != null) {
            height++;
            right = right.right;
        }
        if (height == lastHeight) {
            // 如果树的最后一层是满的,可以直接算得结果
            return (int)Math.pow(2, height + 1) - 1;
        }
        int nodeCount = (int) Math.pow(2, lastHeight) - 1;
        nodeCount += lastLayerLen(root, lastHeight);
        return nodeCount;
    }
    private static int lastLayerLen(TreeNode root, int height) {
        // 进入这里的的树应该至少保证最左侧子节点比最右侧子节点高1
        if (height == 1) {
            // 当到达了倒数第二层
            if (root.left == null) {
                return 0;
            } else if (root.right == null) {
                return 1;
            } else {
                // 不应该走到这里
                return 2;
            }
        }
        int curHeight = 0;
        // 如果不是倒数第二层的话左右子节点是保证存在的
        TreeNode left = root.left.right;
        TreeNode right = root.right.left;
        while (left != null) {
            curHeight++;
            left = left.right;
        }
        final int leftHeight = curHeight;
        curHeight = 0;
        while (right != null) {
            curHeight++;
            right = right.left;
        }
        final int rightHeight = curHeight;
        if (leftHeight == rightHeight) {
            if (leftHeight == height - 1) {
                // 左子右子都有最后一层, 在右子半继续找分割处
                return (int)Math.pow(2, leftHeight) + lastLayerLen(root.right, height - 1);
            } else {
                return lastLayerLen(root.left, height - 1);
            }
        } else {
            // 如果已经是左子树全,右子树少一层,那么已经找到分裂处了
            return (int) Math.pow(2, leftHeight);
        }
    }
}
// @lc code=end

