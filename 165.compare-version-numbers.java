/*
 * @lc app=leetcode id=165 lang=java
 *
 * [165] Compare Version Numbers
 */

// @lc code=start
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\."); // 注意这里split接受的是一个正则表达式字符串，不能直接输入'.'因为这代表通配符
        int vl1 = v1.length, vl2 = v2.length, len = vl1 > vl2 ? vl1 : vl2;
        for (int i = 0; i < len; ++i) {
            if (i >= vl1 || i >= vl2) {
                // 一个比另一个长
                String[] remaining = i >= vl1 ? v2 : v1;
                boolean allZero = true;
                for (int j = i; j < remaining.length; ++j) {
                    allZero = remaining[j].chars().allMatch((c)->{return c=='0';});
                    if (!allZero) {
                        break;
                    }
                }
                if (allZero) {
                    return 0;
                } else {
                    return remaining == v1 ? 1 : -1;
                }
            }
            String[] strip = {v1[i], v2[i]};
            int[] vNum = {0, 0};
            for (int j = 0; j < 2; ++j) {
                int leadingZeroes= 0;
                while (leadingZeroes < strip[j].length() && strip[j].charAt(leadingZeroes) == '0') {
                    leadingZeroes++;
                }
                if (leadingZeroes == strip[j].length()) {
                    continue;
                } else {
                    vNum[j] = Integer.parseInt(strip[j].substring(leadingZeroes));
                }
            }
            if (vNum[0] < vNum[1]) {
                return -1;
            } else if (vNum[0] > vNum[1]) {
                return 1;
            }
        }
        return 0;
    }
}
// @lc code=end

