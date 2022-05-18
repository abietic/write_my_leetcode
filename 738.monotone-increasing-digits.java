import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=738 lang=java
 *
 * [738] Monotone Increasing Digits
 */

// @lc code=start
class Solution {
    public int monotoneIncreasingDigits(int n) {
        // 单个数字直接成立
        if (n < 10) {
            return n;
        }
        // 得到数字十进制的每一位，方便接下来处理
        byte[] numArr = Integer.toString(n).getBytes();
        // 由于不像找最近回文数一样大于小于都要考虑，这里只需要找小于等于的数
        // 因此一旦输入的数字本身不是满足条件的，就要让数字减小，而每次进行减小，之后位的位数都直接取9
        // 这时才能保证是最接近输入的，同时这种减小会产生借位，可能会进一步破坏条件，要进一步变9借位
        int wipeBound = numArr.length;
        for (int i = numArr.length - 1; i > 0; --i) {
            if (numArr[i] >= numArr[i - 1]) {
                continue;
            } else {
                numArr[i - 1]--;
                wipeBound = i;
            }
        }
        int start = numArr[0] == '0' ? 1 : 0;
        String numString = new String(numArr, start, wipeBound - start) + "9".repeat(numArr.length - wipeBound);
        return Integer.parseInt(numString);
    }
}
// @lc code=end

