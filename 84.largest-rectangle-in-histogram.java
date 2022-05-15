import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 */

// @lc code=start
class Solution {
    private static class HeightUpperBound {
        public final int height;
        public int lastTime;
        public HeightUpperBound(int height) {
            this.height = height;
            lastTime = 1;
        }
    }
    public int largestRectangleArea(int[] heights) {
        Deque<HeightUpperBound> stack = new ArrayDeque<>();
        int maxSquare = heights[0];
        stack.addLast(new HeightUpperBound(heights[0]));
        for (int i = 1; i < heights.length; ++i) {
            int height = heights[i];
            if (height == stack.peekLast().height) {
                stack.peekLast().lastTime++;
                continue;
            } else if (height > stack.peekLast().height) {
                stack.addLast(new HeightUpperBound(height));
            } else {
                int lastTime = 0;
                while (!stack.isEmpty() && height < stack.peekLast().height) {
                    HeightUpperBound hub = stack.pollLast();
                    lastTime += hub.lastTime;
                    if (lastTime * hub.height > maxSquare) {
                        maxSquare = lastTime*hub.height;
                    }
                }
                HeightUpperBound neoTop = new HeightUpperBound(height);
                neoTop.lastTime = lastTime + 1;
                stack.addLast(neoTop);
            }
        }
        int lastTime = 0;
        while (!stack.isEmpty()) {
            HeightUpperBound hub = stack.pollLast();
            lastTime += hub.lastTime;
            if (lastTime * hub.height > maxSquare) {
                maxSquare = lastTime*hub.height;
            }
        }
        return maxSquare;
    }
    // public int largestRectangleArea(int[] heights) {
    //     // 设想：记录不同高度下限的持续时间
    //     TreeMap<Integer, Integer> heightLowerBound = new TreeMap<>();
    //     int maxSquare = heights[0];
    //     heightLowerBound.put(heights[0], 1);
    //     for (int i = 1; i < heights.length; ++i) {
    //         int nextHeight = heights[i];
    //         int ceil = -1, newLastTime = 1;
    //         for (Iterator<Map.Entry<Integer, Integer>> areaEntry = heightLowerBound.entrySet().iterator(); areaEntry
    //                 .hasNext();) {
    //             Map.Entry<Integer, Integer> area = areaEntry.next();
    //             int h = area.getKey(), lastTime = area.getValue();
    //             if (h < nextHeight) {
    //                 area.setValue(lastTime + 1);
    //             } else if (h == nextHeight) {
    //                 ceil = nextHeight;
    //                 newLastTime = lastTime + 1;
    //             } else {
    //                 if (ceil == -1) {
    //                     ceil = h;
    //                     newLastTime = lastTime + 1;
    //                 }
    //                 if (h * lastTime > maxSquare) {
    //                     maxSquare = h * lastTime;
    //                 }
    //                 areaEntry.remove();
    //             }
    //         }
    //         heightLowerBound.put(nextHeight, newLastTime);
    //     }
    //     for (Map.Entry<Integer, Integer> area : heightLowerBound.entrySet()) {
    //         int square = area.getKey() * area.getValue();
    //         if (square > maxSquare) {
    //             maxSquare = square;
    //         }
    //     }
    //     return maxSquare;
    // }
}
// @lc code=end
