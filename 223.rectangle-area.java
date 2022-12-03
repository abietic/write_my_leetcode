/*
 * @lc app=leetcode id=223 lang=java
 *
 * [223] Rectangle Area
 */

// @lc code=start
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // (ax1, ay1) -> a的左下角 (ax2, ay2) -> a的右上角
        // (bx1, by1) -> b的左下角 (bx2, by2) -> b的右上角
        final int aLeft = ax1, aRight = ax2, aTop = ay2, aBot = ay1;
        final int bLeft = bx1, bRight = bx2, bTop = by2, bBot = by1;
        // 要想有重合面积至少上下左右都有overlap
        // 计算overlap长度相乘即可
        int[][] aIntervals = {{aLeft, aRight},{aBot, aTop}};
        int[][] bIntervals = {{bLeft, bRight},{bBot, bTop}};
        int[] overlaps = {0, 0};
        for (int i = 0; i < aIntervals.length; ++i) {
            int[] aInterval = aIntervals[i];
            int[] bInterval = bIntervals[i];
            // 以一个区间为基准,计算重叠长度,这里以A为基准
            final int intAL = aInterval[0], intAR = aInterval[1];
            final int intBL = bInterval[0], intBR = bInterval[1];
            if (intBR <= intAL || intBL >= intAR) {
                // 没有重叠长度 
                overlaps[i] = 0;
            } else if (intAL <= intBL && intAR >= intBR){
                // A包含B
                overlaps[i] = intBR - intBL;
            } else if (intBL <= intAL && intBR >= intAR) {
                // B包含A
                overlaps[i] = intAR - intAL;
            } else {
                // A与B是重叠关系,但是不是包含关系
                if (intAL < intBL && intBL < intAR) {
                    // A在左侧
                    overlaps[i] = intAR - intBL;
                } else if (intBL < intAL && intAL < intBR) {
                    // B在左侧
                    overlaps[i] = intBR - intAL;
                }
            }
        }
        return (aRight - aLeft) * (aTop - aBot) + (bRight - bLeft) * (bTop - bBot) - overlaps[0] * overlaps[1];
    }
}
// @lc code=end

