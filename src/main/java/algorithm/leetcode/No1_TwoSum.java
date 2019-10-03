package algorithm.leetcode;

import java.util.Arrays;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */

public class No1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[]{-1,-1};

        int[] sorted = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
        }

        Arrays.sort(sorted);
        int start, end;
        for(start = 0, end = sorted.length - 1; start < end; ) {
            if(sorted[start] + sorted[end] < target) {
                ++ start;
                continue;
            }

            if(sorted[start] + sorted[end] > target) {
                -- end;
                continue;
            }

            break;
        }

        for(int i = 0; i < nums.length; i++) {
            if(res[0] == -1 && nums[i] == sorted[start]) {
                res[0] = i;
                continue;
            }
            if (res[1] == -1 && nums[i] == sorted[end]) {
                res[1] = i;
                continue;
            }
            if (res[1] != -1 && res[0] != -1) {
                break;
            }
        }

        return res;
    }
}
