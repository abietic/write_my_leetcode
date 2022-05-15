/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
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

    // public ListNode sortList(ListNode head) {
    // if (head == null || head.next == null) {
    // return head;
    // }
    // ListNode slow = head, fast = head;
    // while (fast.next != null && fast.next.next != null) {
    // fast = fast.next.next;
    // slow = slow.next;
    // }
    // ListNode tailHead = slow.next;
    // slow.next = null;
    // head = sortList(head);
    // tailHead = sortList(tailHead);
    // ListNode sentinel = new ListNode(), cur = sentinel;
    // while (head != null && tailHead != null) {
    // if (head.val <= tailHead.val) {
    // cur.next = head;
    // head = head.next;
    // } else {
    // cur.next = tailHead;
    // tailHead = tailHead.next;
    // }
    // cur = cur.next;
    // }
    // if (head == null) {
    // cur.next = tailHead;
    // } else {
    // cur.next = head;
    // }
    // return sentinel.next;
    // }
    // public ListNode sortList(ListNode head) {
    // final int radix = (int)Math.pow(10, 5);
    // ListNode[] bucket = new ListNode[10];
    // ListNode[] cur = new ListNode[10];
    // for (int i = 0; i < 10; ++ i) {
    // bucket[i] = new ListNode();
    // cur[i] = bucket[i];
    // }
    // int nowRadix = 1;
    // ListNode neoHead = new ListNode(), neoTail = neoHead;
    // while (nowRadix <= radix) {
    // while (head != null) {
    // int bucketIndex = ((head.val + radix) / nowRadix) % 10;
    // cur[bucketIndex].next = head;
    // cur[bucketIndex] = head;
    // head = head.next;
    // cur[bucketIndex].next = null;
    // }
    // for (int i = 0; i < 10; ++i) {
    // if (bucket[i] == cur[i]) {
    // continue;
    // }
    // neoTail.next = bucket[i].next;
    // neoTail = cur[i];
    // bucket[i].next = null;
    // cur[i] = bucket[i];
    // }
    // head = neoHead.next;
    // neoHead.next = null;
    // neoTail = neoHead;
    // nowRadix *= 10;
    // }
    // return head;
    // }
    public ListNode sortList(ListNode head) {
        int range = 1, len = 0;
        ListNode neoHead = new ListNode(), neoTail = neoHead;
        while (head != null) {
            neoTail.next = head;
            head = head.next;
            neoTail = neoTail.next;
            len++;
        }
        while (true) {
            head = neoHead.next;
            neoHead.next = null;
            neoTail = neoHead;
            if (range >= len) {
                break;
            }
            ListNode leftPart = head, rightPart = head;
            while (leftPart != null) {
                for (int i = 0; i < range && rightPart != null; ++i) {
                    rightPart = rightPart.next;
                }
                int leftRemain = range, rightRemain = range;
                while (leftRemain > 0 && rightRemain > 0 && rightPart != null) {
                    if (leftPart.val <= rightPart.val) {
                        neoTail.next = leftPart;
                        leftPart = leftPart.next;
                        leftRemain--;

                    } else {
                        neoTail.next = rightPart;
                        rightPart = rightPart.next;
                        rightRemain--;
                    }
                    neoTail = neoTail.next;
                    neoTail.next = null;
                }
                if (rightRemain == 0 || rightPart == null) {
                    while (leftRemain > 0 && leftPart != null) {
                        neoTail.next = leftPart;
                        leftPart = leftPart.next;
                        neoTail = neoTail.next;
                        neoTail.next = null;
                        leftRemain--;
                    }
                } else {
                    while (rightRemain > 0 && rightPart != null) {
                        neoTail.next = rightPart;
                        rightPart = rightPart.next;
                        neoTail = neoTail.next;
                        neoTail.next = null;
                        rightRemain--;
                    }
                }
                leftPart = rightPart;
            }
            range <<= 1;
        }
        return head;
    }
}
// @lc code=end
