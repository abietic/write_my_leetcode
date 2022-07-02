/*
 * @lc app=leetcode id=190 lang=java
 *
 * [190] Reverse Bits
 */

// @lc code=start
class Solution {
    // // you need treat n as an unsigned value
    // public int reverseBits(int n) {
    // int rev = 0;
    // for (int i = 0; i < 32; ++i) {
    // rev = (rev << 1) | ((n & (1 << i)) != 0 ? 1 : 0);
    // }
    // return rev;
    // }
    public int reverseBits(int n) {
        // 用与191题计算方法类似，做in-place的反序
        int _16Rev = (n >>> 16) | (n << 16);
        int _8Rev = ((_16Rev >>> 8) & 0x00ff00ff) | ((_16Rev << 8) & 0xff00ff00);
        int _4Rev = ((_8Rev >>> 4) & 0x0f0f0f0f) | ((_8Rev << 4) & 0xf0f0f0f0);
        int _2Rev = ((_4Rev >>> 2) & 0x33333333) | ((_4Rev << 2) & 0xcccccccc);
        int res = ((_2Rev >>> 1) & 0x55555555) | ((_2Rev << 1) & 0xaaaaaaaa);
        return res; 
    }
}
// @lc code=end
