/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pseudoHead = new ListNode(), leftCursor = pseudoHead, trueRight = null, midCursor = null, pseudoRight = new ListNode(), rightCursor = pseudoRight, cur = head;
        for (int i = 1; cur != null; i++, cur = cur.next) {
            if (i < left) {
                leftCursor.next = new ListNode(cur.val);
                leftCursor = leftCursor.next;
            } else if (i <= right) {
                if (trueRight == null) {
                    trueRight = new ListNode(cur.val);
                    midCursor = trueRight;
                } else {
                    ListNode neo = new ListNode(cur.val, midCursor);
                    midCursor = neo;
                }
            } else {
                rightCursor.next = new ListNode(cur.val);
                rightCursor = rightCursor.next;
            }
        }
        leftCursor.next = midCursor;
        trueRight.next = pseudoRight.next;
        return pseudoHead.next;
    }
}
// @lc code=end
