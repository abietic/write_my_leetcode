import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=166 lang=java
 *
 * [166] Fraction to Recurring Decimal
 */

// @lc code=start
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // 防止overflow需要把参数装到long里，比如将Integer.MIN_VALUE / -1
        long numer = numerator, deno = denominator;
        if (numer % deno == 0) {
            return Long.toString(numer / deno);
        }
        // 把符号计算剥离出来，防止影响求余操作
        String sign = (numer < 0 && deno > 0 || numer > 0 && deno < 0) ? "-" : "";
        numer = Math.abs(numer);
        deno = Math.abs(deno);
        String intPart = Long.toString(numer / deno);
        StringBuilder decPart = new StringBuilder();
        numer %= deno;
        numer *= 10;
        // 记录已经计算过的余数，和它出现时计算的小数位的位置，以便找到循环的开始部分
        Map<Long, Integer> numerMemo = new HashMap<>();
        while (numer != 0) {
            if (numer < deno) {
                // 借一位还不够的情况
                numer *= 10;
                decPart.append('0');
                continue;
            }
            if (numerMemo.containsKey(numer)) {
                // 例如 1 / 6 和 4 / 333
                int zeroCount = 0;
                while (decPart.charAt(decPart.length() - 1) == '0') {
                    decPart.deleteCharAt(decPart.length() - 1);
                    zeroCount++;
                }
                decPart.insert(numerMemo.get(numer) - zeroCount, '(');
                decPart.append(')');
                break;
            } else {
                numerMemo.put(numer, decPart.length());
            }
            decPart.append((char) (numer / deno + '0'));
            numer %= deno;
            numer *= 10;
        }
        return sign + intPart + '.' + decPart;
    }
}
// @lc code=end
