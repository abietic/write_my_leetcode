/*
 * @lc app=leetcode id=83 lang=cpp
 *
 * [83] Remove Duplicates from Sorted List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
// struct ListNode
// {
//     int val;
//     ListNode *next;
//     ListNode() : val(0), next(nullptr) {}
//     ListNode(int x) : val(x), next(nullptr) {}
//     ListNode(int x, ListNode *next) : val(x), next(next) {}
// };
class Solution
{
public:
    ListNode *deleteDuplicates(ListNode *head)
    {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        int memo = head->val;
        head = head->next;
        ListNode *neo = new ListNode(memo);
        ListNode *cur = neo;
        while (head != nullptr) {
            if (head->val != memo){
                memo = head->val;
                ListNode *next = new ListNode(memo);
                cur->next = next;
                cur = next;
            }
            head = head->next;
        }
        return neo;
    }
};
// @lc code=end
