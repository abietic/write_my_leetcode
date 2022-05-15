import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=68 lang=java
 *
 * [68] Text Justification
 */

// @lc code=start
class Solution {
    private String fillWords(List<String> temp, int maxWidth, int currentLeastTotal) {
        StringBuilder sb = new StringBuilder();
        int blankCount = maxWidth - currentLeastTotal + temp.size();
        if (temp.size() == 1) {
        sb.append(temp.get(0)).append(" ".repeat(blankCount));
        return sb.toString();
        }
        int blankslotCount = temp.size() - 1;
        int[] blankCounts = new int[blankslotCount];
        int average = blankCount / blankslotCount;
        for (int i = 0; i < blankslotCount; ++i) {
            blankCounts[i] = average;
        }
        int remainBlanks = blankCount % blankslotCount;
        for (int i = 0; remainBlanks > 0; --remainBlanks) {
            blankCounts[i++] += 1;
            i %= blankslotCount;
        }
        int blankIndex = 0;
        for (String s : temp) {
            sb.append(s);
            if (blankIndex < blankslotCount) {
                sb.append(" ".repeat(blankCounts[blankIndex++]));
            }
        }
        return sb.toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>(), temp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int currentLeastTotal = 0;
        for (int idx = 0; idx < words.length; ++idx) {
            String word = words[idx];
            // 最后一个放入的词，尾部不用加空格
            if (maxWidth <= currentLeastTotal + word.length()) {
                // 这一行填满了，看一下当前word能不能被放在本行
                if (currentLeastTotal + word.length() == maxWidth) {
                    // 正好这个词可以放入，且不需要添加额外的空格
                    for (String s : temp) {
                        sb.append(s).append(' ');
                    }
                    sb.append(word);
                    res.add(sb.toString());
                    sb = new StringBuilder();
                    temp.clear();
                    currentLeastTotal = 0;
                } else {
                    // 放不进这行，这行要填充额外的空格
                    res.add(fillWords(temp, maxWidth, currentLeastTotal));
                    temp.clear();
                    currentLeastTotal = 0;
                    idx--;
                }
            } else {
                // 如果还能继续填词
                temp.add(word);
                currentLeastTotal += word.length() + 1;
            }
        }
        if (!temp.isEmpty()) {
            // 如果还有词没有被填入行，且尾部一定有空格
            // 如果没有空格就触发第一个if下的第一个子if了
            int blankCount = maxWidth - currentLeastTotal;
            // if (temp.size() == 1) {
            //     sb.append(temp.get(0));
            // } else {
                for (String s : temp) {
                    sb.append(s).append(' ');
                }
            // }
            sb.append(" ".repeat(blankCount));
            res.add(sb.toString());
        }
        return res;
    }
}
// @lc code=end
