/*
 * @lc app=leetcode id=3 lang=golang
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start
package main

func lengthOfLongestSubstring(s string) int {
	if len(s) <= 1 {
		return len(s)
	}
	var ele2Idx map[rune]int = map[rune]int{rune(s[0]): 0}
	maxLen := 1
	left := 0
	for i, c := range s {
		if i == 0 {
			continue
		}
		neoLeft, has := ele2Idx[c]
		if has {
			neoLen := i - left
			if neoLen > maxLen {
				maxLen = neoLen
			}
			for ; left <= neoLeft; left++ {
				delete(ele2Idx, rune(s[left]))
			}
		}
		ele2Idx[c] = i
	}
	if len(ele2Idx) > maxLen {
		return len(ele2Idx)
	} else {
		return maxLen
	}
}

// @lc code=end
