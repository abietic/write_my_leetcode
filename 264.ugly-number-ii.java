
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 */

// @lc code=start
class Solution {
    // public int nthUglyNumber(int n) {
    //     // 不使用long的话，中途的乘法会溢出导致错误的更小的数字出现在堆中
    //     // 导致结果错误
    //     PriorityQueue<Long> heap = new PriorityQueue<>();
    //     heap.add(1l);
    //     long prev = 0;
    //     for (int i = 0; i < n; ++i) {
    //         while (heap.peek() <= prev) {
    //             heap.poll();
    //         }
    //         prev = heap.poll();
    //         heap.add(5* prev);
    //         heap.add(3 * prev);
    //         heap.add(2 * prev);
    //     }
    //     return (int)prev;
    // }

    // dp方法
    public int nthUglyNumber(int n) {
        int[] memo = new int[n];
        memo[0] = 1;
        int t2,t3,t5;
        t2=t3=t5=0;
        for (int i = 1; i < n; ++i) {
            memo[i] = Math.min(memo[t2] * 2, Math.min(memo[t3] * 3, memo[t5] * 5));
            // 下面的几个条件可能同时进入，比如2*3和3*2，5*3和3*5
            if (memo[i] == memo[t2] * 2) {
                t2++;
            }
            if (memo[i] == memo[t3] * 3) {
                t3++;
            }
            if (memo[i] == memo[t5] * 5) {
                t5++;
            }
        }
        return memo[n - 1];
    }
}
// @lc code=end

