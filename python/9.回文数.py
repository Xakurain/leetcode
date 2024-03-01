#
# @lc app=leetcode.cn id=9 lang=python3
#
# [9] 回文数
#

# @lc code=start
class Solution:
    def isPalindrome(self, x: int) -> bool:
        s = str(x)
        l = len(s)
        h = l // 2
        return s[:h] == s[-1:-h-1:-1]
# @lc code=end

