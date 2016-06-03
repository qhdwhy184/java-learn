package algorithm.leetcode;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyuanhui on 16/6/2.
 */
public class LeetCode {
    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,4};
        int[] num2 = new int[]{4,5,3,6};
        System.out.println(new Gson().toJson(new LeetCode().intersection(num1, num2)));
    }
    /**
     * 349
     * Intersection of Two Arrays My Submissions QuestionEditorial Solution
     * Total Accepted: 12573 Total Submissions: 28156 Difficulty: Easy
     * Given two arrays, write a function to compute their intersection.

     * Example:
     * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

     * Note:
     * Each element in the result must be unique.
     * The result can be in any order.

     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> firstSet = new HashSet<Integer>();
        for(int i = 0; i < nums1.length; i++) {
            firstSet.add(nums1[i]);
        }

        Set<Integer> secondSet = new HashSet<Integer>();
        for(int i = 0; i < nums2.length; i++) {
            secondSet.add(nums2[i]);
        }

        firstSet.retainAll(secondSet);

        int[] result = new int[firstSet.size()];
        int i = 0;
        for(Integer num : firstSet) {
            result[i++] = num;
        }
        return result;
    }
}
