/*
 * @lc app=leetcode id=10 lang=cpp
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
#include<string>
using namespace std;
class Solution {
public:
    bool isMatch(string s, string p) {
        if (s.empty()) {
            if (p.empty()) {
                // string和pattern直接匹配了
                return true;
            } else {
                // 如果pattern还有剩余字符，剩下的是否全是*
                for (int i = 0 ; i < p.size() ; i ++) {
                    if (p[i] != '*') {
                        return false;
                    }
                }
                return true;
            }
        }
        if (p.empty()) {
            return false;
        }
        /// 做错了还没改呢
        // if (p[1] == '*') {
        //     if (p[0] == s[0] || p[0] == '.') {
        //         if (isMatch(s , p.substr(2)))
        //             return true;
        //         if (p[0] == '.') {
        //             int i = 2;
        //             for ( ; i < s.size() && s[1] == s[i] ; i ++);
        //             if (i > s.size()) {
        //                 return isMatch("" , p.substr(2));
        //             }
        //             return isMatch(s.substr(i) , p.substr(2));
        //         } else {
        //             int i = 1;
        //             for ( ; i < s.size() && s[0] == s[i] ; i ++);
        //             return isMatch(s.substr(i) , p.substr(2));
        //         }
        //     } else {
        //         return isMatch(s , p.substr(2));
        //     }
        // } else {
        //     if (p[0] == s[0] || p[0] == '.') {
        //         return isMatch(s.substr(1) , p.substr(1));
        //     }
        //     return false;
        // }
    }
};
// @lc code=end

