import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=205 lang=java
 *
 * [205] Isomorphic Strings
 */

// @lc code=start
class Solution {
    // public boolean isIsomorphic(String s, String t) {
    // final int n = s.length();
    // int[] sState = new int[n], tState = new int[n];
    // Map<Character, Integer> memo = new HashMap<>(256);
    // int cnt = 0;
    // String[] ss = { s, t };
    // int[][] sst = { sState, tState };
    // for (int i = 0; i < 2; ++i) {
    // for (int j = 0; j < n; ++j) {
    // char c = ss[i].charAt(j);
    // if (!memo.containsKey(c)) {
    // memo.put(c, cnt++);
    // }
    // sst[i][j] = memo.get(c);
    // }
    // memo.clear();
    // cnt = 0;
    // }
    // for (int i = 0; i < n; ++i) {
    // if (sState[i] != tState[i]) {
    // return false;
    // }
    // }
    // return true;
    // }

    // public boolean isIsomorphic(String s, String t) {
    // final int n = s.length();
    // Map<Character, Character> mapping = new HashMap<>(256);
    // Set<Character> mapped = new HashSet<>(256);
    // // 找到两个字符串相互对应的字母，应该是一个一一映射，如果不是，那就错了
    // for (int i = 0; i < n; ++i) {
    // char sc = s.charAt(i), tc = t.charAt(i);
    // if (mapping.containsKey(sc)) {
    // // 因为要满足一一映射所以要检查两个方向的映射
    // if (mapping.get(sc) != tc) {
    // return false;
    // }
    // } else if (mapped.contains(tc)) {
    // // 可能出现多对一的情况
    // return false;
    // } else {
    // mapping.put(sc, tc);
    // mapped.add(tc);
    // }
    // }
    // return true;
    // }

    public boolean isIsomorphic(String s, String t) {
        // 用一种直接用数组偏移完成一一映射检查的天才做法
        // 0-255 给s做mapping 256-511 给t做mapping
        int[] mapping = new int[512];
        final int n = s.length();
        // int count = 1;
        for (int i = 0; i < n; ++i) {
            char sc = s.charAt(i), tc = t.charAt(i);
            if (mapping[sc] != mapping[tc + 256]) {
                // 进入这里要么是一一映射未满足，要么有一方没有被初始化
                return false;
            }
            // if (mapping[sc] == 0) {
            //     // 第一次出现这个字符时，已经记录了几种字符
            //     mapping[sc] = mapping[tc + 256] = count++;
            // }

            // 还有只记录最后一次出现位置的方式记录映射的  
            mapping[sc] = mapping[tc + 256] = i + 1;
        }
        return true;
    }
}
// @lc code=end
