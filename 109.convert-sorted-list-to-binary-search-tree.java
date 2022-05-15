/*
 * @lc app=leetcode id=109 lang=java
 *
 * [109] Convert Sorted List to Binary Search Tree
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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

    // private static class ListNode {
    //     int val;
    //     ListNode next;

    //     ListNode() {
    //     }

    //     ListNode(int val) {
    //         this.val = val;
    //     }

    //     ListNode(int val, ListNode next) {
    //         this.val = val;
    //         this.next = next;
    //     }
    // }
    private static final class NodePair {
        public final TreeNode root, forBalance;

        public NodePair(TreeNode root, TreeNode forBalance) {
            this.root = root;
            this.forBalance = forBalance;
        }
        
    }
    private NodePair balanceInsert(TreeNode root, TreeNode forBalance, int val) {
        if (root == null) {
            return new NodePair(new TreeNode(val), new TreeNode(1));
        }
        // sorted升序，一定向最右插
        NodePair res = balanceInsert(root.right, forBalance.right, val);
        root.right = res.root;
        forBalance.right = res.forBalance;
        int leftHeight = forBalance.left == null ? 0 : forBalance.left.val;
        int rightHeight = forBalance.right.val;
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            forBalance.val = Math.max(leftHeight, rightHeight) + 1;
            return new NodePair(root, forBalance);
        }
        // 一定是右子树高度增加导致的不平衡，所以应该只有一种旋转方式
        TreeNode treeTmp, balanceTmp;
        treeTmp = root.right;
        root.right = root.right.left;
        treeTmp.left = root;
        root = treeTmp;
        balanceTmp = forBalance.right;
        forBalance.right = forBalance.right.left;
        balanceTmp.left = forBalance;
        forBalance = balanceTmp;
        rightHeight = forBalance.left.right == null ? 0 : forBalance.left.right.val;
        forBalance.left.val = Math.max(leftHeight, rightHeight) + 1;
        forBalance.val = Math.max(forBalance.left.val, forBalance.right.val) + 1;
        return new NodePair(root, forBalance);
    }
    public TreeNode sortedListToBST(ListNode head) {
        TreeNode root = null, forBalance = null;
        while (head != null) {
            NodePair res = balanceInsert(root, forBalance, head.val);
            root = res.root;
            forBalance = res.forBalance;
            head = head.next;
        }
        return root;
    }
}
// @lc code=end
