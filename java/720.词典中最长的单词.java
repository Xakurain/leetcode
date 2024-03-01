import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=720 lang=java
 *
 * [720] 词典中最长的单词
 *
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/description/
 *
 * algorithms
 * Easy (48.31%)
 * Likes:    292
 * Dislikes: 0
 * Total Accepted:    53.9K
 * Total Submissions: 103.8K
 * Testcase Example:  '["w","wo","wor","worl","world"]'
 *
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * 
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply" 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestWord(String[] words) {
        radixSort(words, 0, words.length - 1, maxlen(words));
        //if(words[0].length() > 1) return "";
        Set<String> hash = new HashSet<>();
		String ans = "";
        
		for (String word : words) {
			boolean add = true;
			if (!hash.contains(word.substring(0, word.length() - 1))) {
                add = false;
            }
            if(word.length() == 1){
                add = true;
            }
			if (add) {
				hash.add(word);
                if(word.length() > ans.length()){
                    ans = word;
                }
				
			}
		}
		return ans;

    }
    
    public int maxlen(String[] words){
        int res = 0;
        int len;
        for(String i: words){
            len = i.length();
            res = Math.max(res, len);
        }
        return res;
    }

    public char get_char(String word, int i){
        if(i >= word.length() ){
            return '`';
        }
        return word.charAt(i);
    }

    public void radixSort(String[] words, int begin, int end, int digit){
        final int radix = 27;
        int i = 0, j = 0;

        String[] help = new String[end - begin + 1];
        for(int d = digit - 1; d >= 0; d--){
            int[] count = new int[radix];
            for(i = begin; i <= end; i++){
                char ch = get_char(words[i], d);
                j = (int)(ch - '`');
                count[j]++;    
            }

            for(i = 1; i < radix; i++){
                count[i] = count[i] + count[i - 1];
            }

            for(i = end; i >= begin; i--){
                char ch = get_char(words[i], d);
                j = (int)(ch - '`');
                help[count[j] - 1] = words[i];
                count[j]--;
            }
            for(i = begin, j = 0; i <= end; i++, j++){
                words[i] = help[j];
            }
        }
    }
    

}

 
// @lc code=end

