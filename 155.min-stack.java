import java.util.Stack;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */

// @lc code=start
class MinStack {

    private static class Node {
        public int val, curMin;

        public Node(int val, int curMin) {
            this.val = val;
            this.curMin = curMin;
        }

    }
    private Stack<Node> minStack;
    public MinStack() {
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        if (minStack.empty() || minStack.peek().curMin > val) {
            minStack.push(new Node(val, val));
        } else {
            minStack.add(new Node(val, minStack.peek().curMin));
        }
    }
    
    public void pop() {
        minStack.pop();
    }
    
    public int top() {
        return minStack.peek().val;
    }
    
    public int getMin() {
        return minStack.peek().curMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// @lc code=end

