import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=1462 lang=java
 *
 * [1462] Course Schedule IV
 */

// @lc code=start
class Solution {
    // public List<Boolean> checkIfPrerequisite(int numCourses, int[][]
    // prerequisites, int[][] queries) {
    // // 直接传播呢
    // // 前驱关系表
    // BitSet[] relations = new BitSet[numCourses];
    // for (int i = 0; i < numCourses; ++i) {
    // relations[i] = new BitSet(numCourses);
    // }
    // // 构造拓扑
    // List<List<Integer>> adjList = new ArrayList<>(numCourses);
    // int[] ingressDegrees = new int[numCourses];
    // for (int i = 0; i < numCourses; ++i) {
    // adjList.add(new ArrayList<>());
    // }
    // for (int[] prerequisite : prerequisites) {
    // ingressDegrees[prerequisite[1]]++;
    // adjList.get(prerequisite[0]).add(prerequisite[1]);
    // }
    // // 遍历拓扑
    // Stack<Integer> stack = new Stack<>();
    // for (int i = 0; i < numCourses; ++i) {
    // if (ingressDegrees[i] == 0) {
    // stack.push(i);
    // }
    // }
    // while (!stack.empty()) {
    // int cur = stack.pop();
    // for (int adj : adjList.get(cur)) {
    // // 节点标记自己的前驱节点
    // relations[adj].set(cur);
    // // 节点继承前驱节点集合
    // relations[adj].or(relations[cur]);
    // if (--ingressDegrees[adj] == 0) {
    // // 已经得到了他的所有前驱信息，开始向它的后继提供信息
    // stack.push(adj);
    // }
    // }
    // }
    // List<Boolean> res = new ArrayList<>();
    // for (int[] query : queries) {
    // res.add(relations[query[1]].get(query[0]));
    // }
    // return res;
    // }
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Floyd ?
        boolean[][] relations = new boolean[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            relations[prerequisite[0]][prerequisite[1]] = true;
        }
        for (int k = 0; k < numCourses; ++k) {
            for (int i = 0; i < numCourses; ++i) {
                for (int j = 0; j < numCourses; ++j) {
                    if (relations[i][k] && relations[k][j]) {
                        // Floyd算法的松弛部分
                        // 如果i是k的前驱，且k是j的前驱
                        // 那么i也是j的前驱
                        relations[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(relations[query[0]][query[1]]);
        }
        return res;
    }
}
// @lc code=end
