import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */
import java.util.Map;

// @lc code=start
class Solution {
    // 桶排不是符合要求的
    // public int longestConsecutive(int[] nums) {
    // final int base = (int) Math.pow(10, 9);
    // final int n = nums.length;
    // if (n <= 1) {
    // return n;
    // }
    // int[][] buckets = new int[10][n];
    // int[] waterLevel = new int[10];
    // for (int i = 1; i <= base; i *= 10) {
    // for (int num : nums) {
    // final int bucketIndex = ((num + base) / i) % 10;
    // buckets[bucketIndex][waterLevel[bucketIndex]++] = num;
    // }
    // int iter = 0;
    // for (int j = 0; j < 10; ++j) {
    // for (int k = 0; k < waterLevel[j]; ++k) {
    // nums[iter++] = buckets[j][k];
    // }
    // waterLevel[j] = 0;
    // }
    // }
    // // Arrays.sort(nums);
    // int maxSeqLen = 1, curSeqLen = 1;
    // for (int i = 1; i < n; ++i) {
    // if (nums[i] == nums[i - 1]) {
    // continue;
    // } else if (nums[i] == nums[i - 1] + 1) {
    // curSeqLen++;
    // } else {
    // maxSeqLen = Math.max(curSeqLen, maxSeqLen);
    // curSeqLen = 1;
    // }
    // }
    // return Math.max(maxSeqLen, curSeqLen);
    // }
    // public int longestConsecutive(int[] nums) {
    // // 是个n^2的方法
    // final int n = nums.length;
    // if (n <= 1) {
    // return n;
    // }
    // Map<Integer, Integer> sequences = new HashMap<>(n);
    // Set<Integer> existed = new HashSet<>(n);
    // // 一个数插入范围记录中时有4种情况
    // // 1. 在已有范围内，即重复元素
    // // 2. 扩展了一个范围
    // // 3. 扩展了两个范围，即让两个范围合并
    // // 4. 不能加入任何一个范围
    // int maxSeqLen = 1;
    // for (int num : nums) {
    // if (existed.contains(num)) {
    // continue;
    // } else {
    // existed.add(num);
    // }
    // int[] extended = new int[2];
    // int extendedCount = 0;
    // if (sequences.containsKey(num + 1)) {
    // extended[extendedCount++] = num + 1;
    // }
    // for (int trySeqLen = 1; trySeqLen <= maxSeqLen; ++trySeqLen) {
    // if (sequences.containsKey(num - trySeqLen) && sequences.get(num - trySeqLen)
    // == trySeqLen) {
    // extended[extendedCount++] = num - trySeqLen;
    // break;
    // }
    // }
    // switch (extendedCount) {
    // case 0: {
    // sequences.put(num, 1);
    // break;
    // }
    // case 1: {
    // int len = sequences.get(extended[0]) + 1;
    // maxSeqLen = Math.max(maxSeqLen, len);
    // if (extended[0] < num) {
    // sequences.put(extended[0], len);
    // } else {
    // sequences.remove(extended[0]);
    // sequences.put(num, len);
    // }
    // break;
    // }
    // case 2: {
    // int len = 1 + sequences.remove(extended[0]) + sequences.remove(extended[1]);
    // maxSeqLen = Math.max(maxSeqLen, len);
    // sequences.put(Math.min(extended[0], extended[1]), len);
    // break;
    // }
    // }
    // }
    // return maxSeqLen;
    // }

    // 最简单直接的方法
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>(nums.length);
        for (int n : nums) {
            numSet.add(n);
        }
        int maxSeqLen = 0;
        for (int n : nums) {
            if (!numSet.contains(n)) {
                continue;
            }
            int leftSpan = n;
            while (numSet.remove(leftSpan)) {
                leftSpan--;
            }
            leftSpan++;
            numSet.add(n);
            int rightSpan = n;
            while (numSet.remove(rightSpan)) {
                rightSpan++;
            }
            rightSpan--;
            int len = rightSpan - leftSpan + 1;
            maxSeqLen = Math.max(maxSeqLen, len);
        }
        return maxSeqLen;
    }
}
// @lc code=end
