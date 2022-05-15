import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */

// @lc code=start
class Solution {
    // 拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        int[] ingressDegrees = new int[numCourses];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            ingressDegrees[prerequisite[0]]++;
            adjList.get(prerequisite[1]).add(prerequisite[0]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            if (ingressDegrees[i] == 0) {
                stack.push(i);
            }
        }
        while (!stack.empty()) {
            int cur = stack.pop();
            res.add(cur);
            for (int adj : adjList.get(cur)) {
                if (--ingressDegrees[adj] < 0) {
                    return new int[0];
                }
                if (ingressDegrees[adj] == 0) {
                    stack.push(adj);
                }
            }
        }
        for (int nodeIngressCount : ingressDegrees) {
            if (nodeIngressCount > 0) {
                return new int[0];
            }
        }
        int[] arrayRes = new int[res.size()];
        for (int i = 0; i < arrayRes.length; ++i) {
            arrayRes[i] = res.get(i);
        }
        return arrayRes;
    }
}
// @lc code=end
