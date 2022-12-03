/*
 * @lc app=leetcode id=4 lang=golang
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
package main

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	// 暴力做法O((m+n)log(m+n))不符合要求
	m, n := len(nums1), len(nums2)
	if m == 0 && n == 0 {
		return 0
	}
	// extNums := make([]int, m+n)
	// copy(extNums, nums1)
	// copy(extNums[len(nums1):], nums2)
	// sort.Ints(extNums)
	// needMean := false
	// if (m+n)%2 == 0 {
	// 	needMean = true
	// }
	// mid := (m + n) / 2
	// if needMean {
	// 	return (float64(extNums[mid]) + float64(extNums[mid-1])) / 2
	// } else {
	// 	return float64(extNums[mid])
	// }

	needMean := false
	if (m+n)%2 == 0 {
		needMean = true
	}
	mid := (m + n) / 2
	if m == 0 || n == 0 {
		var arr []int
		if m == 0 {
			arr = nums2
		} else {
			arr = nums1
		}
		if needMean {
			return (float64(arr[mid]) + float64(arr[mid-1])) / 2
		} else {
			return float64(arr[mid])
		}
	}
	return 0
}

// @lc code=end
