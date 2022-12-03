import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=227 lang=java
 *
 * [227] Basic Calculator II
 */

// @lc code=start
class Solution {
    // public int calculate(String s) {
    //     // 存储操作数和中间值
    //     Deque<Integer> valStack = new ArrayDeque<>();
    //     // 存储操作符
    //     Deque<Character> opStack = new ArrayDeque<>();
    //     // 发生操作时的寄存器
    //     int vall, valr, res;
    //     // 遍历表达式字符串上的每个字符
    //     for (int cursor = 0; cursor < s.length();) {
    //         // 跳过所有空白字符
    //         while (cursor < s.length() && s.charAt(cursor) == ' ') {
    //             cursor++;
    //         }
    //         if (cursor >= s.length()) {
    //             break;
    //         }
    //         // 简易的tokenizer
    //         // 检查有效字符代表的是什么token
    //         switch (s.charAt(cursor)) {
    //             case '0':
    //             case '1':
    //             case '2':
    //             case '3':
    //             case '4':
    //             case '5':
    //             case '6':
    //             case '7':
    //             case '8':
    //             case '9': {
    //                 int val = Character.digit(s.charAt(cursor++), 10);
    //                 while (cursor < s.length() && Character.isDigit(s.charAt(cursor))) {
    //                     val = val * 10 + Character.digit(s.charAt(cursor++), 10);
    //                 }
    //                 valStack.addFirst(val);
    //                 break;
    //             }
    //             case '+':
    //             case '-': {
    //                 // 当它拥有上一个操作时，上一个操作可以继续了
    //                 // 加减操作优先级低，算式左结合，之前确定可以先算的开始计算
    //                 while (!opStack.isEmpty()) {

    //                     valr = valStack.removeFirst();
    //                     vall = valStack.removeFirst();
    //                     res = 0;
    //                     switch (opStack.removeFirst()) {
    //                         case '+': {
    //                             res = vall + valr;
    //                             break;
    //                         }
    //                         case '-': {
    //                             res = vall - valr;
    //                             break;
    //                         }
    //                         case '*': {
    //                             res = vall * valr;
    //                             break;
    //                         }
    //                         case '/': {
    //                             res = vall / valr;
    //                             break;
    //                         }
    //                         default:
    //                             break;
    //                     }
    //                     valStack.addFirst(res);
    //                 }
    //                 opStack.addFirst(s.charAt(cursor++));
    //                 break;
    //             }
    //             case '*':
    //             case '/': {
    //                 while (!opStack.isEmpty() && (opStack.peekFirst()== '*' || opStack.peekFirst()== '/')) {
    //                     valr = valStack.removeFirst();
    //                     vall = valStack.removeFirst();
    //                     res = 0;
    //                     switch (opStack.removeFirst()) {
    //                         case '*': {
    //                             res = vall * valr;
    //                             break;
    //                         }
    //                         case '/': {
    //                             res = vall / valr;
    //                             break;
    //                         }
    //                         default:
    //                             break;
    //                     }
    //                     valStack.addFirst(res);
    //                 }
    //                 opStack.addFirst(s.charAt(cursor++));
    //                 break;
    //             }
    //             default:
    //                 break;
    //         }
    //     }
    //     while (!opStack.isEmpty()) {
    //         valr = valStack.removeFirst();
    //         vall = valStack.removeFirst();
    //         res = 0;
    //         switch (opStack.removeFirst()) {
    //             case '+': {
    //                 res = vall + valr;
    //                 break;
    //             }
    //             case '-': {
    //                 res = vall - valr;
    //                 break;
    //             }
    //             case '*': {
    //                 res = vall * valr;
    //                 break;
    //             }
    //             case '/': {
    //                 res = vall / valr;
    //                 break;
    //             }
    //             default:
    //                 break;
    //         }
    //         valStack.addFirst(res);
    //     }
    //     return valStack.removeFirst();
    // }

    private static final long OP_PLUS = 1, OP_MINUS = 2, OP_MUL = 3, OP_DIV = 4;

    public static long simpleCalculator(String expr) {
        long res = 0;
        Deque<Long> valStack = new ArrayDeque<>(), opStack = new ArrayDeque<>();
        int cursor = 0;
        final int len = expr.length();
        while (cursor < len) {
            // 跳过多余空格
            while (cursor < len && expr.charAt(cursor) == ' ') {
                ++cursor;
            }
            if (cursor >= len) {
                break;
            }
            char c = expr.charAt(cursor);
            if (c >= '0' && c <= '9') {
                // 处理一个数值串
                long cur = c - '0';
                while (cursor + 1 < len && (c = expr.charAt(cursor + 1)) >= '0' && c <= '9') {
                    cur = cur * 10 + (c - '0');
                    cursor++;
                }
                // 与操作占栈顶元素相关
                // TODO
                valStack.addLast(cur);
            } else {
                // 处理一个操作符
                long op = 0;
                switch (c) {
                    case '+':
                        op = OP_PLUS;
                        break;
                    case '-':
                        op = OP_MINUS;
                        break;
                    case '*':
                        op = OP_MUL;
                        break;
                    case '/':
                        op = OP_DIV;
                        break;
                }
                if (!opStack.isEmpty() && opStack.peekLast() > OP_MINUS) {
                    long right = valStack.pollLast(), left = valStack.pollLast(), doOp = opStack.pollLast();
                    long r = (doOp == OP_MUL) ? (left * right) : (left / right);
                    valStack.addLast(r);
                }
                if (op <= OP_MINUS && !opStack.isEmpty()) {
                    long right = valStack.pollLast(), left = valStack.pollLast(), doOp = opStack.pollLast();
                    long r = (doOp == OP_PLUS) ? (left + right) : (left - right);
                    valStack.addLast(r);
                }
                opStack.addLast(op);
            }
            cursor++;
        }
        while (!opStack.isEmpty()) {
            long right = valStack.pollLast(), left = valStack.pollLast(), doOp = opStack.pollLast();
            long r = 0;
            if (doOp <= OP_MINUS) {
                r = (doOp == OP_PLUS) ? (left + right) : (left - right);
            } else {
                r = (doOp == OP_MUL) ? (left * right) : (left / right);
            }
            valStack.addLast(r);
        }
        res = valStack.pollLast();
        return res;
    }
    public int calculate(String s) {
        return (int)simpleCalculator(s);
    }
}
// @lc code=end
