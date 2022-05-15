/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
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
    // public class ListNode {
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
    private int len(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    public ListNode rotateRight(ListNode head, int k) {
        int listLen = this.len(head);
        if (listLen <= 1) {
            return head;
        }
        int rotate = k % listLen;
        if (rotate == 0) {
            return head;
        }
        ListNode cursor = head;
        for (int i = 0; i < listLen - rotate - 1; ++i, cursor = cursor.next);
        ListNode resHead = cursor.next;
        cursor.next = null;
        cursor = resHead;
        while(cursor.next != null){
            cursor = cursor.next;
        }
        cursor.next = head;
        return resHead;
    }
}
// @lc code=end
