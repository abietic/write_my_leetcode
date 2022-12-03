/*
 * @lc app=leetcode id=8 lang=golang
 *
 * [8] String to Integer (atoi)
 */

// @lc code=start
package main

import (
	"math"
	"strings"
	"unicode"
)

func myAtoi(s string) int {
	// 去掉前驱空格
	s = strings.TrimLeft(s, " ")
	// 防止全空白串
	if len(s) == 0 {
		return 0
	}
	// 看字符串是否有符号
	isNeg := false
	var res int32 = 0
	switch s[0] {
	case '-':
		isNeg = true
		s = s[1:]
	case '+':
		s = s[1:]
	default:
		// 如果首个字符不是符号那需要是数字才可能是合法的字符串
		if !unicode.IsDigit(rune(s[0])) {
			return 0
		}
	}
	// 去掉前驱的0为了防止检查溢出的操作误判
	s = strings.TrimLeft(s, "0")
	// 生成数字的时候用的是reverse integer里我的暴力做法
	firstAdd := true
	for i := 0; i < len(s); i++ {
		// 检查数字字符串是否合法
		if !unicode.IsDigit(rune(s[i])) {
			break
		}
		prevRes := res
		if isNeg {
			for i := 0; i < 9 && !firstAdd; i++ {
				res += prevRes
				if res >= 0 || res >= prevRes {
					return math.MinInt32
				}
			}
			res = res - int32(s[i]-'0')
			if res >= 0 || res >= prevRes {
				return math.MinInt32
			}

		} else {
			for i := 0; i < 9 && !firstAdd; i++ {
				res += prevRes
				if res <= 0 || res <= prevRes {
					return math.MaxInt32
				}
			}
			res = res + int32(s[i]-'0')
			if res <= 0 || res <= prevRes {
				return math.MaxInt32
			}
		}
		firstAdd = false
	}
	return int(res)
}

// @lc code=end
