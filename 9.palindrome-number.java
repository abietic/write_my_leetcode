import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */

// @lc code=start
class Solution {
    // public boolean isPalindrome(int x) {
    //     if (x < 0) {
    //         return false;
    //     }
    //     // Deque<Integer> nums = new LinkedList<>();
    //     // while (x != 0) {
    //     //     nums.add(x % 10);
    //     //     x /= 10;
    //     // }
    //     // while (nums.size() > 0) {
    //     //     if (nums.peekFirst() == nums.peekLast()) {
    //     //         if (nums.size() == 1) {
    //     //             break;
    //     //         } else {
    //     //             nums.pollFirst();
    //     //             nums.pollLast();
    //     //         }
    //     //     } else {
    //     //         return false;
    //     //     }
    //     // }
    //     // return true;
    //     int tmp = x, rev = 0;
    //     while (tmp > 0) {
    //         rev = rev*10+ tmp % 10;
    //         tmp /= 10;
    //     }
    //     return x == rev;
    // }

    public boolean isPalindrome(int x) {
        if (x < 10) {
            return x >= 0;
        }
        int len = 0;
        int tar = 0;
        boolean oodPar = false;
        int parLen = 0;
        boolean par = false;
        while (x > 0) {
            len++;
            int bs = x % 10;
            x /= 10;
            if (x == tar) {
                oodPar = true;
                parLen = len - 1;
            }
            tar = tar * 10  + bs;
            if (tar == x) {
                par = true;
                parLen = len;
            }
        }
        // 保证100和10能正确地判别
        return ((len &  1) != 0) ? (parLen == len / 2) : par;
    }
}
// @lc code=end

