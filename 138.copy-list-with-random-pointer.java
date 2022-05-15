import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    // private class Node {
    // int val;
    // Node next;
    // Node random;

    // public Node(int val) {
    // this.val = val;
    // this.next = null;
    // this.random = null;
    // }
    // }

    // public Node copyRandomList(Node head) {
    //     Map<Node, Node> mapping = new HashMap<>();
    //     Node newHead = null, cur = null;
    //     while (head != null) {
    //         Node newCur = new Node(head.val);
    //         newCur.random = head.random;
    //         if (newHead == null) {
    //             newHead = cur = newCur;
    //         } else {
    //             cur.next = newCur;
    //             cur = newCur;
    //         }
    //         mapping.put(head, cur);
    //         head = head.next;
    //     }
    //     cur = newHead;
    //     while (cur != null) {
    //         if (cur.random != null) {
    //             cur.random = mapping.get(cur.random);
    //         }
    //         cur = cur.next;
    //     }
    //     return newHead;
    // }

    public Node copyRandomList(Node head) {
        Node oldHead = head, newHead = null, cur = null;
        while (head != null) {
            Node next = head.next;
            Node newCur = new Node(head.val);
            newCur.random = head.random;
            head.next = newCur;
            newCur.next = next;
            head = next;
        }
        head = oldHead;
        while (head != null) {
            Node next = head.next.next;
            Node newCur = head.next;
            if (newCur.random != null) {
                newCur.random = newCur.random.next;
            }
            head = next;
        }
        head = oldHead;
        while (head != null) {
            Node next = head.next.next;
            Node newCur = head.next;
            if (newHead == null) {
                newHead = cur = newCur;
            } else {
                cur.next = newCur;
                cur = newCur;
            }
            head.next = next;
            head = next;
        }
        return newHead;
    }
}
// @lc code=end
