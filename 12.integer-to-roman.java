import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=12 lang=java
 *
 * [12] Integer to Roman
 */

// @lc code=start
class Solution {
    private static final Map<Integer, String> charMap = new TreeMap<>((i1, i2)->i2-i1) {
        {
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }
    };

    public String intToRoman(int num) {
        String res = "";
        while (num > 0) {
            for (Map.Entry<Integer, String> ce : charMap.entrySet()) {
                if (ce.getKey() <= num) {
                    num -= ce.getKey();
                    res += ce.getValue();
                    break;
                }
            }
        }
        return res;
    }
}
// @lc code=end
