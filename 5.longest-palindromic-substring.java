import java.util.Arrays;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    // public String longestPalindrome(String s) {
    // // 直接暴力做反倒时间和空间上都还可以接受
    // final int n = s.length();
    // int maxLen = 1, maxLenIndex = 0;
    // for (int i = 0; i < n; ++i) {
    // int radius = 0;
    // // 奇数长度的回文
    // for (; i - radius >= 0 && i + radius < n && s.charAt(i - radius) ==
    // s.charAt(i + radius); ++radius);
    // if ((--radius) * 2 + 1 > maxLen) {
    // maxLen = radius * 2 + 1;
    // maxLenIndex = i - radius;
    // }
    // // 偶数长度的回文
    // for (radius = 1; i - radius + 1 >= 0 && i + radius < n && s.charAt(i - radius
    // + 1) == s.charAt(i + radius); ++radius);
    // if ((--radius) * 2 > maxLen) {
    // maxLen = radius * 2;
    // maxLenIndex = i - radius + 1;
    // }
    // }
    // return s.substring(maxLenIndex, maxLenIndex + maxLen);
    // }
    // public String longestPalindrome(String s) {
    // final int n = s.length();
    // if (n == 1) {
    // return s;
    // }
    // // 左闭右开，假定空字符串也为回文字符串
    // boolean[][] isPalidrome = new boolean[n + 1][n + 1];
    // for (int i = 0; i < n; ++i) {
    // isPalidrome[i][i] = isPalidrome[i][i + 1] = true;
    // }
    // int maxLen = 1, maxLenStart = 0;
    // for (int len = 1; len <= n; ++len) {
    // int addedIndex = len - 1;
    // for (int left = 0; left < addedIndex; ++left) {
    // if (s.charAt(left) == s.charAt(addedIndex) && isPalidrome[left +
    // 1][addedIndex]) {
    // isPalidrome[left][addedIndex + 1] = true;
    // int paliLen = addedIndex - left + 1;
    // if (paliLen > maxLen) {
    // maxLen = paliLen;
    // maxLenStart = left;
    // }
    // }
    // }
    // }
    // return s.substring(maxLenStart, maxLenStart + maxLen);
    // }
    public String longestPalindrome(String s) {
        // 尝试一下寻找Longest palindromic substring的Manacher算法
        // 算法的主要思想是在检查一个大的回文的右侧元素为中心的回文时，
        // 由于左侧的回文已经被检查完毕而大回文又有左右镜像对称的特性，因此可以
        // 利用左侧元素的回文信息计算出右侧回文的长度，从而达到减少计算量的目的
        // 把原有的字符串进行加工使所有偶数长度的回文也能像奇数长度的回文一样处理
        String processedS = Arrays.stream(s.split("")).reduce("|", (res, ele) -> {
            return res + ele + "|";
        });
        final int neoLen = processedS.length();
        int[] radiusLen = new int[neoLen];
        int maxRadiusLen = 0, maxCenter = 0;
        int center = 0, radius = 0;
        while (center < neoLen) {
            int leftCursor, rightCursor;
            // 计算当前元素为中心的回文的最大半径大小
            while (((leftCursor = center - radius) >= 0) && ((rightCursor = center + radius) < neoLen)
                    && processedS.charAt(leftCursor) == processedS.charAt(rightCursor)) {
                radius++;
            }
            // 记录这个最大半径大小
            radiusLen[center] = --radius;
            if (radius > maxRadiusLen) {
                maxCenter = center;
                maxRadiusLen = radius;
            }
            // 现在检查这个回文右侧元素为中心的回文大小
            // 用来确定要检查的右侧元素大小，和回文大小限制边界
            int oldCenter = center, oldRadius = radius;
            center++;
            if (center == neoLen) {
                break;
            }
            // 一定不要忘了将半径重置，否则将在下面的while循环在边界跳出，进入下一次外层循环时发生错误
            radius = 0;
            while (center <= oldCenter + oldRadius) {
                // 与当前center在上一个检查的回文中对称位置的center
                int mirroredCenter = oldCenter - (center - oldCenter);
                // 当前center根据对称位置center可以获得它的可能的回文长度的信息
                // 但是由于对称位置的center的回文长度可能超出上一个center的回文匹配长度，
                // 这时当前的center无法和对称位置的center的回文长度一致，因为
                // 当前center右侧字符与与对称center左侧的字符开始出现不同
                int mirroredMaxRadiusLen = oldCenter + oldRadius - center;
                if (radiusLen[mirroredCenter] < mirroredMaxRadiusLen) {
                    // 当镜像元素的最大回文半径小于大回文的边界时，由于镜像对称的特点
                    // 当前检查的元素最大半径，就是镜像元素的最大回文半径
                    radiusLen[center] = radiusLen[mirroredCenter];
                    center++;
                } else if (radiusLen[mirroredCenter] > mirroredMaxRadiusLen) {
                    // 超出大回文的边界，说明左右两侧的元素已经不同，这时可以确定右侧的回文半径已经被截断，比镜像元素的最大回文半径短
                    radiusLen[center] = mirroredMaxRadiusLen;
                    center++;
                } else {
                    // radiusLen[mirroredCenter] == mirroredMaxRadiusLen
                    // 这时镜像元素的回文半径正好到达大回文的边界，
                    // 这时右侧元素是否还能继续扩展回文半径需要检查右侧仍未检查的元素
                    // 因此记录当前半径大小并跳出内层循环，在下一次外层循环中找到当前右侧元素为中心的回文最大半径
                    radius = mirroredMaxRadiusLen;
                    break;
                }
            }
        }
        int realLeft = (maxCenter - maxRadiusLen) / 2, realLen = (2 * maxRadiusLen + 1) / 2;
        return s.substring(realLeft, realLeft + realLen);
    }
}
// @lc code=end
