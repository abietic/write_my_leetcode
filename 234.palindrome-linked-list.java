/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
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
    public boolean isPalindrome(ListNode head) {
        int count = 0;
        for (ListNode cur = head; cur != null; cur = cur.next, count++);
        ListNode cur = head;
        for (int skip = 0; skip < count / 2; ++skip, cur = cur.next);
        if (count  % 2 == 1) {
            cur = cur.next;
        }
        ListNode tmp = head, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = tmp;
            tmp = cur;
            cur = nxt;
        }
        for (int i = 0; i < count / 2; ++i) {
            if (tmp.val != head.val) {
                return false;
            }
            tmp = tmp.next;
            head = head.next;
        }
        return true;
    }
}
// @lc code=end

