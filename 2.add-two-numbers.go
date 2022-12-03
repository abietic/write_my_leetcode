/*
 * @lc app=leetcode id=2 lang=golang
 *
 * [2] Add Two Numbers
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
package main

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	var sentinal ListNode
	cur := &sentinal
	val := 0
	for l1 != nil || l2 != nil {
		l1v, l2v := 0, 0
		if l1 != nil {
			l1v = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			l2v = l2.Val
			l2 = l2.Next
		}
		val = val + l1v + l2v
		cur.Next = &ListNode{val % 10, nil}
		val = val / 10
		cur = cur.Next
	}
	if val > 0 {
		cur.Next = &ListNode{val, nil}
	}
	return sentinal.Next
}

// @lc code=end
