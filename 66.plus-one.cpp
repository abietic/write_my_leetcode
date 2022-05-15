/*
 * @lc app=leetcode id=66 lang=cpp
 *
 * [66] Plus One
 */

// @lc code=start
using namespace std;
#include<vector>
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
       auto num_iter = digits.rbegin();
       bool carry = true;
       while (carry && num_iter != digits.rend()) {
           *num_iter += 1;
           if (*num_iter < 10) {
               carry = false;
               break;
           }
           *num_iter = 0;
           num_iter += 1;
       }
       if (carry && num_iter == digits.rend()) {
           digits.insert(digits.begin(), 1);
       }
       return digits;
    }
};
// @lc code=end

