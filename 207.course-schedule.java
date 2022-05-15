import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 */

// @lc code=start
class Solution {
    // private static boolean hasCycle(int start, Map<Integer, Set<Integer>> matrix)
    // {
    // Set<Integer> visited = new HashSet<>();
    // Stack<Integer> stack = new Stack<>();
    // if (matrix.containsKey(start)) {
    // for (int adjNode : matrix.get(start)) {
    // stack.push(adjNode);
    // }
    // }
    // while (!stack.empty()) {
    // int cur = stack.pop();
    // if (cur == start) {
    // return true;
    // }
    // if (visited.contains(cur)) {
    // continue;
    // }
    // visited.add(cur);
    // if (matrix.containsKey(cur)) {
    // for (int adjNode : matrix.get(cur)) {
    // stack.push(adjNode);
    // }
    // }
    // }
    // return false;
    // }
    // public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Map<Integer, Set<Integer>> matrix = new HashMap<>();
    // for (int[] prerequisite : prerequisites) {
    // Set<Integer> adj;
    // if (matrix.containsKey(prerequisite[1])) {
    // adj = matrix.get(prerequisite[1]);
    // } else {
    // adj = new HashSet<>();
    // matrix.put(prerequisite[1], adj);
    // }
    // adj.add(prerequisite[0]);
    // }
    // for (int start : matrix.keySet()) {
    // if (hasCycle(start, matrix)) {
    // return false;
    // }
    // }
    // return true;
    // }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 拓扑排序
        // 节点作为课程，节点的出边代表其是另一个课程的前置课程
        // 记录节点的入度
        // 从没有入边但有出边的节点开始遍历，减小经过节点的入度，会把节点的入度减为负数即产生环
        // 或是由于成环导致没有开始遍历的节点，从而导致入度大于0
        // 节点的入度
        Map<Integer, Integer> ingressCount = new HashMap<>();
        // 只有出边的节点集合
        Set<Integer> onlyHasEgress = new HashSet<>();
        // 图
        Map<Integer, Set<Integer>> matrix = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            onlyHasEgress.add(prerequisite[1]);
            Set<Integer> adj;
            if (matrix.containsKey(prerequisite[1])) {
                adj = matrix.get(prerequisite[1]);
            } else {
                adj = new HashSet<>();
                matrix.put(prerequisite[1], adj);
            }
            adj.add(prerequisite[0]);
            if (ingressCount.containsKey(prerequisite[0])) {
                ingressCount.put(prerequisite[0], ingressCount.get(prerequisite[0]) + 1);
            } else {
                ingressCount.put(prerequisite[0], 1);
            }
        }
        // 将既有出边又有入边的节点从带有出边的节点集合中删去
        onlyHasEgress.removeAll(ingressCount.keySet());
        Stack<Integer> stack = new Stack<>();
        for (int start : onlyHasEgress) {
            stack.push(start);
        }
        // 如果不记录访问次数，成环会导致无限循环
        while (!stack.empty()) {
            int cur = stack.pop();
            if (matrix.containsKey(cur)) {
                for (int adj : matrix.get(cur)) {
                    // 更新入度
                    int adjIngressCount = ingressCount.get(adj);
                    if (adjIngressCount == 0) {
                        return false;
                    } else {
                        ingressCount.put(adj, adjIngressCount - 1);
                    }
                    // 加入访问栈，只有在前置课程都完成时才对其进行展开
                    if (adjIngressCount == 1) {
                        stack.push(adj);
                    }
                }
            }
        }
        // 完全成环情况
        for (int nodeIngressCount : ingressCount.values()) {
            if (nodeIngressCount > 0) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
