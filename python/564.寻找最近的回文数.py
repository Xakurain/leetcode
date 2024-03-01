#
# @lc app=leetcode.cn id=564 lang=python3
#
# [564] 寻找最近的回文数
#
# https://leetcode-cn.com/problems/find-the-closest-palindrome/description/
#
# algorithms
# Hard (18.27%)
# Likes:    201
# Dislikes: 0
# Total Accepted:    15.7K
# Total Submissions: 57.3K
# Testcase Example:  '"123"'
#
# 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
# 
# “最近的”定义为两个整数差的绝对值最小。
# 
# 
# 
# 示例 1:
# 
# 
# 输入: n = "123"
# 输出: "121"
# 
# 
# 示例 2:
# 
# 
# 输入: n = "1"
# 输出: "0"
# 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
# 
# 
# 
# 
# 提示:
# 
# 
# 1 <= n.length <= 18
# n 只由数字组成
# n 不含前导 0
# n 代表在 [1, 10^18 - 1] 范围内的整数
# 
# 
#

# @lc code=start
import math
class Solution:
    def nearestPalindromic(self, n: str) -> str:
        l = len(n)
        #两个临界值
        lst = [10 ** (l-1) - 1, 10 ** l + 1]
        b = math.ceil(l/2)
        high = int(n[:b])

        for high1 in [high - 1, high, high + 1]:
            c = high1
            high2 = c * (10 ** (l - b))
            for i in range(l - b):
                high2 = high2 + (c // (10**(b - i -1))) * (10 ** i)
                c = c % (10**(b - i -1))
            lst.append(high2)
        if int(n) in lst:
            lst.remove(int(n))
        m = abs(int(n) - lst[0])
        k = 0
        for i in range(1, len(lst)):
            minus = abs(int(n) - lst[i])
            if minus == m:
                if lst[i] <= lst[k]:
                    k = i
            elif minus < m:
                k = i
                m = minus
        return(str(lst[k])) 

# @lc code=end

