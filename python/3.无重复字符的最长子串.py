#
# @lc app=leetcode.cn id=3 lang=python3
#
# [3] 无重复字符的最长子串
#

# @lc code=start
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        length = 0
        ss = set()
        for i in range(len(s)):
            cur_length = 0
            for j in range(i, len(s)):
                if s[j] in ss:
                    ss.clear()
                    break
                else:
                    ss.add(s[j])
                    cur_length += 1
            length = max(length, cur_length)
        return length    


# @lc code=end

