import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */

// @lc code=start
class Solution {
    // public List<List<Integer>> getSkyline(int[][] buildings) {
    // List<List<Integer>> res = new ArrayList<>();
    // // 使用类似扫描线算法的想法?
    // Arrays.sort(buildings, (b1, b2) -> {
    // // 左边界在前的排在前面
    // if (b1[0] == b2[0]) {
    // // 否则有边界在前的排在前面
    // if (b1[1] == b2[1]) {
    // // 否则高的排在前面
    // return -(b1[2] - b2[2]);
    // } else {
    // return b1[1] - b2[1];
    // }
    // }
    // return b1[0] - b2[0];
    // });
    // // 当有block进入的时候可能会更新高度,如果新加入的block高度大于当前最大高度那么要更新,并记录,否则只记录不更新
    // // 当有block退出时,可能更新高度,如果退出的block会使高度更新
    // TreeMap<Integer, Integer> heightMemo = new TreeMap<>();
    // heightMemo.put(0, 1); // 起始时添加一个地面
    // PriorityQueue<int[]> firstEnd = new PriorityQueue<>((b1, b2) -> b1[1] -
    // b2[1]);
    // int lastRight = 0, lastHeight = 0;
    // for (int i = 0; i < buildings.length; ++i) {
    // int[] block = buildings[i];
    // List<int[]> putBatch = new ArrayList<>();
    // putBatch.add(block);
    // for (; i + 1 < buildings.length && buildings[i + 1][0] == block[0]; ++i) {
    // block = buildings[i + 1];
    // putBatch.add(block);
    // }
    // boolean updateOnCommonEdge = false;
    // // 计算block退出时的更新情况
    // while (!firstEnd.isEmpty() && firstEnd.peek()[1] <= block[0]) {
    // int[] needPop = firstEnd.poll();
    // List<int[]> popBatch = new ArrayList<>();
    // popBatch.add(needPop);
    // while (!firstEnd.isEmpty() && firstEnd.peek()[1] == needPop[1]) {
    // needPop = firstEnd.poll();
    // popBatch.add(needPop);
    // }
    // for (int[] proc : popBatch) {
    // // 统一更新高度
    // int lastVal = 0;
    // if ((lastVal = heightMemo.get(proc[2]).intValue()) == 1) {
    // heightMemo.remove(proc[2]);
    // } else {
    // heightMemo.put(proc[2], lastVal - 1);
    // }
    // }
    // if (needPop[1] < block[0]) {
    // // 判断是否要输出这个高度更新
    // int curHeight = heightMemo.lastKey();
    // if (lastHeight > curHeight) {
    // lastHeight = curHeight;
    // List<Integer> point = Arrays.asList(Integer.valueOf(needPop[1]),
    // Integer.valueOf(curHeight));
    // res.add(point);
    // }
    // } else {
    // updateOnCommonEdge = true;
    // }
    // }
    // for (int[] b : putBatch) {
    // firstEnd.add(b);
    // heightMemo.compute(b[2], (height, cnt) -> cnt == null ? 1 : cnt + 1);
    // }
    // int curHeight = heightMemo.lastKey();
    // //
    // 这一步更新判断很重要,当在这个位置同时有进入和退出出现的情况需要把进入和退出全做完后再判断,而且全做完后高度不止会升高还可能会降低,不像只涉及插入时,只要考虑变大的情况
    // if (curHeight > lastHeight || (updateOnCommonEdge && curHeight < lastHeight))
    // {
    // lastHeight = curHeight;
    // List<Integer> point = Arrays.asList(Integer.valueOf(block[0]),
    // Integer.valueOf(curHeight));
    // res.add(point);
    // }
    // }
    // // 计算block退出时的更新情况
    // // 排空blocks
    // while (!firstEnd.isEmpty()) {
    // int[] needPop = firstEnd.poll();
    // List<int[]> popBatch = new ArrayList<>();
    // popBatch.add(needPop);
    // while (!firstEnd.isEmpty() && firstEnd.peek()[1] == needPop[1]) {
    // needPop = firstEnd.poll();
    // popBatch.add(needPop);
    // }
    // for (int[] proc : popBatch) {
    // // 统一更新高度
    // int lastVal = 0;
    // if ((lastVal = heightMemo.get(proc[2]).intValue()) == 1) {
    // heightMemo.remove(proc[2]);
    // } else {
    // heightMemo.put(proc[2], lastVal - 1);
    // }
    // }
    // // 判断是否要输出这个高度更新
    // int curHeight = heightMemo.lastKey();
    // if (lastHeight > curHeight) {
    // lastHeight = curHeight;
    // List<Integer> point = Arrays.asList(Integer.valueOf(needPop[1]),
    // Integer.valueOf(curHeight));
    // res.add(point);
    // }
    // }
    // return res;
    // }

    // 也有方法可以简化上面的操作,就是将所有的进入和退出发生位置都放到一个堆里,然后标识出这些位置哪些是进入位置哪些是退出位置,高度都是多少,就可以简化代码了
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> actionQueue = new PriorityQueue<>((a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a1[1] - a2[1];
            }
            return a1[0] - a2[0];
        });
        final int ACTION_ENTER = 1, ACTION_LEAVE = -1;
        for (int[] block : buildings) {
            actionQueue.add(new int[] { block[0], ACTION_ENTER, block[2] });
            actionQueue.add(new int[] { block[1], ACTION_LEAVE, block[2] });
        }
        TreeMap<Integer, Integer> heightRec = new TreeMap<>();
        int lastHeight = 0;
        while (!actionQueue.isEmpty()) {
            int progress = actionQueue.peek()[0];
            while (!actionQueue.isEmpty() && progress == actionQueue.peek()[0]) {
                int[] action = actionQueue.poll();
                if (action[1] == ACTION_ENTER) {
                    heightRec.compute(action[2], (h, c) -> c == null ? 1 : c + 1);
                } else {
                    Integer cnt = heightRec.get(action[2]);
                    if (cnt == 1) {
                        heightRec.remove(action[2]);
                    } else {
                        heightRec.put(action[2], cnt - 1);
                    }
                }
            }
            Integer curHeight = heightRec.isEmpty() ? 0 : heightRec.lastKey();
            if (lastHeight != curHeight.intValue()) {
                res.add(Arrays.asList(progress, curHeight));
                lastHeight = curHeight;
            }
        }
        return res;
    }
}
// @lc code=end
