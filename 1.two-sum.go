/*
 * @lc app=leetcode id=1 lang=golang
 *
 * [1] Two Sum
 */

// @lc code=start
package main

func twoSum(nums []int, target int) []int {
	var numSet map[int]int = make(map[int]int)
	for i, n := range nums {
		numSet[n] = i
	}
	for i, n := range nums {
		ai, has := numSet[target-n]
		if has && ai != i {
			return []int{i, ai}
		}
	}
	return nil
}

// @lc code=end
