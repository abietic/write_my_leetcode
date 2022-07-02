import java.util.Arrays;

/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 */

// @lc code=start
class Solution {
    // public int[] longestPalindrome(String s) {
    // // 尝试一下寻找Longest palindromic substring的Manacher算法
    // // 算法的主要思想是在检查一个大的回文的右侧元素为中心的回文时，
    // // 由于左侧的回文已经被检查完毕而大回文又有左右镜像对称的特性，因此可以
    // // 利用左侧元素的回文信息计算出右侧回文的长度，从而达到减少计算量的目的
    // // 把原有的字符串进行加工使所有偶数长度的回文也能像奇数长度的回文一样处理
    // String processedS = Arrays.stream(s.split("")).reduce("|", (res, ele) -> {
    // return res + ele + "|";
    // });
    // final int neoLen = processedS.length();
    // int[] radiusLen = new int[neoLen];
    // // int maxRadiusLen = 0, maxCenter = 0;
    // int center = 0, radius = 0;
    // while (center < neoLen) {
    // int leftCursor, rightCursor;
    // // 计算当前元素为中心的回文的最大半径大小
    // while (((leftCursor = center - radius) >= 0) && ((rightCursor = center +
    // radius) < neoLen)
    // && processedS.charAt(leftCursor) == processedS.charAt(rightCursor)) {
    // radius++;
    // }
    // // 记录这个最大半径大小
    // radiusLen[center] = --radius;
    // // if (radius > maxRadiusLen) {
    // // maxCenter = center;
    // // maxRadiusLen = radius;
    // // }
    // // 现在检查这个回文右侧元素为中心的回文大小
    // // 用来确定要检查的右侧元素大小，和回文大小限制边界
    // int oldCenter = center, oldRadius = radius;
    // center++;
    // if (center == neoLen) {
    // break;
    // }
    // // 一定不要忘了将半径重置，否则将在下面的while循环在边界跳出，进入下一次外层循环时发生错误
    // radius = 0;
    // while (center <= oldCenter + oldRadius) {
    // // 与当前center在上一个检查的回文中对称位置的center
    // int mirroredCenter = oldCenter - (center - oldCenter);
    // // 当前center根据对称位置center可以获得它的可能的回文长度的信息
    // // 但是由于对称位置的center的回文长度可能超出上一个center的回文匹配长度，
    // // 这时当前的center无法和对称位置的center的回文长度一致，因为
    // // 当前center右侧字符与与对称center左侧的字符开始出现不同
    // int mirroredMaxRadiusLen = oldCenter + oldRadius - center;
    // if (radiusLen[mirroredCenter] < mirroredMaxRadiusLen) {
    // // 当镜像元素的最大回文半径小于大回文的边界时，由于镜像对称的特点
    // // 当前检查的元素最大半径，就是镜像元素的最大回文半径
    // radiusLen[center] = radiusLen[mirroredCenter];
    // center++;
    // } else if (radiusLen[mirroredCenter] > mirroredMaxRadiusLen) {
    // // 超出大回文的边界，说明左右两侧的元素已经不同，这时可以确定右侧的回文半径已经被截断，比镜像元素的最大回文半径短
    // radiusLen[center] = mirroredMaxRadiusLen;
    // center++;
    // } else {
    // // radiusLen[mirroredCenter] == mirroredMaxRadiusLen
    // // 这时镜像元素的回文半径正好到达大回文的边界，
    // // 这时右侧元素是否还能继续扩展回文半径需要检查右侧仍未检查的元素
    // // 因此记录当前半径大小并跳出内层循环，在下一次外层循环中找到当前右侧元素为中心的回文最大半径
    // radius = mirroredMaxRadiusLen;
    // break;
    // }
    // }
    // }
    // // int realLeft = (maxCenter - maxRadiusLen) / 2, realLen = (2 * maxRadiusLen
    // + 1) / 2;
    // return radiusLen;
    // }
    // public String shortestPalindrome(String s) {
    // // 需要在字符串前方添加字符的情况：
    // // 如果字符串本身就是回文，不需要添加字符
    // // 如果不是回文，且出现了回文覆盖到了左侧边界的情况，补齐这个没有完成的右侧
    // // 如果没有上述情况，镜像字符串
    // if (s.length() <= 1) {
    // return s;
    // }
    // int[] radiusLen = longestPalindrome(s);
    // int maxRight = 1;
    // for (int center = 0; center < radiusLen.length; ++center) {
    // int radius = radiusLen[center], realLeft = (center - radius) / 2, realLen =
    // (2 * radius + 1) / 2, realRight = realLeft + realLen - 1;
    // if (realLeft == 0) {
    // maxRight = Math.max(maxRight, realRight + 1);
    // }
    // }
    // String sb = new
    // StringBuilder(s.substring(maxRight)).reverse().append(s).toString();
    // return sb;
    // }

    public String shortestPalindrome(String s) {
        // 可以通过翻转字符串和kmp算法生成fail数组找到包含左侧边界的回文子串
        // 例如：对于字符串s "abacd"，通过 s + '#' + s.reverse() 得到， "abacd#dcaba"
        // 可以从例子中看出， s.reverse()成功将包含左边界的回文子串，变成了出现在新字符串末尾的回文子串
        // ，由于回文子串的定义回文子串的翻转仍是本身，用KMP的fail数组即可找到与前缀相等的字符串长度，即我们要确定的长度
        String ss = new StringBuilder(s).append("#").append(new StringBuilder(s).reverse()).toString();
        // 记录当前匹配失败时之前部分可以代表的匹配前缀长度
        int[] fail = new int[ss.length()];
        int cur = 0;
        for (int i = 1; i < ss.length(); ++i) {
            fail[i] = cur;
            if (ss.charAt(cur) == ss.charAt(i)) {
                cur++;
            } else if (cur!=0){
                cur = fail[cur];
                while (true) {
                    if (ss.charAt(cur) == ss.charAt(i)) {
                        cur++;
                        break;
                    } else if (cur != 0){
                        cur = fail[cur];
                    } else {
                        break;
                    }
                }
            }
        }
        // 现在cur存储的值就代表了，包含左边界的回文的长度
        return new StringBuilder(s.substring(cur)).reverse().append(s).toString();
    }
}
// @lc code=end
