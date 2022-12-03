/*
 * @lc app=leetcode id=273 lang=java
 *
 * [273] Integer to English Words
 */

// @lc code=start
class Solution {
    private static final String[] ones = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private static final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String numberUnderThousandToWord(int num) {
        if (num == 0) {
            return "";
        }
        String res = "";
        if (num / 100 > 0) {
            res += ones[num / 100] + " " + "Hundred" + " ";
            num %= 100;
        }
        if (num / 10 == 1) {
            res += ones[num];
        } else {
            if (num / 10 > 0) {
                res += tens[num / 10] + " ";
                num %= 10;
            }
            if (num != 0) {
                res += ones[num];
            }
        }
        return res.charAt(res.length() - 1) == ' ' ?  res.substring(0, res.length() - 1) : res;
    }
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        } 
        String res = "";
        
        // Integer.MAX_VALUE == 2,147,483,647;
        // billion, million, thousand, hundred, 
        if (num / (int)Math.pow(10, 9) > 0) {
            int billionCount = num / (int)Math.pow(10, 9);
            res += ones[billionCount] + " " + "Billion" + " ";
            num %= (int)Math.pow(10, 9);
        }
        if (num / (int)Math.pow(10, 6) > 0) {
            int millionCount = num / (int)Math.pow(10, 6);
            res += numberUnderThousandToWord(millionCount) + " " + "Million" + " ";
            num %= (int)Math.pow(10, 6);
        }
        if (num / (int)Math.pow(10, 3) > 0) {
            int thousandCount = num / (int)Math.pow(10, 3);
            res += numberToWords(thousandCount) + " " + "Thousand" + " ";
            num %= (int)Math.pow(10, 3);
        }
        res += numberUnderThousandToWord(num);
        return res.charAt(res.length() - 1) == ' ' ?  res.substring(0, res.length() - 1) : res;
    }
}
// @lc code=end

