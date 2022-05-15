/*
 * @lc app=leetcode id=67 lang=cpp
 *
 * [67] Add Binary
 */

// @lc code=start
using namespace std;
#include <string>
#include<algorithm>
class Solution
{
public:
    string addBinary(string a, string b)
    {
        if (a == "0")
        {
            return b;
        }
        if (b == "0")
        {
            return a;
        }
        int padding = a.length() - b.length();
        if (padding < 0)
        {
            a = a.insert(0, -padding, '0');
        }
        else if (padding > 0)
        {
            b = b.insert(0, padding, '0');
        }
        bool carry = false;
        string res = "";
        for (auto ai = a.rbegin(), bi = b.rbegin(); ai != a.rend(); ai++, bi++)
        {
            int an = *ai - '0', bn = *bi - '0', cn = 0;
            if (an ^ bn)
            { // 只有一个为1
                if (carry)
                {
                    cn = 0;
                }
                else
                {
                    cn = 1;
                    carry = false;
                }
            }
            else if (an)
            { // 两个都为1
                cn = carry ? 1 : 0;
                carry = true;
            }
            else
            { // 都为0
                cn = carry ? 1 : 0;
                carry = false;
            }
            res.push_back(cn + '0');
        }
        if (carry) {
            res.push_back('1');
        }
        reverse(res.begin(), res.end());
        return res;
    }
};
// @lc code=end
