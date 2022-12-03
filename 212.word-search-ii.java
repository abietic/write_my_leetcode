import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */

// @lc code=start
class Solution {
    private static class TireNode {
        public Map<Character, TireNode> children;
        public TireNode parent;
        public String aWord;
        public TireNode() {
            children = new HashMap<>();
            aWord = null;
            parent = null;
        }
    }
    private static class Tire {
        public TireNode root;
        public Tire() {
            root = new TireNode();
        }
        public void insert(String s) {
            TireNode cur = root, child = null;
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                cur.children.putIfAbsent(c, new TireNode());
                child = cur.children.get(c);
                child.parent = cur;
                cur = child;
            }
            cur.aWord = s;
        }
    }
    
    private static final int[][] steps = {{0,1},{0,-1},{1,0},{-1,0}};
    
    private static void recursiveSearch(char[][] map, int row, int col, TireNode curNode, boolean[][] visited, Map<String, Integer> str2Idx, boolean[] res) {
        // 保证每次进入这个方法都是一个有效的搜索
        visited[row][col] = true;
        if (curNode.aWord != null) {
            res[str2Idx.get(curNode.aWord)] = true;
        }
        for (int[] step : steps) {
            int neoRow = row + step[0], neoCol = col + step[1];
            // 在边界内,未访问过,且有可能含有目标单词
            if (neoRow >= 0 && neoRow < map.length && neoCol >= 0 && neoCol < map[0].length && !visited[neoRow][neoCol] && curNode.children.containsKey(map[neoRow][neoCol])) {
                char c = map[neoRow][neoCol];
                TireNode nxtNode = curNode.children.get(c);
                recursiveSearch(map, neoRow, neoCol, nxtNode, visited, str2Idx, res);
            }
        }
        visited[row][col] = false;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        // write code here
        boolean[] res = new boolean[words.length];
        Map<String, Integer> str2Idx = new HashMap<>();
        Tire tire = new Tire();
        for (int i = 0; i < words.length; ++i) {
            str2Idx.put(words[i], i);
            tire.insert(words[i]);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[0].length; ++col) {
                char c = board[row][col];
                if (tire.root.children.containsKey(c)) {
                    recursiveSearch(board, row, col, tire.root.children.get(c), visited, str2Idx, res);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < res.length; ++i) {
            if (res[i]) {
                ans.add(words[i]);
            }
        }
        return ans;
    }
}
// @lc code=end

