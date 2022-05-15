/*
 * @lc app=leetcode id=147 lang=java
 *
 * [147] Insertion Sort List
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
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode neoHead = new ListNode(-5001);
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            ListNode prev,now;
            for (prev = neoHead, now = neoHead.next; now != null; prev = prev.next, now = now.next) {
                if (cur.val < now.val) {
                    break;
                }
            }
            cur.next = now;
            prev.next = cur;
        }
        return neoHead.next;
    }
}
// @lc code=end

