import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=241 lang=java
 *
 * [241] Different Ways to Add Parentheses
 */

// @lc code=start
class Solution {
    public static void processStr(String expr, List<Integer> vals, List<Character> ops, List<String> valStrs) {
        String[] ss = expr.split("\\D");
        for (String vs : ss) {
            if (vs.isEmpty()) {
                continue;
            }
            vals.add(Integer.parseInt(vs));
            valStrs.add(vs);
        }
        ss = expr.split("\\d+");
        for (String os : ss) {
            if (os.isEmpty()) {
                continue;
            }
            ops.add(os.charAt(0));
        }
    }
    public static int doBiOp(int left, int right, char op) {
        int res = 0;
        //  由于没有除法,应该不会出现不合法的操作
        switch(op) {
            case '+':{
                res = left + right;
                break;
            }
            case '-':{
                res = left - right;
                break;
            }
            case '*' : {
                res = left * right;
                break;
            }
        }
        return res;
    }
    public static void computeRec(List<Integer> vals, List<Character> ops, List<Integer> res, List<String> valStrs, Set<String> usedExpr) {
        if (vals.size() == 1) {
            if (usedExpr.add(valStrs.get(0))) {
                res.add(vals.get(0));
            }
            return;
        }
        for (int i = 0; i < vals.size() - 1; ++i) {
            Integer left = vals.remove(i);
            Integer right = vals.remove(i);
            String ls = valStrs.remove(i);
            String rs = valStrs.remove(i);
            Character op = ops.remove(i);
            String bs = "(" + ls + op + rs + ")";
            Integer br = doBiOp(left, right, op);
            vals.add(i, br);
            valStrs.add(i, bs);
            computeRec(vals, ops, res, valStrs, usedExpr);
            vals.remove(i);
            valStrs.remove(i);
            vals.add(i, right);
            vals.add(i, left);
            valStrs.add(i, rs);
            valStrs.add(i, ls);
            ops.add(i, op);
        }
    }
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>(), vals = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        List<String> valStrs = new ArrayList<>();
        // 为了去重,不得不记录枚举的结果
        Set<String> usedExpr = new HashSet<>();
        processStr(expression, vals, ops, valStrs);
        computeRec(vals, ops, res, valStrs, usedExpr);
        return res;
    }
}
// @lc code=end

