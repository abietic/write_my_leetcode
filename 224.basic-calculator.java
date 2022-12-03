/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 */

// @lc code=start
class Solution {
    // 题目前提是算式合法且没有两个连续的操作符
    // 长度比较大不知道用递归下降会不会爆栈
    private static class Token {

        public static final int NUM = 0;
        public static final int PLUS = 1;
        public static final int MINUS = 2;
        public static final int LEFT_PAR = 3;
        public static final int RIGHT_PAR = 4;
        public int type;
        public long val;
    }

    private static int cursor;
    private static Token cur, nxt;
    private static String s;

    private static boolean hasNextToken() {
        while (cursor < s.length() && s.charAt(cursor) == ' ') {
            cursor++;
        }
        if (cursor < s.length()) {
            return true;
        }
        return false;
    }

    private static Token nextToken() {
        // 跳过空格
        if (!hasNextToken()) {
            return null;
        }
        Token res = new Token();
        if (Character.isDigit(s.charAt(cursor))) {
            res.type = Token.NUM;
            int val = Character.digit(s.charAt(cursor++), 10);
            while (cursor < s.length() && Character.isDigit(s.charAt(cursor))) {
                val = val * 10 + Character.digit(s.charAt(cursor++), 10);
            }
            res.val = val;
        } else {
            switch (s.charAt(cursor++)) {
                case '(': {
                    res.type = Token.LEFT_PAR;
                    break;
                }
                case ')': {
                    res.type = Token.RIGHT_PAR;
                    break;
                }
                case '+': {
                    res.type = Token.PLUS;
                    break;
                }
                case '-': {
                    res.type = Token.MINUS;
                    break;
                }
                default:
                    break;
            }
        }
        return res;
    }

    private static Long expr() {
        // expr -> num
        // expr -> unaryExpr
        // expr -> parathesesExpr
        // expr -> expr [+ | -] expr 但是由于递归下降是ll文法，ll需要消除左递归，因此不能直接用，又由于计算是左结合的还要额外做处理
        // 使用消除直接左递归的方法处理处理上述推导式
        // expr -> [num | unaryExpr | parathesesExpr] binaryExprRight
        // binaryExprRight -> [{[+ | -][num | parathesesExpr] binaryExprRight} | 空]
        // 由于题目前提保证不会出现连续的操作符，所以只有立即数和括号表达式两种可能
        if (cur == null) {
            cur = nextToken();
        }
        long val = 0;
        switch (cur.type) {
            case Token.NUM: {
                val = cur.val;
                break;
            }
            case Token.LEFT_PAR: {
                val = parathesesExpr();
                break;
            }
            case Token.MINUS: {
                val = unaryExpr();
                break;
            }
            default:
                break;
        }
        if (!hasNextToken() || (cur = nextToken()).type == Token.RIGHT_PAR) {
            return val;
        }
        val = binaryExprRight(val);
        return val;
    }

    private static Long binaryExprRight(long leftExprVal) {
        if (cur == null || (cur.type != Token.MINUS && cur.type != Token.PLUS)) {
            // 不应该到这里
            return null;
        }
        Token op = cur;
        cur = nextToken();
        long val = 0, rightExprVal = 0;
        switch (op.type) {
            case Token.PLUS: {
                switch (cur.type) {
                    case Token.NUM: {
                        rightExprVal = cur.val;
                        break;
                    }
                    case Token.LEFT_PAR: {
                        rightExprVal = parathesesExpr();
                        break;
                    }
                    default:
                        break;
                }
                val = leftExprVal + rightExprVal;
                break;
            }
            case Token.MINUS: {
                switch (cur.type) {
                    case Token.NUM: {
                        rightExprVal = cur.val;
                        break;
                    }
                    case Token.LEFT_PAR: {
                        rightExprVal = parathesesExpr();
                        break;
                    }
                    default:
                        break;
                }
                val = leftExprVal - rightExprVal;
                break;
            }
            default:
                break;
        }
        if (!hasNextToken() || ((cur = nextToken()).type != Token.PLUS && cur.type != Token.MINUS)) {
            return val;
        }
        val = binaryExprRight(val);
        return val;
    }

    private static Long unaryExpr() {
        // unaryExpr -> -num
        // unaryExpr -> -parathesesExpr
        if (cur == null || cur.type != Token.MINUS || !hasNextToken()) {
            // 不应该到这里
            return null;
        }
        long val = 0;
        cur = nextToken();
        switch (cur.type) {
            case Token.NUM: {
                val = cur.val;
                break;
            }
            case Token.LEFT_PAR: {
                val = parathesesExpr();
                break;
            }
        }
        cur = null;
        return -val;
    }

    private static Long parathesesExpr() {
        // parathesesExpr -> (expr)
        if (cur == null || cur.type != Token.LEFT_PAR || !hasNextToken()) {
            // 不应该到这里
            return null;
        }
        cur = null;
        long val = expr();
        if (cur == null || cur.type != Token.RIGHT_PAR) {
            // 不应该到这里
            return null;
        }
        // 清除右括号
        cur = null;
        return val;
    }

    public int calculate(String s) {
        cur = nxt = null;
        this.s = s;
        cursor = 0;
        return expr().intValue();
    }
}
// @lc code=end
