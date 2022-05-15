import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // Deque<Integer> nums = new LinkedList<>();
        // while (x != 0) {
        //     nums.add(x % 10);
        //     x /= 10;
        // }
        // while (nums.size() > 0) {
        //     if (nums.peekFirst() == nums.peekLast()) {
        //         if (nums.size() == 1) {
        //             break;
        //         } else {
        //             nums.pollFirst();
        //             nums.pollLast();
        //         }
        //     } else {
        //         return false;
        //     }
        // }
        // return true;
        int tmp = x, rev = 0;
        while (tmp > 0) {
            rev = rev*10+ tmp % 10;
            tmp /= 10;
        }
        return x == rev;
    }
}
// @lc code=end

