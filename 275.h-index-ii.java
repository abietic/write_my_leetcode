/*
 * @lc app=leetcode id=275 lang=java
 *
 * [275] H-Index II
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        // // 题目要求logn,这个方法只到n
        // // 可能需要二分搜索
        // int h = 0;
        // for (; h < citations.length; ++h) {
        //     int idx = citations.length - h - 1;
        //     if (citations[idx] <= h) {
        //         break;
        //     }
        // }
        // return h;

        int h = citations.length / 2, step = citations.length / 4;
        step = step > 0 ? step : 1;
        int idx = citations.length - 1 - h;
        while (true) {
            if (h == citations[idx]) {
                break;
            } else if (h < citations[idx]) {
                h += step;
                step = step / 2 > 0 ? step / 2 : 1;
            } else {
                if (h == 0 || h - 1 < citations[idx + 1]) {
                    break;
                }
                h -= step;
                h = h < 0 ? 0 : h;
                step = step / 2 > 0 ? step / 2 : 1;
            }
            idx = citations.length - 1 - h;
            if (idx < 0 || idx >= citations.length) {
                break;
            }
        }
        return h;
    }
}
// @lc code=end

