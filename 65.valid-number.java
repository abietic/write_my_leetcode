/*
 * @lc app=leetcode id=65 lang=java
 *
 * [65] Valid Number
 */

// @lc code=start
class Solution {
    private int cursor;
    private String input;

    private boolean integerPart(boolean signAware) {
        char peek = this.peek();
        if (peek == '+' || peek == '-') {
            if (signAware) {
                // 可以出现正负号，如果有正负号就承认并继续
                this.next();
            } else {
                // 如果在不想出现正负号的位置出现正负号也是不合法的
                return false;
            }
        }
        boolean digitFound = false;
        while (!this.eof()) {
            switch (this.peek()) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    digitFound = true;
                    this.next();
                    break;
                }
                case '.':
                case 'e':
                case 'E': {
                    return digitFound;
                }
                default:
                    return false;
            }
        }
        return digitFound;
    }

    private boolean integerOrDecimal() {
        // 可以出现正负号的上下文中如果出现承认并跳过
        char peek = this.peek();
        if (peek == '+' || peek == '-') {
            this.next();
            if (this.eof()) {
                return false;
            } else {
                peek = this.peek();
            }
        }
        // 如果是一个以小数点开头的小数
        if (peek == '.') {
            this.next();
            if (this.eof() || !this.integerPart(false)) {
                return false;
            } else {
                return true;
            }
        }
        // 否则至少有一段不会出现正负号的整数
        if (!integerPart(false)) {
            return false;
        }
        if (this.eof()) {
            return true;
        }
        peek = this.peek();
        if (peek == 'e' || peek == 'E') {
            return true;
        }
        if (peek == '.') {
            // 小数情况
            this.next();
            // 小数点后直接没有数了
            if (this.eof()) {
                return true;
            }
            peek = this.peek();
            if (peek == 'e' || peek == 'E') {
                return true;
            } else if (peek >= '0' && peek <= '9') {
                // 如果还有数，有一串不带正负号的整数
                return integerPart(false);
            } else {
                // 不合法字符
                return false;
            }
        }
        return false;
    }

    private char peek() {
        return this.input.charAt(this.cursor);
    }

    private boolean eof() {
        return this.cursor == this.input.length();
    }

    private char next() {
        return this.input.charAt(this.cursor++);
    }

    public boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }
        this.cursor = 0;
        this.input = s;
        char peek = this.peek();
        // 第一部分，一个整数或小数
        if ((peek >= '0' && peek <= '9') || peek == '.' || peek == '+' || peek == '-') {
            if (!this.integerOrDecimal()) {
                return false;
            }
        } else {
            return false;
        }
        // 如果只有一部分，已经判断完成
        if (this.eof()) {
            return true;
        }
        peek = this.next();
        // 如果还有指数部分，指数部分还有一个整数
        if (peek == 'e' || peek == 'E') {
            if (this.eof() || !this.integerPart(true)) {
                return false;
            }
        } else {
            return false;
        }
        return this.eof();
    }
}
// @lc code=end
