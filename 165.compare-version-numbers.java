/*
 * @lc app=leetcode id=165 lang=java
 *
 * [165] Compare Version Numbers
 */

// @lc code=start
class Solution {
    // public int compareVersion(String version1, String version2) {
    //     String[] v1 = version1.split("\\."), v2 = version2.split("\\."); // 注意这里split接受的是一个正则表达式字符串，不能直接输入'.'因为这代表通配符
    //     int vl1 = v1.length, vl2 = v2.length, len = vl1 > vl2 ? vl1 : vl2;
    //     for (int i = 0; i < len; ++i) {
    //         if (i >= vl1 || i >= vl2) {
    //             // 一个比另一个长
    //             String[] remaining = i >= vl1 ? v2 : v1;
    //             boolean allZero = true;
    //             for (int j = i; j < remaining.length; ++j) {
    //                 allZero = remaining[j].chars().allMatch((c)->{return c=='0';});
    //                 if (!allZero) {
    //                     break;
    //                 }
    //             }
    //             if (allZero) {
    //                 return 0;
    //             } else {
    //                 return remaining == v1 ? 1 : -1;
    //             }
    //         }
    //         String[] strip = {v1[i], v2[i]};
    //         int[] vNum = {0, 0};
    //         for (int j = 0; j < 2; ++j) {
    //             int leadingZeroes= 0;
    //             while (leadingZeroes < strip[j].length() && strip[j].charAt(leadingZeroes) == '0') {
    //                 leadingZeroes++;
    //             }
    //             if (leadingZeroes == strip[j].length()) {
    //                 continue;
    //             } else {
    //                 vNum[j] = Integer.parseInt(strip[j].substring(leadingZeroes));
    //             }
    //         }
    //         if (vNum[0] < vNum[1]) {
    //             return -1;
    //         } else if (vNum[0] > vNum[1]) {
    //             return 1;
    //         }
    //     }
    //     return 0;
    // }

    public int compareVersion(String version1, String version2) {
        final int v1l = version1.length(), v2l = version2.length();
        int v1Cursor = 0, v2Cursor = 0;
        // while (v1Cursor < v1l && v2Cursor < v2l) {
        while (v1Cursor < v1l || v2Cursor < v2l) {
            int v1v = 0, v2v = 0;
            char v1c = 0,v2c = 0;
            // 用来跳过0的部分由于我们使用从最高位读取数字累加获得的方法，所以可以直接在累加中处理不影响结果
            // while (v1Cursor < v1l && (v1c = version1.charAt(v1Cursor)) != '.' && v1c == '0') {
            //     v1Cursor++;
            // }
            while (v1Cursor < v1l && (v1c = version1.charAt(v1Cursor)) != '.') {
                v1v *= 10;
                v1v += v1c - '0';
                v1Cursor++;
            }
            // while (v2Cursor < v2l && (v2c = version2.charAt(v2Cursor)) != '.' && v2c == '0') {
            //     v2Cursor++;
            // }
            while (v2Cursor < v2l && (v2c = version2.charAt(v2Cursor)) != '.') {
                v2v *= 10;
                v2v += v2c - '0';
                v2Cursor++;
            }
            if (v1v > v2v) {
                return 1;
            } else if (v1v < v2v) {
                return - 1;
            }
            // if (v1Cursor < v1l) {
                v1Cursor++;
            // }
            // if (v2Cursor < v2l) {
                v2Cursor++;
            // }
        }
        // 其实这个分支可以通过将上边的while条件由and改成or，由于超出长度的那边，生成的新值一定为0仍可以正确比较
        // if (v1Cursor < v1l || v2Cursor < v2l) {
        //     int cursor = v1Cursor < v1l ? v1Cursor : v2Cursor;
        //     String version = v1Cursor < v1l ? version1 : version2;
        //     int ifBigerReturn = v1Cursor < v1l ? 1 : -1;
        //     while (cursor < version.length()) {
        //         char vc = version.charAt(cursor);
        //         if (vc != '0' && vc != '.') {
        //             return ifBigerReturn;
        //         }
        //         cursor++;
        //     }
        // }
        return 0;
    }
}
// @lc code=end

