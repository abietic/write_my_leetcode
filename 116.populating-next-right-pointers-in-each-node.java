/*
 * @lc app=leetcode id=116 lang=java
 *
 * [116] Populating Next Right Pointers in Each Node
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    // private static class Node {
    //     public int val;
    //     public Node left;
    //     public Node right;
    //     public Node next;

    //     public Node() {
    //     }

    //     public Node(int _val) {
    //         val = _val;
    //     }

    //     public Node(int _val, Node _left, Node _right, Node _next) {
    //         val = _val;
    //         left = _left;
    //         right = _right;
    //         next = _next;
    //     }
    // };

    // private void connect(Node root, Node next) {
    // if (root == null || root.left == null) {
    // // 满二叉树，所以有一个子节点为空就能判断是叶子节点
    // return;
    // }
    // // root.next = next;
    // // 下面的执行，root一定不是叶子节点
    // if (next != null) {
    // // 为右子节点的右方设置
    // root.right.next = next.left;
    // }
    // root.left.next = root.right;
    // connect(root.right, root.right.next);
    // connect(root.left, root.left.next);
    // }
    // public Node connect(Node root) {
    // connect(root, null);
    // return root;
    // }
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur.left != null) {
            Node  iter = cur, prev = null;
            while (iter != null) {
                if (prev != null) {
                    prev.next = iter.left;
                }
                iter.left.next = iter.right;
                prev = iter.right;
                iter = iter.next;
            }
            cur = cur.left;
        }
        return root;
    }
}
// @lc code=end
