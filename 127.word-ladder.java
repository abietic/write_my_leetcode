
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
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

    // 由于单词长度最大为10，且单词仅包含26个小写字母，因此将字典存入hashset后每个单词的可及计算就是最大260
    // 又由于单词的转换不应该出现重复，因此不同的搜索路径也不应该相交，因此使用广度优先搜索，就能找到转换的最短路径
    // public int ladderLength(String beginWord, String endWord, List<String>
    // wordList) {
    // // 虽然是广度优先搜索但是因为要记录层数，所以使用两个栈来模拟每一步
    // // 用一个单独的step记录步数
    // int step = 1;
    // Deque<String> unvisited = new ArrayDeque<>(), tmp = new ArrayDeque<>();
    // // 当前字典中还没有被访问到的元素
    // Set<String> canVisit = new HashSet<>(wordList);
    // if (!canVisit.contains(endWord)) {
    // return 0;
    // }
    // unvisited.push(beginWord);
    // canVisit.remove(beginWord);
    // while (!unvisited.isEmpty()) {
    // while (!unvisited.isEmpty()) {
    // String cur = unvisited.pop();
    // if (cur.equals(endWord)) {
    // return step;
    // }
    // StringBuilder sb = new StringBuilder(cur);
    // for (int i = 0; i < sb.length(); ++i) {
    // char mem = sb.charAt(i);
    // for (char c = 'a'; c <= 'z'; ++c) {
    // sb.setCharAt(i, c);
    // // 如果转换能到达未被访问的元素
    // if (canVisit.remove(sb.toString())) {
    // tmp.push(sb.toString());
    // }
    // }
    // sb.setCharAt(i, mem);
    // }
    // }
    // unvisited = tmp;
    // tmp = new ArrayDeque<>();
    // step++;
    // }
    // return 0;
    // }

    // 双向bfs加速
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> canVisit = new HashSet<>(wordList);
        if (!canVisit.contains(endWord)) {
            return 0;
        }
        Set<String> leftLine = new HashSet<>(), rightLine = new HashSet<>(), tmp = new HashSet<>();
        leftLine.add(beginWord);
        rightLine.add(endWord);
        canVisit.remove(beginWord);
        canVisit.remove(endWord);
        int step = 2;
        while (!leftLine.isEmpty() && !rightLine.isEmpty()) {
            Set<String> curLine = leftLine.size() > rightLine.size() ? rightLine : leftLine;
            Set<String> anotherLine = leftLine == curLine ? rightLine : leftLine;
            for (String cur : curLine) {
                StringBuilder sb = new StringBuilder(cur);
                for (int i = 0; i < sb.length(); ++i) {
                    char mem = sb.charAt(i);
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        sb.setCharAt(i, ch);
                        if (anotherLine.contains(sb.toString())) {
                            return step;
                        }
                        if (canVisit.remove(sb.toString())) {
                            tmp.add(sb.toString());
                        }
                    }
                    sb.setCharAt(i, mem);
                }
            }
            step++;
            if (curLine == rightLine) {
                rightLine = tmp;
            } else {
                leftLine = tmp;
            }
            tmp = new HashSet<>();
        }
        return 0;
    }
}
// @lc code=end
