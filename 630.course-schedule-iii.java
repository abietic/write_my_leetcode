import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;
/*
 * @lc app=leetcode id=630 lang=java
 *
 * [630] Course Schedule III
 */
import java.util.Map.Entry;

// @lc code=start
class Solution {
    // private static void merge(int[] mergeSet, int root, int leaf) {
    // mergeSet[leaf] = root;
    // }

    // private static int root(int[] mergeSet, int leaf) {
    // while (mergeSet[leaf] != leaf) {
    // int newLeaf = mergeSet[leaf];
    // mergeSet[leaf] = mergeSet[newLeaf];
    // leaf = newLeaf;
    // }
    // return leaf;
    // }

    // private static int[] mergeSet(int elementCount) {
    // int[] res = new int[elementCount];
    // for (int i = 0; i < elementCount; ++i) {
    // res[i] = i;
    // }
    // return res;
    // }

    // // 超时了
    // public int scheduleCourse(int[][] courses) {
    //     // 单机多任务处理的调度问题，不可抢占？
    //     // 截止期由后到前排列课程
    //     Arrays.sort(courses, (a, b) -> {
    //         return b[1] - a[1];
    //     });
    //     // 从第x天开始上课最多还能上几门课
    //     TreeMap<Integer, Integer> memo = new TreeMap<>(), tmp = new TreeMap<>();
    //     // 最晚截止的课也截止了，没有课可以上了
    //     memo.put(courses[0][1] + 1, 0);
    //     for (int[] course : courses) {
    //         // 对于课程的选择有两种，上或不上
    //         // 要将那些占用天数多，完成课程数还少的中间结果去掉
    //         // 假设当前中间结果集中没有这种中间结果
    //         // 当加入一个新的中间结果时
    //         int afterThisClass = course[1] + 1;
    //         var headMap = memo.headMap(afterThisClass);
    //         for (var entry : headMap.entrySet()) {
    //             // 新生成的中间结果之间不会产生上述冲突
    //             tmp.put(entry.getKey() - course[0], entry.getValue() + 1);
    //         }
    //         tmp.put(afterThisClass - course[0], memo.ceilingEntry(afterThisClass).getValue() + 1);
    //         // 要检查新结果和旧结果集之间有没有冲突
    //         for (var entry : tmp.entrySet()) {
    //             if (entry.getKey() <= 0) {
    //                 continue;
    //             }
    //             var iter = memo.entrySet().iterator();
    //             boolean legal = true;
    //             while (iter.hasNext()) {
    //                 var memoEntry = iter.next();
    //                 // if (memoEntry.getKey() > entry.getKey()) {
    //                 //     break;
    //                 // }
    //                 // if (memoEntry.getValue() <= entry.getValue()) {
    //                 //     // 删除不满足阶梯上升的中间结果
    //                 //     iter.remove();
    //                 // }
    //                 if (memoEntry.getKey() <= entry.getKey() && memoEntry.getValue() < entry.getValue()) {
    //                     iter.remove();
    //                 }
    //                 if (memoEntry.getKey() >= entry.getKey() && memoEntry.getValue() >= entry.getValue()) {
    //                     legal = false;
    //                     break;
    //                 }
    //             }
    //             if (legal) {
    //                 memo.put(entry.getKey(), entry.getValue());
    //             }
    //             // memo.put(entry.getKey(), Math.max(entry.getValue(), memo.getOrDefault(entry.getKey(), 0)));
    //         }
    //         tmp.clear();
    //     }
    //     // 从第一天上课最多还能上几门课
    //     return memo.ceilingEntry(1).getValue();
    // }
    public int scheduleCourse(int[][] courses) {
        // 最大堆用来存放那些已经被选择的课程，并且这些课程都能在截止期前完成
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2)->{return e2 - e1;});
        // 将课程按照它们的截止期排序
        Arrays.sort(courses, (e1, e2)->{return e1[1] - e2[1];});
        // 选择的课程已经消耗了多少天
        int totalDays = 0;
        for (int[] course : courses) {
            // 在已经选择的课程都满足截止期的前提下
            // 加入一个新的课程
            // 如果累计消耗天数仍在该课程的截止期范围内，课程可以完成，选择上这个课程
            totalDays += course[0];
            maxHeap.add(course[0]);
            if (totalDays > course[1]) {
                // 如果新加入的课程无法满足截止期
                // 从已经选择的课程中选择最大的上课用时的课程
                // 这时选出的课程有两种情况
                // 1.就是新加入的不满足截止期的课程
                //   这时直接抛弃课程
                // 2.不是新加入的那个课程
                //   那么这个课程的耗时要大于等于新加入课程，且截止期小于等于新加入课程
                //   因此这时将这个课程抛弃，将新加入的课程放入其占有的时间段一定可以满足要求
                //   并且这时总选择课程数不变的情况下，累计消耗天数至少保证不增加，甚至可能减少
                totalDays -= maxHeap.poll();
            }
        }
        return maxHeap.size(); 
    }
}
// @lc code=end
