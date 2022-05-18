
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1005 lang=java
 *
 * [1005] Maximize Sum Of Array After K Negations
 */

// @lc code=start
class Solution {
    // public int largestSumAfterKNegations(int[] nums, int k) {
    // // 根据题目给出的取值范围，k与nums的长度无关，且将同一元素正负反转的的次数没有要求
    // // 那么只要尽可能将所有绝对值较大的负数翻转为正数，如果还有次数剩余，找当前最小的整数进行重复翻转，
    // // 如果剩余次数为偶数则没有变化，如果为奇数则有一个翻转
    // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // int sum = 0;
    // for (int num : nums) {
    // sum += num;
    // minHeap.add(num);
    // }
    // while (true) {
    // if (k > 0) {
    // if (minHeap.peek() >= 0) {
    // if (k % 2 == 1) {
    // sum -= minHeap.peek() * 2;
    // }
    // break;
    // } else {
    // int change = minHeap.poll();
    // sum -= change * 2;
    // minHeap.add(-change);
    // --k;
    // }
    // } else {
    // break;
    // }
    // }
    // return sum;
    // }

    public int largestSumAfterKNegations(int[] nums, int k) {
        // 根据题目的取值范围nums这中的元素只有201种情况，[-100,100]
        // 可以使用对元素根据数组偏移计数的方法（counting-sort，类似桶排？）得到相应元素种类和数量，方便操作
        int[] countings = new int[201];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            countings[num + 100]++;
        }
        for (int i = 0; i < 100 && k > 0; ++i) {
            if (countings[i] == 0) {
                continue;
            }
            if (k < countings[i]) {
                countings[i] -= k;
                countings[200 - i] += k;
                sum -= 2 * k * (i - 100);
                k = 0;
            } else {
                k -= countings[i];
                countings[200 - i] += countings[i];
                sum -= 2 * countings[i] * (i - 100);
                countings[i] = 0;
            }
        }
        if ((k & 1) != 0) {
            for (int i = 100; i < 201; ++i) {
                if (countings[i] > 0) {
                    sum -= 2 * (i - 100);
                    break;
                }
            }
        }
        return sum;
    }
}
// @lc code=end
