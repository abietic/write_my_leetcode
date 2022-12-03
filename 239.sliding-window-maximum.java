import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 */

// @lc code=start
class Solution {
    // public int[] maxSlidingWindow(int[] nums, int k) {
    // int[] res = new int[nums.length - k + 1];
    // // 通过一个红黑树来管理最大值,复杂度为(n - k + 1)*klogk比较耗时
    // TreeMap<Integer, Integer> window = new TreeMap<>();
    // for (int i = 0; i < k; ++i) {
    // window.compute(nums[i], (num, cnt)->cnt==null?1:cnt+1);
    // }
    // res[0] = window.lastKey();
    // for (int i = 0, j = k; j < nums.length; ++i, ++j) {
    // if (nums[i] != nums[j]) {
    // int remain;
    // if ((remain = window.get(nums[i])) == 1) {
    // window.remove(nums[i]);
    // } else {
    // window.put(nums[i], remain - 1);
    // }
    // window.compute(nums[j], (num, cnt)->cnt==null?1:cnt+1);
    // }
    // res[i + 1] = window.lastKey();
    // }
    // return res;
    // }

    // 滑动的窗口有一个道理是后进入的元素比先进入的元素还大的话,那么先加入的元素就失去滑动窗口中的影响力了
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        Deque<int[]> maxQueue = new ArrayDeque<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            if (j - i == k) {
                if (!maxQueue.isEmpty() && i == maxQueue.peekFirst()[1]) {
                    maxQueue.pollFirst();
                }
                ++i;
            }
            int curAdd = nums[j];
            while (!maxQueue.isEmpty() && maxQueue.peekLast()[0] <= curAdd) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(new int[]{curAdd, j});
            if (j - i == k - 1) {
                res[idx++] = maxQueue.peekFirst()[0];
            }
        }
        return res;
    }
}
// @lc code=end
