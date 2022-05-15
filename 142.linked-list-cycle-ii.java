/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
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
    // int val;
    // ListNode next;

    // ListNode(int x) {
    // val = x;
    // next = null;
    // }
    // }

    public ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        int t = 0;
        while (fast != null) {
            if (fast.next == null) {
                return null;
            }
            // 假设
            fast = fast.next.next; // 速度为2
            slow = slow.next; // 速度为1
            t++;
            if (fast == slow) {
                // 第一次相遇之后，两者在圈中同一位置
                // 那么下次相遇的时间间隔就是圈的长度
                // 且相遇位置与第一次相遇位置相同

                // 慢指针在进入环中，到与快指针相遇走过的总路程小于环的总路程，除非环是一个self-loop而且就是第一个节点
                // 因此假设慢指针相遇时走过的路程为m+x，其中m代表进入环之前的路程，x代表在环中走过的路程
                // 由此可以推得快指针相遇时走过的路程为m+bn+x，其中b>=1（否则无法形成追赶）
                // 由于快指针是慢指针速度的2倍因此两者同一时间内走过的路程比也是1:2
                // 可得2m+2x=m+bn+x -> m = bn - x -> m = (b - 1)n + (n - x)
                // (n - x)代表从环中位置x开始还需要多少路程回到环的开始位置，即从head经过路程m到达的位置
                // 这时可以从等式中发现，从head到环开始位置的路程正好等于，若干圈环的路程（>=0），加上从相遇位置到回到环中起点的路程
                // 因此将两个慢指针一个放在相遇位置，一个放在head，不断前进，他们的相遇位置就会是环的开始位置。（可以发现其对self-loop的单个节点也适用）
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;

    }
}
// @lc code=end
