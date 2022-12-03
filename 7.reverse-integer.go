/*
 * @lc app=leetcode id=7 lang=golang
 *
 * [7] Reverse Integer
 */

// @lc code=start
package main

import "fmt"

func reverse(x int) int {
	if x == 0 {
		return 0
	}
	// 先将原数字转换成字符串
	prevNumStr := fmt.Sprintf("%d", x)
	// 看是不是负数
	isNeg := false
	if prevNumStr[0] == '-' {
		isNeg = true
		prevNumStr = prevNumStr[1:]
	}
	// 去掉翻转时带来的前驱0
	for prevNumStr[len(prevNumStr)-1] == '0' {
		prevNumStr = prevNumStr[:len(prevNumStr)-1]
	}
	// 由于go的int只保证至少32bits而事实上在leetcode中是64位的
	var res int32 = 0
	// 是否是第一获得值,防止溢出判断误判
	firstAdd := true
	for i := len(prevNumStr) - 1; i >= 0; i-- {
		prevRes := res
		if isNeg {
			for i := 0; i < 9 && !firstAdd; i++ {
				res += prevRes
				if res >= 0 || res >= prevRes {
					return 0
				}
			}
			res = res - int32(prevNumStr[i]-'0')
			if res >= 0 || res >= prevRes {
				return 0
			}

		} else {
			for i := 0; i < 9 && !firstAdd; i++ {
				res += prevRes
				if res <= 0 || res <= prevRes {
					return 0
				}
			}
			res = res + int32(prevNumStr[i]-'0')
			if res <= 0 || res <= prevRes {
				return 0
			}
		}
		firstAdd = false
	}

	return int(res)
}

// @lc code=end
