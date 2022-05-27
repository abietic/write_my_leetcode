--
-- @lc app=leetcode id=181 lang=mysql
--
-- [181] Employees Earning More Than Their Managers
--

-- @lc code=start
# Write your MySQL query statement below
SELECT emp.name AS Employee FROM Employee AS emp JOIN Employee AS mng ON emp.managerId = mng.id AND emp.salary > mng.salary
-- @lc code=end

