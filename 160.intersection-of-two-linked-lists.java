import java.util.HashSet;
import java.util.Set;
/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
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
    // int val;
    // ListNode next;

    // ListNode(int x) {
    // val = x;
    // next = null;
    // }
    // }

    // 暴力做法O(mn)
    // public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    // while (headA != null) {
    // ListNode cur = headB;
    // while (cur != null) {
    // if (cur == headA) {
    // return cur;
    // }
    // cur = cur.next;
    // }
    // headA = headA.next;
    // }
    // return null;
    // }

    // 用记忆，时间复杂度O(m+n)，空间复杂度O(n)
    // public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    // Set<ListNode> memo = new HashSet<>();
    // while (headA != null) {
    // memo.add(headA);
    // headA = headA.next;
    // }
    // while (headB != null) {
    // if (memo.contains(headB)) {
    // return headB;
    // }
    // headB = headB.next;
    // }
    // return null;
    // }

    // 先让两条链路等长，然后同时前进，何时相遇哪里就是最早相遇点了
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;
        int cnt = 1;
        while (curA.next != null) {
            curA = curA.next;
            cnt++;
        }
        final int lenA = cnt;
        cnt = 1;
        while (curB.next != null) {
            curB = curB.next;
            cnt++;
        }
        final int lenB = cnt;
        if (curA == curB) {

            curA = headA;
            curB = headB;
            if (lenA > lenB) {
                for (int i = 0; i < (lenA - lenB); ++i) {
                    curA = curA.next;
                }
            } else {
                for (int i = 0; i < (lenB - lenA); ++i) {
                    curB = curB.next;
                }
            }
            while (curA != null) {
                if (curA == curB) {
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }
        return null;
    }
}
// @lc code=end
