/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
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

    public ListNode partition(ListNode head, int x) {
        ListNode lessPart = new ListNode(), geqPart = new ListNode(), cursorL = lessPart, cursorGEQ = geqPart, cur = head;
        while (cur != null) {
            if (cur.val < x) {
                cursorL.next = new ListNode(cur.val);
                cursorL = cursorL.next;
            } else {
                cursorGEQ.next = new ListNode(cur.val);
                cursorGEQ = cursorGEQ.next;
            }
            cur = cur.next;
        }
        cursorL.next = geqPart.next;
        return lessPart.next;
    }
}
// @lc code=end
