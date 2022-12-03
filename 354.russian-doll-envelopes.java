import java.util.Arrays;

/*
 * @lc app=leetcode id=354 lang=java
 *
 * [354] Russian Doll Envelopes
 */

// @lc code=start
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // 对信封以宽度由小到大排序,如果宽度相同则以长度由小到大排序
        Arrays.sort(envelopes, (env1, env2) -> {
            if (env1[0] == env2[0]) {
                return env2[1] - env1[1]; // 当宽度相等时,将高度大的放在前面, 这个顺序很重要,直接影响到接下来dp记忆操作的简化
            } else {
                return env1[0] - env2[0];
            }
        });
        // F(env[0:N]) = max{F(env[0:N-1]), 包含env[N]的最大嵌套长度}
        // 当宽度的大小关系已经确立之后,接下来需要判断长度来确定嵌套关系
        // 已知前一封信的宽度一定大于等于之前已经选入的信封
        int[] embedArray = new int[envelopes.length]; // 保存插入的信封的宽度,其有效数组长度代表了信封嵌套的最大深度
        // 正常想要保存之前的所有可能嵌套信息需要在对应的插入信封记录当前信封的嵌套深度(计算方式为插入时刻前一个信封的嵌套深度)
        // 但是这样每次插入就是nlogn时间复杂度太大而且等宽情况不好处理
        // 考虑到插入到内部的信封在超出原有长度之前都是无法更新最优解的,而且新加入的信封在宽度上一定大于等于之前插入的信封
        // 所以只要高度上满足条件就可以开出一个新的嵌套分支直到超出原有嵌套深度,因此可以直接对原有位置的信封相应高度进行替换(使高度变小或不变)
        // 这样做不会影响之后的插入的前提是不能提前把有效信息覆盖掉和不能等宽嵌套,这时就要让等宽的信封高度降序排列
        int depth = 0; // 信封嵌套的深度
        for (int i = 0; i < envelopes.length; ++i) {
            int index = Arrays.binarySearch(embedArray, 0, depth, envelopes[i][1]);
            if (index < 0) {
                // 如果在指定范围内没有找到相等的值
                // binarySearch返回的是应插入位置 (-(insertion point) - 1)
                index = -index - 1;
            }
            if (index == depth) {
                // 如果没有在已选入的信封中找到相同高度,且要插入的信封高度比所有已选入的信封高度要大
                // 可以选择这个信封插入
                // 因为1.之后的信封宽度和高度都会大于等于这个信封
                embedArray[depth++] = envelopes[i][1]; // 如果同宽度的让高度低的排在前面会发生同宽度嵌套
            } else {
                embedArray[index] = envelopes[i][1]; // 不是使用插入,而是覆盖,不会改变最大嵌套长度,但是如果之后不断覆盖后终于变成了插入到尾部,就得到了一个新的更长的长度
                // 如果宽度相同让高度低的放在前面会提前覆盖掉之前的高度信息,和等宽度嵌套
            }
        }
        return depth;
    }
}
// @lc code=end
