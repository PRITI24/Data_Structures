/*
 * 377. Combination Sum IV

Asked in:
Amazon
4
Google
3
Facebook
2
Microsoft
2


Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

 https://leetcode.com/problems/combination-sum-iv/

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
 

Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
 * 
 * 
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(target<0)return 0;
        int dp[]=new int[target+1];
        Arrays.fill(dp,-1);
        return combine(nums,target,dp);
    }
    public int combine(int[] nums, int target,int[] dp) {
        if(target==0)return 1;
        if(target<0)return 0;
        if(dp[target]!=-1)return dp[target];
        int count=0;
        for(int i=0;i<nums.length;i++){
            count=count+combine(nums,target-nums[i],dp);
        }
        dp[target]=count;
        return count;
    }
}
