import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=968 lang=java
 *
 * [968] Binary Tree Cameras
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // private static class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;

    //     TreeNode() {
    //     }

    //     TreeNode(int val) {
    //         this.val = val;
    //     }

    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }

    // private static class SubTreeMap {
    //     public final TreeNode root;
    //     public final boolean parentCam, hasCam;

    //     public SubTreeMap(TreeNode root, boolean parentCam, boolean hasCam) {
    //         this.root = root;
    //         this.parentCam = parentCam;
    //         this.hasCam = hasCam;
    //     }

    //     @Override
    //     public int hashCode() {
    //         final int prime = 31;
    //         int result = 1;
    //         result = prime * result + (hasCam ? 1231 : 1237);
    //         result = prime * result + (parentCam ? 1231 : 1237);
    //         result = prime * result + ((root == null) ? 0 : root.hashCode());
    //         return result;
    //     }

    //     @Override
    //     public boolean equals(Object obj) {
    //         if (this == obj)
    //             return true;
    //         if (obj == null)
    //             return false;
    //         if (getClass() != obj.getClass())
    //             return false;
    //         SubTreeMap other = (SubTreeMap) obj;
    //         if (hasCam != other.hasCam)
    //             return false;
    //         if (parentCam != other.parentCam)
    //             return false;
    //         if (root == null) {
    //             if (other.root != null)
    //                 return false;
    //         } else if (root != (other.root))
    //             return false;
    //         return true;
    //     }

    // }

    // private static final int MAX_CAMS = 1001;

    // private static int _minCameraCover(TreeNode root, boolean parentCam, boolean hasCam,
    //         Map<SubTreeMap, Integer> memo) {
    //     if (root == null) {
    //         return 0;
    //     }
    //     if (root.left == null && root.right == null) {
    //         if (parentCam) {
    //             return 0;
    //         } else if (hasCam) {
    //             return 1;
    //         } else {
    //             return MAX_CAMS;
    //         }
    //     }
    //     SubTreeMap key = new SubTreeMap(root, parentCam, hasCam);
    //     Integer res = memo.get(key);
    //     if (res != null) {
    //         return res;
    //     }
    //     if (hasCam) {
    //         int left = Math.min(_minCameraCover(root.left, true, false, memo),
    //                 _minCameraCover(root.left, true, true, memo));
    //         int right = Math.min(_minCameraCover(root.right, true, false, memo),
    //                 _minCameraCover(root.right, true, true, memo));
    //         res = left + right + 1;
    //     } else if (parentCam) {
    //         int left = Math.min(_minCameraCover(root.left, false, false, memo),
    //                 _minCameraCover(root.left, false, true, memo));
    //         int right = Math.min(_minCameraCover(root.right, false, false, memo),
    //                 _minCameraCover(root.right, false, true, memo));
    //         res = left + right;

    //     } else if (root.left != null && root.right != null){
    //         res = Math.min(Math.min(
    //                 _minCameraCover(root.left, false, true, memo) + _minCameraCover(root.right, false, false, memo),
    //                 _minCameraCover(root.left, false, false, memo) + _minCameraCover(root.right, false, true, memo)),
    //                 _minCameraCover(root.left, false, true, memo) + _minCameraCover(root.right, false, true, memo));
    //     } else if (root.left == null) {
    //         res = _minCameraCover(root.right, false, true, memo);
    //     } else {
    //         res = _minCameraCover(root.left, false, true, memo);
    //     }
    //     memo.put(key, res);
    //     return res;
    // }

    // public int minCameraCover(TreeNode root) {
    //     Map<SubTreeMap, Integer> memo = new HashMap<>();
    //     return Math.min(_minCameraCover(root, false, true, memo), _minCameraCover(root, false, false, memo));
    // }
    private static enum NodeType {
        // 如果把Cam放在叶节点，只能照亮叶节点本身和父节点
        // 如果把Cam放在叶节点的父节点，可以照亮叶节点的父节点、兄弟节点和祖父节点
        // 显然把Cam放在叶节点的父节点可以照亮的范围更大
        // 要想使用最少的Cam照亮整棵树，需要选最大的范围和最小的overlap
        // 优先选择树中的叶节点的父节点，放置Cam，并将Cam照亮的节点从树中删除
        // 不断重复直到树中所有节点都被删除
        LEAF, // 虚拟叶节点
        LEAF_PARENT, // 虚拟叶节点的父节点 
        OTHER // 不需要被父节点的Cam照到的节点，null或是已经被其子节点Cam照到的节点

    }
    public int minCameraCover(TreeNode root) {
        // 存储已经使用了几个Cam
        int[] camUsed = {0};
        NodeType rootType = _minCameraCover(root, camUsed);
        // 如果最后根节点是一个叶节点，没有别的节点可以照亮它，为他设置一个Cam
        // 如果最后根节点是叶节点的父节点，那在调用方法时已经为其分配Cam了，不需要多加
        // 如果根节点已经被照亮，不需要添加Cam
        return rootType == NodeType.LEAF ? 1 + camUsed[0] : camUsed[0];
    }
    private static NodeType _minCameraCover(TreeNode root, int camUsed[]) {
        if (root == null) {
            return NodeType.OTHER;
        }
        NodeType leftChildType = _minCameraCover(root.left, camUsed), rightChildType = _minCameraCover(root.right, camUsed);
        if (leftChildType == NodeType.LEAF || rightChildType == NodeType.LEAF) {
            // 当一个节点有子节点是未被照亮的叶节点时，在该节点处分配一个Cam
            // 这样它就可以照到，其未被照亮的叶节点和其父节点
            ++camUsed[0];
            return NodeType.LEAF_PARENT;
        }
        if (leftChildType == NodeType.LEAF_PARENT || rightChildType == NodeType.LEAF_PARENT) {
            // 当节点的子节点没有未被照亮的叶节点时
            // 如果其中有子节点是被放置了Cam的节点，本节点已被照亮，且不需要照亮子节点，因为这时在本节点放Cam只能额外照亮一个父节点，overlap大
            // 返回本节点已被照亮
            return NodeType.OTHER;
        }
        // 只有在节点的两个子节点都已经被照亮或是为null时，这个节点是虚拟叶节点
        return NodeType.LEAF;
    }
}
// @lc code=end
