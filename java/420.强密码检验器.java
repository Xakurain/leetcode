import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/*
 * @lc app=leetcode.cn id=420 lang=java
 *
 * [420] 强密码检验器
 *
 * https://leetcode-cn.com/problems/strong-password-checker/description/
 *
 * algorithms
 * Hard (21.32%)
 * Likes:    136
 * Dislikes: 0
 * Total Accepted:    9.4K
 * Total Submissions: 27.9K
 * Testcase Example:  '"a"'
 *
 *  
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 
 * 
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 
 * 
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0
 * 。
 * 
 * 在一步修改操作中，你可以：
 * 
 * 
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：password = "a"
 * 输出：5
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：password = "aA1"
 * 输出：3
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：password = "1337C0d3"
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 * 
 * 
 */

// @lc code=start
class Solution {
    public int strongPasswordChecker(String password) {
        char[] cs = password.toCharArray();
        int n = password.length();
        int down = 0, up = 0, num = 0;

        for (char ch : cs) {
            if (ch >= 'a' && ch <= 'z')
                down = 1;
            if (ch >= 'A' && ch <= 'Z')
                up = 1;
            if (ch >= '0' && ch <= '9')
                num = 1;
        }
        int typenum = down + up + num;

        if (n < 6) {
            return Math.max(6 - n, 3 - typenum);
        } else if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            int i, j = 0;
            for (i = 0; i < n; ) {
                while (j < n && cs[i] == cs[j]) {
                    j++;
                }
                cnt = j - i;
                i = j;
                if (cnt >= 3)
                    replace += cnt / 3;
            }
            return Math.max(3 - typenum, replace);
        } else {
            int replace = 0;
            int[] a = {0, 0, 0};
            for (int i = 0; i < n; ){
                int j = i;
                
                while (j < n && cs[i] == cs[j]){
                    j++;
                }
                int cnt = j - i;
                i = j;
                if (cnt >= 3){
                    replace += cnt / 3;
                    a[cnt % 3]++;
                }
            }
            int del = n - 20;
            int cur = del;
            for (int i = 0; i < 3; i++){
                if (i == 2)
                    a[i] = replace;
                if (a[i] != 0 && cur != 0){
                    int b = Math.min(a[i] * (i + 1), cur);
                    cur -= b;
                    replace -= b / (i + 1);
                }
                
            }
            return del + Math.max(replace, 3 - typenum);

        }

    }

}

// @lc code=end
