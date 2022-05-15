import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * @lc app=leetcode id=150 lang=java
 *
 * [150] Evaluate Reverse Polish Notation
 */

// @lc code=start
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedBlockingDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+":{
                    stack.push(stack.pop() + stack.pop());
                    break;
                }
                case "-":{
                    int right = stack.pop(), left = stack.pop();
                    stack.push(left - right);
                    break;
                }
                case "*":{
                    stack.push(stack.pop() * stack.pop());
                    break;
                }
                case "/": {
                    int divee = stack.pop(), diver = stack.pop();
                    stack.push(diver / divee);
                    break;
                }
                default:{
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.pop();
    }
}
// @lc code=end

