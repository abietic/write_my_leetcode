
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1005 lang=java
 *
 * [1005] Maximize Sum Of Array After K Negations
 */

// @lc code=start
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            minHeap.add(num);
        }
        while (true) {
            if (k > 0) {
                if (minHeap.peek() >= 0) {
                    if (k % 2 == 1) {
                        sum -= minHeap.peek() * 2;
                    }
                    break;
                } else {
                    int change = minHeap.poll();
                    sum -= change * 2;
                    minHeap.add(-change);
                    --k;
                }
            } else {
                break;
            }
        }
        return sum;
    }
}
// @lc code=end
