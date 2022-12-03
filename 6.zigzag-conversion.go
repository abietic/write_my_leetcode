/*
 * @lc app=leetcode id=6 lang=golang
 *
 * [6] Zigzag Conversion
 */

// @lc code=start
package main

func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	sepStrs := make([]string, numRows)
	for i := 0; i < numRows; i++ {
		sepStrs[i] = ""
	}
	isDownward := true
	for cursor, colNo, n := 0, 0, len(s); cursor < n; cursor++ {
		if isDownward {
			sepStrs[colNo] += s[cursor : cursor+1]
			colNo++
			if colNo == numRows {
				isDownward = false
				colNo -= 2
			}
		} else {
			sepStrs[colNo] += s[cursor : cursor+1]
			colNo--
			if colNo < 0 {
				isDownward = true
				colNo += 2
			}
		}
	}
	res := ""
	for i := 0; i < numRows; i++ {
		res += sepStrs[i]
	}
	return res
}

// @lc code=end
