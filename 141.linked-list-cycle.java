/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    // private static class ListNode {
    //     int val;
    //     ListNode next;

    //     ListNode(int x) {
    //         val = x;
    //         next = null;
    //     }
    // }

    public boolean hasCycle(ListNode head) {
        // 如果有环，快指针一定会追上慢指针
        // 没有环的话，快指针会提前到达结束位置
        ListNode fast, slow;
        fast = slow = head;
        while (fast!=null) {
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
