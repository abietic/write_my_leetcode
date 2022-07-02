import java.util.Arrays;

/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    // private static int gcd(int p, int q) {
    // if (p < q) {
    // return gcd(q, p);
    // }
    // int tmp;
    // while ((tmp = p % q) != 0) {
    // p = q;
    // q = tmp;
    // }
    // return q;
    // }

    // public void rotate(int[] nums, int k) {
    // // 使用常数额外空间的方法，还没有弄明白数学原理
    // // 将元素直接放置到它的最终对应位置，这时会使原来在这里的元素失去存放地点
    // // 因此需要依次进行转移直到起始移动位置被放入新值
    // // 1.当移动步长可以整除数组长度时，经过一圈的总路程就可以回到起始位置
    // // 这时只需要将一个跨度内的所有元素都当成起始元素做一次循环移动就能完成移动。
    // // 2.如果移动步长与数组长度互质，这时一次循环移动就会遍历就会完成所有元素的遍历
    // // 3.在其他情况下，就需要求出移动步长与数组长度的最小公倍数，以求得一次循环移动遍历的元素数，和遍历的元素位置
    // // 为什么几个循环移动的起点是连续的？起点移位遍历的位置为: (0 + tk) % n
    // // 接下来的其他的循环移动遍历的位置记为: (0 + offset + tk) % n == (offset % n + tk % n) % n 其中
    // 0 < offset < k, k < n
    // // -> (offset + tk % n) % n 假设 (offset + tk % n) % n == tk % n
    // // -> offset + tk % n == n + tk % n
    // // -> offset == n 与前提条件不符
    // final int numLen = nums.length;
    // k %= numLen;
    // if (k == 0) {
    // return;
    // }
    // final int gcd = gcd(numLen, k), lcm = numLen / gcd * k, elesPerRotate = lcm /
    // k /* 等于numLen / gcd */,
    // rotateCount = numLen / elesPerRotate /* 等于gcd */;
    // for (int idx = 0; idx < rotateCount; ++idx) {
    // int cursor = (idx + k) % numLen, cur = nums[idx], nxt;
    // while (cursor != idx) {
    // nxt = nums[cursor];
    // nums[cursor] = cur;
    // cur = nxt;
    // cursor = (cursor + k) % numLen;
    // }
    // nums[idx] = cur;
    // }
    // }

    // public void rotate(int[] nums, int k) {
    // // 直接开一个同样大小的数组，将结果映射过去，再把结果填回来
    // final int numLen = nums.length;
    // k %= numLen;
    // if (k == 0) {
    // return;
    // }
    // int[] rotated = new int[numLen];
    // for (int i = 0; i < numLen; ++i) {
    // int to = (i + k) % numLen;
    // rotated[to] = nums[i];
    // }
    // for (int i = 0; i < numLen; ++i) {
    // nums[i] = rotated[i];
    // }
    // }

    // private static void reverse(int[] nums, int left, int right) {
    // while (left < right) {
    // int tmp = nums[left];
    // nums[left] = nums[right];
    // nums[right] = tmp;
    // ++left;
    // --right;
    // }
    // }

    // public void rotate(int[] nums, int k) {
    // // 使用3次翻转将相应的段就位
    // final int numLen = nums.length;
    // k %= numLen;
    // if (k == 0) {
    // return;
    // }
    // k = numLen - k;
    // final int rightPartLen = numLen - k;
    // // a_{0}...a_{k - 1} | a_{k}...a_{n - 1}
    // // a_{n - 1}...a_{k} | a_{k - 1}...a_{0}
    // // a_{k}...a_{n - 1} | a_{k - 1}...a_{0}
    // // a_{k}...a_{n - 1} | a_{0}...a_{k - 1}
    // reverse(nums, 0, numLen - 1);
    // reverse(nums, 0, rightPartLen - 1);
    // reverse(nums, rightPartLen, numLen - 1);
    // }

    public void rotate(int[] nums, int k) {
        // 以块的形式交换
        // 需要检查k的大小以减少移动和反向次数
        int numLen = nums.length;
        k %= numLen;
        if (k == 0) {
            return;
        }
        // 转换成左移
        k = numLen - k;
        int boundLeft = 0, boundRight = numLen - 1;
        int rightPartLen = numLen - k;
        int tmp, left, right;
        while (boundLeft < boundRight) {
            if (k > rightPartLen) {
                // a_{0}...a_{n - k - 1} | a_{n - k}...a_{k - 1} | a_{k}...a_{n - 1}
                // a_{k}...a_{n - 1} | a_{n - k}...a_{k - 1} | a_{0}...a_{n - k - 1}
                for (left = boundLeft, right = boundLeft + k; right <= boundRight; ++left, ++right) {
                    tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                }
                boundLeft = numLen - k + boundLeft;
                k = 2 * k - numLen;
                numLen = boundRight - boundLeft + 1;
                rightPartLen = numLen - k;
            } else if (k < rightPartLen) {
                // a_{0}...a_{k - 1} | a_{k}...a_{2k - 1} | a_{2k}...a_{n - 1}
                // a_{k}...a_{2k - 1} | a_{0}...a_{k - 1} | a_{2k}...a_{n - 1}
                for (left = boundLeft, right = boundLeft + k; left < boundLeft + k; ++left, ++right) {
                    tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                }
                boundLeft += k;
                numLen -= k;
                rightPartLen -= k;
            } else {
                // 两段大小相等，直接交换即可
                for (left = boundLeft, right = boundLeft + rightPartLen; left < right /* 防止溢出 */ && right <= boundRight; ++right, ++left) {
                    tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                }
                break;
            }
        }
    }
}
// @lc code=end
