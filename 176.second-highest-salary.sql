--
-- @lc app=leetcode id=176 lang=mysql
--
-- [176] Second Highest Salary
--

-- @lc code=start
# Write your MySQL query statement below
-- SELECT MAX(salary) AS SecondHighestSalary FROM Employee a WHERE salary < (SELECT MAX(salary) FROM Employee b)
SELECT MAX(a.salary) AS SecondHighestSalary FROM Employee a WHERE a.salary < (SELECT DISTINCT salary FROM Employee b ORDER BY b.salary DES LIMIT 1 OFFSET 0)
-- @lc code=end

