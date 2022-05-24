import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
 * @lc app=leetcode id=151 lang=java
 *
 * [151] Reverse Words in a String
 */

// @lc code=start
class Solution {
    public String reverseWords(String s) {
        // 模拟 in place
        byte[] str = s.getBytes();
        // 先消掉多余的空格
        int left = 0, right = 0, cursor = 0;
        while (right < str.length) {
            while (right < str.length && str[right] == ' ') {
                left++;
                right++;
            }
            // 跳过尾部空格
            if (right == str.length) {
                break;
            }
            while (right < str.length && str[right] != ' ') {
                right++;
            }
            if (cursor == left) {
                left = right;
                cursor = left;
            } else {
                while (left < right) {
                    str[cursor++] = str[left++];
                }
            }
            if (cursor < str.length) {
                str[cursor++] = ' ';
            }
        }
        if (cursor >= 1 && str[cursor - 1] == ' ') {
            cursor--;
        }
        final int len = cursor;
        // 将整个字符串反序，这时所有的单词的位置已经反序完成了。
        // 但是单词内部的字母也反序了，需要再反序回来
        left = 0;
        right = len - 1;
        while (left < right) {
            byte tmp = str[left];
            str[left] = str[right];
            str[right] = tmp;
            left++;
            right--;
        }
        // 将所有单词的内部字母反序
        left = right = 0;
        while (right < len) {
            while (right < len && str[right] != ' ') {
                right++;
            }
            int rightMem = right;
            right--;
            while (left < right) {
                byte tmp = str[left];
                str[left] = str[right];
                str[right] = tmp;
                left++;
                right--;
            }
            left = right = rightMem + 1;
        }
        return new String(str, 0, len);
    }
}
// @lc code=end
