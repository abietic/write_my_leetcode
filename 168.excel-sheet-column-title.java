import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=168 lang=java
 *
 * [168] Excel Sheet Column Title
 */

// @lc code=start
class Solution {
    public String convertToTitle(int columnNumber) {
        Deque<Character> stack = new ArrayDeque<>();
        // 由于没有代表0的字母如果求余得到的是0，那么需要向前借位，并使用z
        do{
            int cur = columnNumber % 26;
            columnNumber /= 26;
            if (cur == 0) {
                columnNumber--;
                cur = 26;
            }
            stack.push((char)(cur - 1 + 'A'));
        } while (columnNumber != 0);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
// @lc code=end

