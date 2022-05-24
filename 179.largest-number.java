import java.util.Arrays;

/*
 * @lc app=leetcode id=179 lang=java
 *
 * [179] Largest Number
 */

// @lc code=start
class Solution {
    // private static int repeatPrefixCompare(String str, String prefix) {
    //     // 要比较的是除了重复前缀之后剩下的部分的大小
    //     while (str.startsWith(prefix)) {
    //         str = str.substring(prefix.length());
    //     }
    //     if (str.isEmpty()) {
    //         // 完全多次重复，谁前谁后都一样
    //         return 0;
    //     } else if (prefix.startsWith(str)){
    //         // 如果剩下的部分又是前缀的前缀，要保证排列时比较大的排在前面
    //         // 432,43243
    //         return -repeatPrefixCompare(prefix, str); // 这里返回相反数也是因为比较元素互换位置了
    //     }
    //     return str.compareTo(prefix);
    // }
    // public String largestNumber(int[] nums) {
    //     String res = Arrays.stream(nums).mapToObj((n) -> {
    //         // 将输入数字变成字符串
    //         return Integer.toString(n);
    //     }).sorted((a, b) -> {
    //         if (a.equals(b)) {
    //             return 0;

    //         // 如果是一个字符串是另一个字符串的重复前缀的元素，那么
    //         // 就要判断是将前缀元素放在前面组成x+1次的重复前缀还是放在后面
    //         } else if (a.startsWith(b)) {
    //             // [3,30,34,5,9]
    //             return -repeatPrefixCompare(a, b);
    //         } else if (b.startsWith(a)) {
    //             return repeatPrefixCompare(b, a); // 这里没加正负号是因为比较元素位置互换了
    //         }
    //         return -a.compareTo(b);
    //     }).reduce("", (sum, a) -> {
    //         return sum + a;
    //     });
    //     // [0,0]
    //     return res.charAt(0) == '0' ? "0" : res;
    // }

    public String largestNumber(int[] nums) {
        // 不用那么费劲，直接尝试一下两个数字在两种顺序下那个更大就行。。。。
        String res = Arrays.stream(nums).mapToObj((n)->{return Integer.toString(n);}).sorted((a, b)->{
            return -(a + b).compareTo(b + a);
        }).reduce("", (sum, a)->{return sum + a;});
        return res.charAt(0) == '0' ? "0" : res;
    }
}
// @lc code=end
