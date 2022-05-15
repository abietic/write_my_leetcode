import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=133 lang=java
 *
 * [133] Clone Graph
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // private static class Node {
    //     public int val;
    //     public List<Node> neighbors;
    //     public Node() {
    //         val = 0;
    //         neighbors = new ArrayList<Node>();
    //     }
    //     public Node(int _val) {
    //         val = _val;
    //         neighbors = new ArrayList<Node>();
    //     }
    //     public Node(int _val, ArrayList<Node> _neighbors) {
    //         val = _val;
    //         neighbors = _neighbors;
    //     }
    // }
    // private static Node[] nodes;
    // private Node _cloneNode(Node node) {
    //     int index = node.val;
    //     Node thisNode;
    //     if (nodes[index] == null) {
    //         thisNode = new Node(index);
    //         nodes[index] = thisNode;
    //         for (Node adj : node.neighbors) {
    //             thisNode.neighbors.add(_cloneNode(adj));
    //         }
    //     } else {
    //         thisNode = nodes[index];
    //     }
    //     return thisNode;
    // }
    // public Node cloneGraph(Node node) {
    //     if (node == null) {
    //         return null;
    //     }
    //     nodes = new Node[101];
    //     return _cloneNode(node);
    // }
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node[] nodes = new Node[101];
        boolean[] visited = new boolean[101];
        Queue<Node> needVisit = new ArrayDeque<>();
        needVisit.add(node);
        Node head = null;
        while (!needVisit.isEmpty()) {
            Node cur = needVisit.poll();
            int index = cur.val;
            if (visited[index]) {
                continue;
            }
            Node thisNode;
            if (nodes[index] == null) {
                thisNode = new Node(index);
                nodes[index] = thisNode;
                if (head == null) {
                    head = thisNode;
                }
            } else {
                thisNode = nodes[index];
            }
            for (Node adj : cur.neighbors) {
                needVisit.add(adj);
                int adjIndex = adj.val;
                if (nodes[adjIndex] == null) {
                    nodes[adjIndex] = new Node(adjIndex);
                }
                thisNode.neighbors.add(nodes[adjIndex]);
            }
            visited[index] = true;
        }
        return head;
    }
}
// @lc code=end

