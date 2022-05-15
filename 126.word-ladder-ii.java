import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

// @lc code=start
class Solution {
    private static int[] Dijsktra(int[][] weight, int start, List<List<Integer>> pathRec) {
        // 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
        // 返回一个int[] 数组，表示从start到它的最短路径长度
        final int n = weight.length; // 顶点个数
        int[] shortPath = new int[n]; // 存放从start到其他各点的最短路径
        int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出
        // List<List<Integer>> pathRec = new ArrayList<>();
        // 初始化，第一个顶点求出
        for (int i = 0; i < n; ++i) {
            List<Integer> prevNodes = new ArrayList<>();
            if (weight[start][i] < n + 1) {
                shortPath[i] = weight[start][i];
                prevNodes.add(start);
            } else {
                shortPath[i] = n + 1;
            }
            pathRec.add(prevNodes);
        }
        shortPath[start] = 0;
        visited[start] = 1;
        for (int count = 1; count <= n - 1; count++) // 要加入n-1个顶点
        {
            int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
            int dmin = n + 1;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && shortPath[i] < dmin) {
                    dmin = shortPath[i];
                    k = i;
                }
            }
            if (k == -1) {
                // 没有可及的节点了
                for (int i = 0; i < n; i++) {
                    if (visited[i] == 0) {
                        shortPath[i] = n + 1;
                        pathRec.get(i).clear();
                    }
                }
                return shortPath;
            }
            // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            visited[k] = 1;
            // 以k为中间点，修正从start到未访问各点的距离
            for (int i = 0; i < n; i++) { 
                if (i == start) {
                    continue;
                }
                int pathLen = shortPath[k] + weight[k][i];
                List<Integer> prevNodes = pathRec.get(i);
                if (pathLen < shortPath[i]) {
                    shortPath[i] = pathLen;
                    prevNodes.clear();
                    prevNodes.add(k);
                } else if (pathLen == shortPath[i]){
                    prevNodes.add(k);
                }
            }
        }
        return shortPath;
    }

    private static boolean adjacent(String from, String to) {
        int differeceCount = 0;
        for (int i = 0; i < from.length(); ++i) {
            if (from.charAt(i) != to.charAt(i) && ((++differeceCount) > 1)) {
                break;
            }
        }
        return differeceCount == 1;
    }

    // private static void dfsWithCutoff (int start, int end, List<Integer> cur, int[][] distance, int cutoff, List<String> wordList, List<List<String>> res) {
    //     if (cur.size() == cutoff) {
    //         if (start == end) {
    //             List<String> aRes = new ArrayList<>();
    //             for (int e : cur) {
    //                 aRes.add(wordList.get(e));
    //             }
    //             res.add(aRes);
    //         }
    //         return;
    //     }
    //     final int n = wordList.size();
    //     for (int i = 0; i < n; ++i) {
    //         if (i == start || distance[start][i] != 1 || cur.contains(i)) {
    //             continue;
    //         }
    //         cur.add(i);
    //         dfsWithCutoff(i, end, cur, distance, cutoff, wordList, res);
    //         cur.remove(cur.size() - 1);
    //     }
    // }

    private static void transform(int start, int end, List<String> cur, List<List<Integer>> pathRec, List<String> wordList, List<List<String>> res) {
        String mightAdd = wordList.get(end);
        if (cur.contains(mightAdd)) {
            // 防止成环
            return;
        }
        cur.add(mightAdd);
        if (end == start) {
            List<String> aRes = new ArrayList<>(cur);
            Collections.reverse(aRes);
            res.add(aRes);
        } else {
            List<Integer> prevNodes = pathRec.get(end);
            for (int prevNode : prevNodes) {
                transform(start, prevNode, cur, pathRec, wordList, res);
            }
        }
        cur.remove(cur.size() - 1);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 所有的单词长度一致，都由26个小写字母组成
        // 最多listLen + 1种状态，每个状态可以有26 * wordLen
        int initState, endState;
        List<List<String>> res = new ArrayList<>();
        if ((endState = wordList.indexOf(endWord)) == -1) {
            return res;
        }
        if ((initState = wordList.indexOf(beginWord)) == -1) {
            initState = wordList.size();
            wordList.add(beginWord);
        }
        // 像Floyd一样松弛？时间复杂度有点大
        final int n = wordList.size();
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                distance[j][i] = (distance[i][j] = (adjacent(wordList.get(i), wordList.get(j)) ? 1 : (n + 1)));
            }
        }
        List<List<Integer>> pathRec = new ArrayList<>();
        // 用djkstra试试
        int[] dijsktra = Dijsktra(distance, initState, pathRec);
        int shortestLen = (dijsktra[endState] >= n + 1) ? 0 : (dijsktra[endState] + 1);
        if (shortestLen == 0) {
            return res;
        }
        transform(initState, endState, new ArrayList<>(), pathRec, wordList, res);
        return res;
        // List<Integer> tmp = new ArrayList<>();
        // tmp.add(initState);
        // dfsWithCutoff(initState, endState, tmp, distance, shortestLen, wordList, res);
        // return res;
    }
}
// @lc code=end

