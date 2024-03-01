import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=315 lang=java
 *
 * [315] 计算右侧小于当前元素的个数
 *
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (41.96%)
 * Likes:    758
 * Dislikes: 0
 * Total Accepted:    56.3K
 * Total Submissions: 134.2K
 * Testcase Example:  '[5,2,6,1]'
 *
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i]
 * 右侧小于 nums[i] 的元素的数量。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0] 
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [-1]
 * 输出：[0]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer> result = new ArrayList<>();
    // private static HashMap map = new HashMap<Integer, Integer>();
    //HashMap<Integer, Integer> map = new HashMap<>();
    int[] temp;
    int[] ans;
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null) {
            return result;
        } else if(nums.length < 2){
            result.add(0);
            return result;
        }
        this.temp = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            temp[i] = i;
            ans[i] = 0;
        }

        mergeSort(nums, 0, nums.length - 1);
        for (int i: ans) {
            result.add(i);
        }
        return result;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if(l == r){
           return;  
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    public void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
        int[] indexhelp = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
            if(arr[p1] > arr[p2]){
                ans[temp[p1]] += r - p2 + 1;
                help[i] = arr[p1];
                indexhelp[i] = temp[p1];
                i++;
                p1++;
            } else{
                help[i] = arr[p2];
                indexhelp[i] = temp[p2];
                i++;
                p2++;
            }
		}
		while (p1 <= m) {

			help[i] = arr[p1];
            indexhelp[i] = temp[p1];
            i++;
            p1++;
		}
		while (p2 <= r) {
			help[i] = arr[p2];
            indexhelp[i] = temp[p2];
            i++;
            p2++;
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
            temp[l + i] = indexhelp[i];
		}
	}
    // public static void main(String[] args) {
    //     // Create a new Solution instance
    //     Solution solution = new Solution();
    //     // Create a test case
    //     int[] num ={ 5,2,6,1};
    //     // Get the answer
    //     List<Integer> answer = solution.countSmaller(num);
    //     // Print the answer
    //     for (int i: answer) {
    //         System.out.println(i);
    //     }
    // }



}
// @lc code=end

