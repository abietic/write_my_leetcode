/*
 * @lc app=leetcode id=2095 lang=java
 *
 * [2095] Delete the Middle Node of a Linked List
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
    public ListNode deleteMiddle(ListNode head) {
        // 快慢指针加哨兵节点搞定
        ListNode fast = head, slow = head, pseudoHead = new ListNode(0, head);
        ListNode prev = pseudoHead;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
            prev = slow;
            slow = slow.next;
        }
        prev.next = prev.next == null ? null : prev.next.next;
        return pseudoHead.next;
    }
}
// @lc code=end

