import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
 * @lc app=leetcode id=71 lang=java
 *
 * [71] Simplify Path
 */

// @lc code=start
class Solution {
    public String simplifyPath(String path) {
        Deque<String> filePath = new ArrayDeque<>();
        String[] filenames = path.split("/");
        for (String filename : filenames) {
            if (filename.isEmpty() || filename.equals(".")) {
                continue;
            }
            if (filename.equals("..")) {
                if (!filePath.isEmpty()) {
                    filePath.pollLast();
                }
                continue;
            }
            filePath.addLast(filename);
        }
        StringBuilder sb = new StringBuilder("/");
        while (!filePath.isEmpty()) {
            sb.append(filePath.pollFirst()).append('/');
        }
        if (sb.length() != 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
// @lc code=end
