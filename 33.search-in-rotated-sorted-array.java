/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    // 不好，O(n)的
    // private int _search(int[] nums, int target, int leftInclusive, int rightInclusive) {
    //     if (leftInclusive > rightInclusive) {
    //         return -1;
    //     }
    //     if (leftInclusive == rightInclusive) {
    //         return nums[leftInclusive] == target ? leftInclusive : -1;
    //     }
    //     int lv = nums[leftInclusive], rv = nums[rightInclusive], mid = (leftInclusive + rightInclusive) / 2;
    //     int mv = nums[mid];
    //     if (mv == target) {
    //         return mid;
    //     }
    //     if (target > mv) {
    //         // 当目标比mid所指的值大时
    //         if (lv > mv) {
    //             /*     升序1的任意值大于升序2中的任意值
    //              *    |      升序1        |  |mid|   升序2    |
    //              *           mid左侧的的值有大于mid的，右侧的值一定大于mid
    //              */
    //             int mightBeResult = _search(nums, target, leftInclusive, mid - 1);
    //             return mightBeResult == -1? _search(nums, target, mid + 1, rightInclusive) : mightBeResult;
    //         } else {
    //             /*     升序1的任意值大于升序2中的任意值
    //              *    |      升序1    |mid|    |     升序2    |
    //              *           mid左侧的的值一定小于mid的，右侧的值有大于mid的
    //              */
    //             return _search(nums, target, mid + 1, rightInclusive);
    //         }
    //     } else {
    //         // 当目标比mid所指的值小时
    //         if (lv > mv) {
    //             /*     升序1的任意值大于升序2中的任意值
    //              *    |      升序1        |  |mid|   升序2    |
    //              *           mid左侧的的值有小于mid的，右侧的值一定大于mid
    //              */
    //             return _search(nums, target, leftInclusive, mid - 1);
    //         } else {
    //            /*     升序1的任意值大于升序2中的任意值
    //             *    |      升序1    |mid|    |     升序2    |
    //             *           mid左侧的的值一定小于mid的，右侧的值有小于mid的
    //             */
    //             int mightBeResult = _search(nums, target, leftInclusive, mid - 1);
    //             return mightBeResult == -1? _search(nums, target, mid + 1, rightInclusive) : mightBeResult;
    //         }
    //     }
    // }


    // private int _search(int[] nums, int target) {
    //     if (nums.length == 1) {
    //         return nums[0] == target ? 0: -1;
    //     }
    //     /*     升序1的任意值大于升序2中的任意值
    //      *    |      升序1       |     升序2    |
    //      *           
    //      */
    //     // 先用二分搜索找到数列中的最小值
    //     /*    |      升序1        |  |mid|   升序2    |
    //      *           情况1，mid小于升序2后续元素
    //      *    |      升序1    |mid|    |     升序2    |
    //      *           情况2，mid大于所有序列2元素
    //      */
    //     int leftInclusive = 0, rightInclusive = nums.length - 1;
    //     while (leftInclusive < rightInclusive) {
    //         int mid = (leftInclusive + rightInclusive) / 2;
    //         if (nums[mid] > nums[rightInclusive]) {  // 情况2时才会发生 
    //             leftInclusive = mid + 1;
    //         } else { // 情况1或已经成为正常的有序序列，则需要向左侧靠拢
    //             rightInclusive = mid; // 这里不减一，防止mid指向的就是最小值
    //         }
    //     }
    //     // 现在leftInclusive指向最小值
    //     final int rotate = leftInclusive;
    //     // 现在数列中的元素索引与数列未进行rotate时的索引对应关系为
    //     // (originIndex + rotate) % len == curIndex
    //     // 由于rotate进行len次移回原位
    //     // (curIndex + (len - rotate)) % len == originIndex
    //     // curMid == ((originLeft + originRight) / 2 + rotate) % len
    //     leftInclusive = 0;
    //     rightInclusive = nums.length - 1;
    //     while (leftInclusive <= rightInclusive) {
    //         int originMid = (leftInclusive + rightInclusive) / 2;
    //         int curMid = (originMid + rotate) % nums.length;
    //         if (nums[curMid] == target) {
    //             return curMid;
    //         }
    //         if (nums[curMid] < target) {
    //             leftInclusive = originMid + 1;
    //         } else {
    //             rightInclusive = originMid - 1;
    //         }
    //     }
    //     return -1;
    // }

    private int __search(int[] nums, int target) {
        int leftInclusive = 0, rightInclusive = nums.length - 1;
        while (leftInclusive <= rightInclusive) {
            int mid = (leftInclusive + rightInclusive) / 2;
            int midVal;
            if (((nums[mid] < nums[0]) && (target < nums[0])) || ((nums[mid] >= nums[0])&&(target >= nums[0]))) {
                // 如果mid和target都在同一边，如都在升序1或都在升序2中（或都在一个有序序列中）
                // 那么按照正常的二分搜索进行计算就可以了
                midVal = nums[mid];
            } else {
                // 如果mid与target不在同一边
                if (nums[mid] < nums[0]) {
                    // mid在升序2，target在升序1，为了实现“伪有序”数列，将序列2的值全看成无穷大
                    midVal = Integer.MAX_VALUE;
                } else {
                    midVal = Integer.MIN_VALUE;
                }
            }
            if (midVal == target) {
                return mid;
            }
            if (midVal > target) {
                rightInclusive = mid - 1;
            } else {
                leftInclusive = mid + 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        // return _search(nums, target, 0, nums.length - 1);
        // return _search(nums, target);
        return __search(nums, target);
    }
}
// @lc code=end

