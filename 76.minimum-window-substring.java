/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

// @lc code=start
class Solution {
    private boolean checkValid(int[] charCount) {
        for (int count : charCount) {
            if (count < 0) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String s, String t) {
        // 得到的结果字符串至少要有t的长度
        if (s.length() < t.length()) {
            return "";
        }
        // 把目标只有一个字符的排除了，因为不太好处理
        if (t.length() == 1) {
            if (s.contains(t)) {
                return t;
            }
            return "";
        }
        // 初始化窗口的状态
        char firstChar = 'A', lastChar = 'z';
        int[] charCount = new int[lastChar - firstChar + 1]; // 0 代表这个字符与搜索无关，1代表平了，负代表没平
        for (int i = 0; i < t.length(); ++i) {
            charCount[t.charAt(i) - firstChar]--;
        }
        // 窗口的边界
        int leftBound = 0, rightBound = 0;
        int minLen = Integer.MAX_VALUE, minLeftBound = 0, minRightBound = 0;
        for (int i = 0; i < s.length(); ++i, ++rightBound) {
            char sc = s.charAt(i);
            int index = sc - firstChar;
            if (charCount[index] == 0) { // 无关字符跳过
                if (leftBound == rightBound) {
                    leftBound++;
                }
                continue;
            }
            if (charCount[index] < 0) { // 如果还有没有满足数量条件的字符
                charCount[index]++;
                if (charCount[index] == 0) {
                    charCount[index]++;
                    if (checkValid(charCount)) {
                        // 满足了条件，并且窗口更小了，需要更新窗口记录
                        if (rightBound - leftBound + 1 < minLen) {
                            minLen = rightBound - leftBound + 1;
                            minLeftBound = leftBound;
                            minRightBound = rightBound;
                        }
                        // 滑动窗口的左侧边界
                        // 左侧边界要保证不出现冗余目标字符，否则无法满足最小
                        // 即左侧边界代表的字符，在满足要求的窗口中，
                        // 这个对应的字符在数组的值一定为不会大于1
                        char leftBoundChar = s.charAt(leftBound);
                        int thisIndex = leftBoundChar - firstChar;
                        charCount[thisIndex] = -1;
                        for (leftBound = leftBound + 1; leftBound < rightBound; ++leftBound) {
                            leftBoundChar = s.charAt(leftBound);
                            thisIndex = leftBoundChar - firstChar;
                            if (charCount[thisIndex] == 0) {
                                continue;
                            }
                            if (charCount[thisIndex] > 1) {
                                // 为了满足左侧边界对应字符所需的性质
                                charCount[thisIndex]--;
                            } else if (charCount[thisIndex] <= 1) {
                                break;
                            }
                        }
                    }
                }
            } else {
                // 已经满足或超过要求数量
                // 这个有效字符是左侧边界对应字符时需要移动左侧边界
                // 其它时候计数增加即可
                char leftBoundChar = s.charAt(leftBound);
                if (sc != leftBoundChar) {
                    charCount[index]++;
                } else {
                    for (leftBound = leftBound + 1; leftBound < rightBound; ++leftBound) {
                        leftBoundChar = s.charAt(leftBound);
                        int thisIndex = leftBoundChar - firstChar;
                        if (charCount[thisIndex] == 0) {
                            continue;
                        }
                        if (charCount[thisIndex] > 1) {
                            // 为了满足左侧边界对应字符所需的性质
                            charCount[thisIndex]--;
                        } else if (charCount[thisIndex] <= 1) {
                            break;
                        }
                    }
                }
            }
        }
        return minLeftBound == minRightBound ? "" : s.substring(minLeftBound, minRightBound + 1);
    }
}
// @lc code=end
