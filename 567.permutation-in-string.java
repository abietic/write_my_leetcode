import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=567 lang=java
 *
 * [567] Permutation in String
 */

// @lc code=start
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Map<Character, Integer> pattern = new HashMap<>(), window = new HashMap<>();
        // 因为题目使用的字符串s1,s2都只由英文小写字母组成，所以两个26大小的整型数组即够用
        int[] pattern = new int[26], window = new int[26];
        if (s1.length() > s2.length()) {
            return false;
        }
        // 
        for (int i = 0; i < s1.length(); i++) {
            int patternEle = s1.charAt(i) - 'a', windowEle = s2.charAt(i)- 'a';
            pattern[patternEle]++;
            window[windowEle]++;
        }
        for (int i = s1.length(); i <= s2.length(); ++i) {
            boolean match = true;
            for (int eci = 0; eci < 26; ++eci) {
                if (pattern[eci] != window[eci]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
            if (i == s2.length()) {
                break;
            }
            // 退窗入窗
            int outEle = s2.charAt(i - s1.length()) - 'a', inEle = s2.charAt(i) - 'a';
            window[outEle]--;
            window[inEle]++;
        }
        return false;
    }
}
// @lc code=end

