
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 */

// @lc code=start
class Solution {
    // private static int[] Dijsktra(int[][] weight, int start) {
    // // 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
    // // 返回一个int[] 数组，表示从start到它的最短路径长度
    // final int n = weight.length; // 顶点个数
    // int[] shortPath = new int[n]; // 存放从start到其他各点的最短路径
    // for (int i = 0; i < n; ++i) {
    // shortPath[i] = n + 1;
    // }
    // int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出
    // // 初始化，第一个顶点求出
    // shortPath[start] = 0;
    // for (int i = 0; i < n; ++i) {
    // if (i != start && weight[start][i] < n + 1) {
    // shortPath[i] = weight[start][i];
    // }
    // }
    // visited[start] = 1;
    // for (int count = 1; count <= n - 1; count++) // 要加入n-1个顶点
    // {
    // int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
    // // int dmin = Integer.MAX_VALUE;
    // int dmin = n + 1;
    // for (int i = 0; i < n; i++) {
    // if (visited[i] == 0 && shortPath[i] < dmin) {
    // dmin = shortPath[i];
    // k = i;
    // }
    // }
    // if (k == -1) {
    // // 没有可及的节点了
    // for (int i = 0; i < n; i++) {
    // if (visited[i] == 0) {
    // shortPath[i] = n + 1;
    // }
    // }
    // return shortPath;
    // }
    // // System.out.println("k=" + k);
    // // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
    // // shortPath[k] = dmin;
    // visited[k] = 1;
    // // 以k为中间点，修正从start到未访问各点的距离
    // for (int i = 0; i < n; i++) { // System.out.println("k="+k);
    // if (visited[i] == 0 && shortPath[k] + weight[k][i] < shortPath[i]) {
    // shortPath[i] = shortPath[k] + weight[k][i];
    // // path[i] = path[k] + "-->" + i;
    // }
    // }
    // }
    // // for (int i = 0; i < n; i++)
    // // System.out.println("从" + start + "出发到" + i + "的最短路径为：" + path[i]);
    // // System.out.println("=====================================");
    // return shortPath;
    // }

    // private static boolean adjacent(String from, String to) {
    // int differeceCount = 0;
    // for (int i = 0; i < from.length(); ++i) {
    // if (from.charAt(i) != to.charAt(i) && ((++differeceCount) > 1)) {
    // break;
    // }
    // }
    // return differeceCount == 1;
    // }

    // public int ladderLength(String beginWord, String endWord, List<String>
    // wordList) {
    // // 所有的单词长度一致，都由26个小写字母组成
    // // 最多listLen + 1种状态，每个状态可以有26 * wordLen
    // int initState, endState;
    // if ((endState = wordList.indexOf(endWord)) == -1) {
    // return 0;
    // }
    // if ((initState = wordList.indexOf(beginWord)) == -1) {
    // initState = wordList.size();
    // wordList.add(beginWord);
    // }
    // // 像Floyd一样松弛？时间复杂度有点大
    // final int n = wordList.size();
    // int[][] distance = new int[n][n];
    // for (int i = 0; i < n; ++i) {
    // for (int j = i + 1; j < n; ++j) {
    // distance[j][i] = (distance[i][j] = (adjacent(wordList.get(i),
    // wordList.get(j)) ? 1 : (n + 1)));
    // }
    // }
    // // 用djkstra试试
    // int[] dijsktra = Dijsktra(distance, initState);
    // return (dijsktra[endState] >= n + 1) ? 0 : (dijsktra[endState] + 1);

    // // for (int k = 0; k < n; ++k) {
    // // for (int i = 0; i < n; ++i) {
    // // for (int j = 0; j < n; ++j) {
    // // distance[i][j] = Math.min(distance[i][j], distance[i][k] +
    // distance[k][j]);
    // // }
    // // }
    // // }
    // // return (distance[initState][endState] < n + 1) ?
    // // (distance[initState][endState] + 1) : 0;
    // }
    public int ladderLength(String beginWord, String endWord, List<String>
    wordList) {
    Stack<String> stack = new Stack<>(), tmp = new Stack<>();
    stack.push(beginWord);
    Set<String> unvisited = new HashSet<>(wordList);
    if (!unvisited.contains(endWord)) {
    return 0;
    }
    unvisited.remove(beginWord);
    int step = 1;
    while (!stack.empty()) {
    while (!stack.empty()) {
    String cur = stack.pop();
    if (cur.equals(endWord)) {
    return step;
    }
    StringBuilder sb = new StringBuilder(cur);
    for (int i = 0; i < cur.length(); ++i) {
    char prev = sb.charAt(i);
    for (char ch = 'a'; ch <= 'z'; ++ch) {
    if (ch == prev) {
    continue;
    }
    sb.setCharAt(i, ch);
    String changedString = sb.toString();
    if (unvisited.remove(changedString)) {
    tmp.push(changedString);
    }
    }
    sb.setCharAt(i, prev);
    }
    }
    stack = tmp;
    tmp = new Stack<>();
    step++;
    }
    return 0;
    }
    // public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    //     // 会超时，还没找到原因
    //     Set<String> wordSet = new HashSet<>(wordList), aSideVisited = new HashSet<>(), anotherSideVisited = new HashSet<>(), tmpVisited = new HashSet<>(), tmp;
    //     wordSet.remove(beginWord);
    //     if (!wordSet.remove(endWord)) {
    //         return 0;
    //     }
    //     aSideVisited.add(beginWord);
    //     anotherSideVisited.add(endWord);
    //     int step = 2;
    //     while (!aSideVisited.isEmpty() && !anotherSideVisited.isEmpty()) {
    //         if (aSideVisited.size() > anotherSideVisited.size()) {
    //             tmp = aSideVisited;
    //             aSideVisited = anotherSideVisited;
    //             anotherSideVisited = tmp;
    //         }
    //         for (String cur : aSideVisited) {
    //             StringBuilder sb = new StringBuilder(cur);
    //             for (int i = 0; i < sb.length(); ++i) {
    //                 char prev = sb.charAt(i);
    //                 for (char ch = 'a'; ch <= 'z'; ++ch) {
    //                     sb.setCharAt(i, ch);
    //                     String changed = sb.toString();
    //                     if (anotherSideVisited.contains(changed)) {
    //                         return step;
    //                     }
    //                     if (wordList.remove(changed)) {
    //                         tmpVisited.add(changed);
    //                     }
    //                 }
    //                 sb.setCharAt(i, prev);
    //             }
    //         }
    //         tmp = aSideVisited;
    //         aSideVisited = tmpVisited;
    //         tmpVisited = tmp;
    //         tmpVisited.clear();
    //         step++;
    //     }
    //     return 0;
    // }
}
// @lc code=end
