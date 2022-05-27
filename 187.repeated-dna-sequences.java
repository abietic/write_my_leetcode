import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=187 lang=java
 *
 * [187] Repeated DNA Sequences
 */

// @lc code=start
class Solution {
    private static final int FAST_HASH_MASK = (1 << 20) - 1;
    private static final Map<Character, Integer> hashMapping = new HashMap<>() {
        {
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }
    };

    private static int frst(String s) {
        int fastHash = 0;
        for (int i = 0; i < 10; ++i) {
            fastHash <<= 2;
            fastHash |= hashMapping.get(s.charAt(i));
        }
        return fastHash;
    }

    private static int nxt(String s, int i, int hash) {
        hash <<= 2;
        hash |= hashMapping.get(s.charAt(i));
        return hash & FAST_HASH_MASK;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        if (s.length() <= 10) {
            return new ArrayList<>(res);
        }
        // 记录长度为10的字符串的hash值和对应开始位置
        Set<Integer> memo = new HashSet<>();
        int hval = frst(s);
        memo.add(hval);
        for (int i = 10; i < s.length(); ++i) {
            hval = nxt(s, i, hval);
            if (!memo.contains(hval)) {
                memo.add(hval);
            } else {
                res.add(s.substring(i - 9, i + 1));
            }
        }
        return new ArrayList<>(res);
    }
}
// @lc code=end
