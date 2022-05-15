import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //  private static class TreeNode {
    //          int val;
    //          TreeNode left;
    //          TreeNode right;
    //          TreeNode() {}
    //          TreeNode(int val) { this.val = val; }
    //          TreeNode(int val, TreeNode left, TreeNode right) {
    //              this.val = val;
    //              this.left = left;
    //              this.right = right;
    //          }
    //      }
         private TreeNode _sortedArrayToBST(List<Integer> nums){
             if (nums.isEmpty()) {
                 return null;
             }
             int rootPos = nums.size() / 2, rootVal = nums.get(rootPos);
             TreeNode left = _sortedArrayToBST(nums.subList(0, rootPos)), right = _sortedArrayToBST(nums.subList(rootPos + 1, nums.size()));
             return new TreeNode(rootVal, left, right);
         }
    public TreeNode sortedArrayToBST(int[] nums) {
        List<Integer> ns = new ArrayList<>(nums.length);
        for (int n : nums) {
            ns.add(n);
        }
        return _sortedArrayToBST(ns);
    }
}
// @lc code=end

