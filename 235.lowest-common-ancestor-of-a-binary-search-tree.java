/*
 * @lc app=leetcode id=235 lang=java
 *
 * [235] Lowest Common Ancestor of a Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 由于是节点值唯一的二叉搜索树
        // 两个节点间的关系有几种情况
        // 如果其中一个就是根节点,最低的公共祖先就是这个节点
        // 如果根节点的值在两个节点值之间,最低公共祖先是这个根节点
        // 否则公共祖先还未找到,都大于根就向右继续搜索,否则向左
        TreeNode cur = root, tmp;
        if (p.val > q.val) {
            tmp = p;
            p = q;
            q = tmp;
        }
        while (cur != null) {
            if (cur == p || cur == q) {
                return cur == p ? p : q;
            }
            if (cur.val > p.val && cur.val < q.val) {
                return cur;
            } else if (cur.val > q.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }
}
// @lc code=end
