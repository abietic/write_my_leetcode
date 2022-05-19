import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * @lc app=leetcode id=564 lang=java
 *
 * [564] Find the Closest Palindrome
 */

// @lc code=start
class Solution {
    // public String nearestPalindromic(String n) {
    // long num = Long.parseLong(n);
    // int numLen = n.length();
    // List<Long> candidates = new ArrayList<>();
    // candidates.add((long)Math.pow(10, numLen) + 1);
    // candidates.add((long)Math.pow(10, numLen - 1) - 1);
    // boolean ood = numLen % 2 == 1;
    // // 多涵盖了一位数字
    // String leftPart = n.substring(0, (numLen + 1) / 2);
    // long leftPartNum = Long.parseLong(leftPart);
    // long[] ls = {leftPartNum - 1, leftPartNum, leftPartNum + 1};
    // for (long l : ls) {
    // String tmpLeftPart = Long.toString(l);
    // StringBuilder sb = new StringBuilder(tmpLeftPart);
    // if (ood) {
    // sb.deleteCharAt(sb.length() - 1);
    // }
    // sb.reverse();
    // sb.insert(0, tmpLeftPart);
    // candidates.add(Long.parseLong(sb.toString()));
    // }
    // long abs = num;
    // long target = 0;
    // for (long candidate : candidates) {
    // if (candidate == num) {
    // continue;
    // }
    // long a = Math.abs(candidate - num);
    // if (a < abs) {
    // abs = a;
    // target = candidate;
    // } else if (a == abs && candidate < target) {
    // target = candidate;
    // }
    // }
    // return Long.toString(target);
    // }

    public String nearestPalindromic(String n) {
        if (n.length() < 2) {
            return Integer.toString(Integer.parseInt(n) - 1);
        }
        long origin = Long.parseLong(n);
        List<Long> candidates = new ArrayList<>();
        // 两种发生长度改变的特殊情况，需要特殊讨论
        candidates.add(Long.parseLong("9".repeat(n.length() - 1)));
        candidates.add(Long.parseLong("1" + "0".repeat(n.length() - 1) + "1"));
        boolean isOod = n.length() % 2 == 0 ? false : true;
        String upperHalf = n.substring(0, n.length() / 2 + (isOod ? 1 : 0));
        int[] changes = { -1, 0, 1 };
        for (int change : changes) {
            long upper = Long.parseLong(upperHalf) + change;
            String tmp = Long.toString(upper);
            int lenDiff = tmp.length() - upperHalf.length();
            if (lenDiff != 0) {
                // 发生了特殊情况
                continue;
            }
            StringBuilder sb = new StringBuilder(tmp);
            sb.reverse();
            String tar = isOod ? tmp + sb.substring(1) : tmp + sb;
            candidates.add(Long.parseLong(tar));
        }
        String res = null;
        long minDist = Long.MAX_VALUE;
        Collections.sort(candidates);
        for (long l : candidates) {
            if (l == origin) {
                continue;
            }
            long dist = Math.abs(l - origin);
            if (dist < minDist) {
                minDist = dist;
                res = Long.toString(l);
            }
        }
        return res;
    }
}
// @lc code=end
