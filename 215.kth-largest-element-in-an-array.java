import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {
    // // 由于是搜索在排序下的第k个元素，所以重复是占用元素个数的
    // // 应该可以用k选择解决，应该也可以用优先队列
    // public int findKthLargest(int[] nums, int k) {
    // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // for (int num : nums) {
    // minHeap.add(num);
    // if (minHeap.size() > k) {
    // minHeap.poll();
    // }
    // }
    // return minHeap.poll();
    // }

    private static int _partition(int[] nums, int leftBound, int rightBound, int k) {
        if (leftBound == rightBound) {
            // 如果长度为1，那么k应该也变为1了，直接返回元素即可
            return nums[leftBound];
        }
        // 进行快速排序中的partition部分，如果
        // 被划到右侧的元素数量大于等于k，在右侧的范围重新开始搜索
        // 如果正好等于k-1，那么划分元素就是目标元素，如果小于k-1
        // k减掉右侧和划分元素，在左侧进行搜索
        int left = leftBound, right = rightBound, midVal = nums[leftBound];
        while (left < right) {
            while (left < right && nums[left] <= midVal) {
                left++;
            }
            while (left < right && nums[right] > midVal) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        while (nums[left] > midVal) {
            left--;
        }
        while (right <= rightBound && nums[right] <= midVal) {
            right++;
        }
        int tmp = nums[left];
        nums[left] = midVal;
        nums[leftBound] = tmp;
        int rightRangeLen = rightBound - right + 1;
        if (k <= rightRangeLen) {
            return _partition(nums, right, rightBound, k);
        } else if (k - 1 == rightRangeLen) {
            return midVal;
        } else {
            return _partition(nums, leftBound, left - 1, k - rightRangeLen - 1);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return _partition(nums, 0, nums.length - 1, k);
    }
}
// @lc code=end
