import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 */

// @lc code=start
// class MyStack {
//     // 虽然只用了一个队列，但是时间复杂度为O(n)，而且需要一个标识元素，不过可以通过计数代替
//     private Deque<Integer> queue;
//     private static final Integer BOTTOM = -1;
//     private Integer curTop;

//     public MyStack() {
//         queue = new ArrayDeque<>();
//         curTop = null;
//     }
    
//     public void push(int x) {
//         curTop = x;
//         queue.add(x);
//     }
    
//     public int pop() {
//         queue.add(BOTTOM);
//         Integer cur = null, prev = null;
//         while (true) {
//             prev = cur;
//             cur = queue.poll();
//             if (cur == BOTTOM) {
//                 break;
//             } else if (prev != null) {
//                 queue.add(prev);
//             }
//         }
//         curTop = null;
//         return prev;
//     }
    
//     public int top() {
//         if (curTop != null) {
//             return curTop;
//         }
//         push(pop());
//         return curTop;
//     }
    
//     public boolean empty() {
//         return queue.isEmpty();
//     }
// }

class MyStack {
    // 虽然只用了一个队列，但是时间复杂度为O(n)，而且需要一个标识元素，不过可以通过计数代替
    private Deque<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }
    
    public void push(int x) {
        int curSize = queue.size();
        queue.addLast(x);
        for (int i = 0; i < curSize; ++i) {
            int tmp = queue.pollFirst();
            queue.addLast(tmp);
        }
    }
    
    public int pop() {
        return queue.pollFirst();
    }
    
    public int top() {
        return queue.getFirst();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

// class MyStack {
//     private Deque<Integer> queue1, queue2;

//     public MyStack() {
//         queue1 = new ArrayDeque<>();
//         queue2 = new ArrayDeque<>();
//     }
    
//     public void push(int x) {
//         queue1.addLast(x);
//         while (!queue2.isEmpty()) {
//             queue1.addLast(queue2.pollFirst());
//         }
//         Deque<Integer> tmp = queue1;
//         queue1 = queue2;
//         queue2 = tmp;
//     }
    
//     public int pop() {
//         return queue2.pollFirst();
//     }
    
//     public int top() {
//         return queue2.getFirst();
//     }
    
//     public boolean empty() {
//         return queue2.isEmpty();
//     }
// }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
// @lc code=end

