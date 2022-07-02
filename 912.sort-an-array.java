/*
 * @lc app=leetcode id=912 lang=java
 *
 * [912] Sort an Array
 */

// @lc code=start
class Solution {
    // // 实现一个快排
    // private static void _partition(int[] nums, int leftBound, int rightBound) {
    // if (leftBound >= rightBound) {
    // return;
    // }
    // int left = leftBound, right = rightBound, midVal = nums[leftBound];
    // while (left < right) {
    // while (left < right && nums[left] <= midVal) {
    // left++;
    // }
    // while (left < right && nums[right] > midVal) {
    // right--;
    // }
    // if (left < right) {
    // int tmp = nums[left];
    // nums[left] = nums[right];
    // nums[right] = tmp;
    // }
    // }
    // while (nums[left] > midVal) {
    // left--;
    // }
    // while (right <= rightBound && nums[right] <= midVal) {
    // right++;
    // }
    // int tmp = nums[left];
    // nums[left] = midVal;
    // nums[leftBound] = tmp;
    // _partition(nums, leftBound, left - 1);
    // _partition(nums, right, rightBound);
    // }
    // public int[] sortArray(int[] nums) {
    // _partition(nums, 0, nums.length - 1);
    // return nums;
    // }

    // // 实现一个最简单的，冒泡排序
    // // 由于O(n^2)的时间复杂度，会超时
    // public int[] sortArray(int[] nums) {
    // boolean changed = false;
    // do {
    // // 原理是不断减少序列中逆序对的个数，有序序列中逆序对的个数为0
    // changed = false;
    // for (int i = 0; i < nums.length - 1; ++i) {
    // if (nums[i] > nums[i + 1]) {
    // int tmp = nums[i];
    // nums[i] = nums[i + 1];
    // nums[i + 1] = tmp;
    // changed = true;
    // }
    // }
    // } while (changed);
    // return nums;
    // }

    // // 尝试实现一个插入排序
    // // 同样是时间复杂度O(n^2)的，超时
    // public int[] sortArray(int[] nums) {
    // // 有序的序列长度随着插入的进行逐渐增加
    // for (int i = 1; i < nums.length; ++i) {
    // int val = nums[i];
    // for (int j = i - 1; j >= 0; --j) {
    // if (val < nums[j]) {
    // nums[j + 1] = nums[j];
    // nums[j] = val;
    // } else {
    // break;
    // }
    // }
    // }
    // return nums;
    // }

    // // 实现一个选择排序
    // // 时间复杂度O(n^2)，超时
    // public int[] sortArray(int[] nums) {
    // for (int i = 0; i < nums.length; ++i) {
    // int j = i, curMin = nums[i], minIndex = i;
    // for (; j < nums.length; ++j) {
    // if (nums[j] < curMin) {
    // minIndex = j;
    // curMin = nums[j];
    // }
    // }
    // nums[minIndex] = nums[i];
    // nums[i] = curMin;
    // }
    // return nums;
    // }

    // // 实现一个归并排序，使用最简单的递归实现
    // // 时间复杂度应该都是O(nlogn)，最大额外的空间复杂度应该为O(n)
    // private static void _merge_sort(int[] nums, int left, int right) {
    // // 左开右闭区间
    // if (right - left <= 1) {
    // return;
    // }
    // int mid = (left + right) / 2;
    // _merge_sort(nums, left, mid);
    // _merge_sort(nums, mid, right);
    // _merge(nums, left, mid, right);
    // }
    // private static void _merge(int[] nums, int left, int mid, int right) {
    // // 这里每次merge都申请了新数组，要想节省分配，只用分配一个大小为n的数组，每次调用时使用
    // int[] sorted = new int[right - left];
    // int a1 = left, a2 = mid, cur = 0;
    // while (a1 < mid && a2 < right) {
    // if (nums[a1] > nums[a2]) {
    // sorted[cur++] = nums[a2++];
    // } else {
    // sorted[cur++] = nums[a1++];
    // }
    // }
    // if (a1 <mid || a2 < right) {
    // int st = a1 < mid ? a1 : a2;
    // int bd = a1 < mid ? mid : right;
    // while (st < bd) {
    // sorted[cur++] = nums[st++];
    // }
    // }
    // for (int i = left; i < right; ++i) {
    // nums[i] = sorted[i - left];
    // }
    // return;
    // }
    // public int[] sortArray(int[] nums) {
    // _merge_sort(nums, 0, nums.length);
    // return nums;
    // }

    // // 实现一个希尔排序
    // // 依然是超时了
    // public int[] sortArray(int[] nums) {
    // int step = 1;
    // while (step < nums.length / 3) {
    // step = 3 * step + 1;
    // }
    // while (step >= 1) {
    // for (int i = 0; i < nums.length; ++i) {
    // int val = nums[i];
    // for (int j = i - step; j >= 0; j -= step) {
    // if (val < nums[j]) {
    // nums[j + step] = nums[j];
    // nums[j] = val;
    // } else {
    // break;
    // }
    // }
    // }
    // step--;
    // }
    // return nums;
    // }

    // 重新实现一下快速排序
    // 这回设为左闭右开区间
    // private static void _quick_partition(int[] nums, int leftBound, int
    // rightBound) {
    // if (rightBound - leftBound <= 1) {
    // return;
    // }
    // int left = leftBound, right = rightBound - 1, midVal = nums[leftBound];
    // while (left < right) {
    // while (left < rightBound && nums[left] <= midVal) {
    // left++;
    // }
    // if (left >= right) {
    // break;
    // }
    // while (right >= leftBound && nums[right] > midVal) {
    // right--;
    // }
    // if (left < right) {
    // int tmp = nums[left];
    // nums[left] = nums[right];
    // nums[right] = tmp;
    // }
    // }
    // nums[leftBound] = nums[left - 1];
    // nums[left - 1] = midVal;
    // // 由于left总是比right先确定，所以left的位置总是正确的边界
    // _quick_partition(nums, leftBound, left - 1);
    // _quick_partition(nums, left, rightBound);
    // }

    // // 使用三向分类的快排划分实现
    // // 即问题79中的djkstru的荷兰国旗方法，一个指针单向遍历，划分出大于小于和等于midVal的部分
    // private static void _quick_partition_3ways(int[] nums, int leftBound, int
    // rightBound) {
    // if (rightBound - leftBound <= 1) {
    // return;
    // }
    // int lower = leftBound, mid = leftBound + 1, higher = rightBound, midVal =
    // nums[leftBound], tmp;
    // while (mid < higher) {
    // if (nums[mid] == midVal) {
    // // 等于midVal，放在中间区段
    // mid++;
    // } else if (nums[mid] < midVal) {
    // // 小于midVal，放在左侧区段
    // nums[lower++] = nums[mid];
    // nums[mid++] = midVal;
    // }
    // else {
    // // 大于midVal，放在右侧区段
    // tmp = nums[higher - 1];
    // nums[--higher] = nums[mid];
    // nums[mid] = tmp;
    // }
    // }
    // _quick_partition_3ways(nums, leftBound, lower);
    // _quick_partition_3ways(nums, higher, rightBound);
    // }
    // public int[] sortArray(int[] nums) {
    // _quick_partition_3ways(nums, 0, nums.length);
    // return nums;
    // }

    // 尝试实现一个堆排序
    // 先将整个数组建为一个最大堆，每次弹出一个堆顶元素放到堆的末尾
    // 使用一个最大堆
    private static void sink(int[] nums, int len) {
        // 将头部元素进行比较，如果较小 ，就进行下沉
        // 这里的索引为了方便计算二叉树的位置都进行了+1操作，在取数时，需要进行-1操作
        int cur = 1;
        // 保证还可能下沉
        while (cur < len + 1 && cur * 2 < len + 1) {
            int next = cur * 2;
            if (cur * 2 + 1 < len + 1) {
                // 两个子树都存在时，选较大的进行上浮
                if (nums[cur * 2 - 1] < nums[cur * 2 + 1 - 1]) {
                    next++;
                }
            }
            if (nums[cur - 1] < nums[next - 1]) {
                int tmp = nums[cur - 1];
                nums[cur - 1] = nums[next - 1];
                nums[next - 1] = tmp;
                cur = next;
            } else {
                break;
            }
        }
    }
    private static void swim(int[] nums, int len) {
        // 将最末尾元素进行比较，如果较大，就上浮
        int cur = len; // 依然是真实索引+1
        // 保证元素还有继续上浮的空间
        while (cur > 1 && cur / 2 >= 1) {
            int next = cur / 2;
            if (nums[cur - 1] > nums[next - 1]) {
                int tmp = nums[cur - 1];
                nums[cur - 1] = nums[next - 1];
                nums[next - 1] = tmp;
                cur = next;
            } else {
                break;
            }
        }

    }
    private static void makeHeap(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        for (int len = 2; len <= nums.length; ++len) {
            swim(nums, len);
        }
    }
    private static void heapSort(int[] nums) {
        makeHeap(nums);
        for (int len = nums.length - 1; len > 0; --len) {
            int tmp = nums[len];
            nums[len] = nums[0];
            nums[0] = tmp;
            sink(nums, len);
        }
    }
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }
}
// @lc code=end
