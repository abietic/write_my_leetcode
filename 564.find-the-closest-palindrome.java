import java.util.ArrayList;
import java.util.List;
/*
 * @lc app=leetcode id=564 lang=java
 *
 * [564] Find the Closest Palindrome
 */

// @lc code=start
class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        int numLen = n.length();
        List<Long> candidates = new ArrayList<>();
        candidates.add((long)Math.pow(10, numLen) + 1);
        candidates.add((long)Math.pow(10, numLen - 1) - 1);
        boolean ood = numLen % 2 == 1;
        // 多涵盖了一位数字
        String leftPart = n.substring(0, (numLen + 1) / 2);
        long leftPartNum = Long.parseLong(leftPart);
        long[] ls = {leftPartNum - 1, leftPartNum, leftPartNum + 1};
        for (long l : ls) {
            String tmpLeftPart = Long.toString(l);
            StringBuilder sb = new StringBuilder(tmpLeftPart);
            if (ood) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.reverse();
            sb.insert(0, tmpLeftPart);
            candidates.add(Long.parseLong(sb.toString()));
        }
        long abs = num;
        long target = 0;
        for (long candidate : candidates) {
            if (candidate == num) {
                continue;
            }
            long a = Math.abs(candidate - num);
            if (a < abs) {
                abs = a;
                target = candidate;
            } else if (a == abs && candidate < target) {
                target = candidate;
            }
        }
        return Long.toString(target);
    }
}
// @lc code=end

