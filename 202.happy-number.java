import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=202 lang=java
 *
 * [202] Happy Number
 */

// @lc code=start
class Solution {
    // 实际上找计算数组上循环本质上与单链表上确定是否有环是类似的，因此也可以使用类似快慢指针的方式判断是否是happy num
    // public boolean isHappy(int n) {
    //     if (n == 1) {
    //         return true;
    //     }
    //     Set<Integer> visited = new HashSet<>();
    //     while (visited.add(n)) {
    //         if (n == 1) {
    //             return true;
    //         }
    //         int sum = 0, tmp;
    //         while (n != 0) {
    //             tmp = n % 10;
    //             sum += tmp * tmp;
    //             n /= 10;
    //         }
    //         n = sum;
    //     }
    //     return false;
    // }

    int nextNum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        int fast, slow;
        fast = slow = n;
        while (fast != 1 && slow != 1) {
            slow = nextNum(slow);
            fast = nextNum(fast);
            if (fast == 1) {
                break;
            }
            fast = nextNum(fast);
            if (slow == fast) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

