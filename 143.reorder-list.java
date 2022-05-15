/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
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
    //  private static class ListNode {
    //          int val;
    //          ListNode next;
    //          ListNode() {}
    //          ListNode(int val) { this.val = val; }
    //          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    //      }
    public void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }
        // 快慢指针找到单链表的一般，同时将上半部分在遍历时反序
        // 1->2->3->4->5->null ==> null<-1<-2 , 3->4->5->null
        ListNode slow = head, fast = head, converted = null;
        boolean odd = true;
        while (fast.next != null) {
            if (fast.next.next == null) {
                odd = false;
                break;
            }
            fast = fast.next.next;
            ListNode tmp = slow;
            slow = slow.next;
            tmp.next = converted;
            converted = tmp;
        }
        // 将左半和右半组合上去
        ListNode leftPart = converted, rightPart = odd ? slow.next : slow.next.next, cur = slow;
        if (odd) {
            slow.next = null;
        } else {
            slow.next.next = null;
        }
        while (leftPart != null) {
            ListNode leftHead = leftPart, rightHead = rightPart;
            leftPart = leftPart.next;
            rightPart = rightPart.next;
            leftHead.next = rightHead;
            rightHead.next = cur;
            cur = leftHead;
        }
    }
}
// @lc code=end

