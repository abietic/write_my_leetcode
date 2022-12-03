import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=739 lang=java
 *
 * [739] Daily Temperatures
 */

// @lc code=start
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 来到新的一天,如果之前栈中有没有等到比自身温度高的日子,检查这个日子与存储的日子的大小关系
        // 弹出那些小于当前温度的日子,并把当前日子压栈
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        stack.addLast(new int[]{temperatures[0], 0});
        for (int i = 1; i < temperatures.length; ++i) {
            while (!stack.isEmpty() && temperatures[i] > stack.peekLast()[0]) {
                int[] mv = stack.pollLast();
                res[mv[1]] = i - mv[1];
            }
            stack.add(new int[]{temperatures[i], i});
        }
        return res;
    }
}
// @lc code=end

