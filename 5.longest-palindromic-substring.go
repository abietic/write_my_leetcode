/*
 * @lc app=leetcode id=5 lang=golang
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
package main

func longestPalindrome(s string) string {
	n := len(s)
	if n <= 2 {
		if n == 1 || s[0] == s[1] {
			return s
		} else {
			return s[1:]
		}
	}
	// 左开右闭区间内是否构成回文串
	isPar := make([][]bool, n+1)
	for i := 0; i <= n; i++ {
		isPar[i] = make([]bool, n+1)
	}
	for i := 0; i < n; i++ {
		// 单个字符是回文
		isPar[i][i+1] = true
		// 空串是回文
		isPar[i][i] = true
	}
	maxLen := 1
	subString := s[0:1]
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			if s[i] == s[j] && isPar[j+1][i] {
				len := i - j + 1
				if len > maxLen {
					maxLen = len
					subString = s[j : i+1]
				}
				isPar[j][i+1] = true
			}
		}
	}
	return subString
}

// @lc code=end
