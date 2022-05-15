/*
 * @lc app=leetcode id=82 lang=java
 *
 * [82] Remove Duplicates from Sorted List II
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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode psudoHead = new ListNode(), cur = head, slide = head, cursor = psudoHead;
        int count = 0;
        while (cur != null) {
            while (slide!=null && cur.val== slide.val) {
                count++;
                slide = slide.next;
            }
            if (count == 1) {
                cursor.next = new ListNode(cur.val);
                cursor = cursor.next;
            }
            count = 0;
            cur = slide;
        }
        return psudoHead.next;
    }
}
// @lc code=end
