/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
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
    // }

    // private void connect(Node root, Node next) {
    // if (root == null || root.left == null && root.right == null) {
    // return;
    // }
    // Node childNext = null;
    // while (next != null) {
    // if (next.left != null) {
    // childNext = next.left;
    // break;
    // } else if (next.right != null){
    // childNext = next.right;
    // break;
    // } else {
    // next = next.next;
    // }
    // }
    // // 下面的执行root一定还有子节点
    // if (root.right != null) {
    // root.right.next = childNext;
    // if (root.left != null) {
    // root.left.next = root.right;
    // }
    // } else {
    // root.left.next = childNext;
    // }
    // connect(root.right, root.right == null ? null : root.right.next);
    // connect(root.left, root.left == null ? null : root.left.next);
    // }

    // public Node connect(Node root) {
    // connect(root, null);
    // return root;
    // }
    public Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node iter = cur, sentinel = new Node(), prev = sentinel;
            while (iter != null) {
                if (iter.left != null) {
                    prev.next = iter.left;
                    prev = iter.left;
                }
                if (iter.right != null) {
                    prev.next = iter.right;
                    prev = iter.right;
                }
                iter = iter.next;
            }
            cur = sentinel.next;
            sentinel.next = null;
        }
        return root;
    }
}
// @lc code=end
