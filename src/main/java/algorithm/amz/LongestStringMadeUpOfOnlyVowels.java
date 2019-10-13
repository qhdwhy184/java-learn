package algorithm.amz;

import com.google.gson.Gson;
import org.junit.Assert;

import java.util.*;

/**
 * https://aonecode.com/amazon-online-assessment-oa2-longest-string-made-up-of-only-vowels
 *
 * Longest String Made Up Of Only Vowels
 * You are given with a string . Your task is to remove at most two substrings of any length from the given
 * string such that the remaining string contains vowels('a','e','i','o','u') only. Your aim is to maximise
 * the length of the remaining string. Output the length of remaining string after removal of at most two
 * substrings.
 *
 * NOTE: The answer may be 0, i.e. removing the entire string.
 *
 * Sample Input
 * 2
 * earthproblem
 * letsgosomewhere
 *
 * Sample Output
 * 3
 * 2
 */
public class LongestStringMadeUpOfOnlyVowels {
    private static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public int[] solution(int n, String[] input) {
        int[] resLst = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            String s = input[i];

            // calculate vowel count in each substring
            // e.g. xxeexx -> 0,2,0
            // e.g. xxeee -> 0,3
            // e.g. exxeexxe -> 1,0,2,0,1
            LinkedList<Integer> vowelCounts = new LinkedList<>();
            int count = 0;
            for(int j = 0; j < s.length(); j++) {
                if(!vowels.contains(s.charAt(j))) {
                    if(j == 0) {
                        vowelCounts.add(0);
                        continue;
                    }

                    if(count > 0) {
                        vowelCounts.add(count);
                        vowelCounts.add(count = 0);
                        continue;
                    }

                    if(count == 0) {
                        continue;
                    }
                }

                if(vowels.contains(s.charAt(j))) {
                    ++ count;
                }
            }

            if(count > 0) {
                vowelCounts.add(count);
            }


            if(vowelCounts.size() == 0) {
                resLst[i] = 0;
                continue;
            }

            if(vowelCounts.size() == 1) {
                resLst[i] = vowelCounts.pollFirst();
                continue;
            }

            int res;
            // start && end not vowel
            if(vowelCounts.peekFirst() == 0 && vowelCounts.peekLast() == 0) {
                res = findLargest(vowelCounts);
            } else if (vowelCounts.peekFirst() != 0 && vowelCounts.peekLast() != 0) {
                res = vowelCounts.peekFirst() + vowelCounts.peekLast();
                vowelCounts.removeFirst();
                vowelCounts.removeLast();
                res += findLargest(vowelCounts);
            } else {
                res = vowelCounts.peekFirst() != 0 ? vowelCounts.pollFirst() : vowelCounts.pollLast();
                res += findLargest(vowelCounts);
            }

            resLst[i] = res;
        }
        return resLst;
    }

    private int findLargest(List<Integer> list) {
        int largest = 0;
        for(Integer c : list) {
            if(c > largest) {
                largest = c;
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        String[] testCase1 = new String[]{
                "earthproblem",
                "letsgosomewhere"};
        String[] testCase2 = new String[]{
                ""};
        String[] testCase3 = new String[]{
                "e",
                "x"};
        String[] testCase4 = new String[]{
                "ex",
                "xe",
                "exe",
                "eexxxee",
                "exxeeexxxe",
                "xxxexxeeexxxe",
                "xxxeeeexxx"};
        int[] res1 = new LongestStringMadeUpOfOnlyVowels().solution(2, testCase1);
        System.out.println("testCase1:" + new Gson().toJson(res1));
        Assert.assertEquals(3, res1[0]);
        Assert.assertEquals(2, res1[1]);

        int[] res2 = new LongestStringMadeUpOfOnlyVowels().solution(1, testCase2);
        System.out.println("testCase2:" + new Gson().toJson(res2));
        Assert.assertEquals(0, res2[0]);

        int[] res3 = new LongestStringMadeUpOfOnlyVowels().solution(2, testCase3);
        System.out.println("testCase3:" + new Gson().toJson(res3));
        Assert.assertEquals(1, res3[0]);
        Assert.assertEquals(0, res3[1]);

        int[] res4 = new LongestStringMadeUpOfOnlyVowels().solution(7, testCase4);
        System.out.println("testCase4:" + new Gson().toJson(res4));
        Assert.assertEquals(1, res4[0]);
        Assert.assertEquals(1, res4[1]);
        Assert.assertEquals(2, res4[2]);
        Assert.assertEquals(4, res4[3]);
        Assert.assertEquals(5, res4[4]);
        Assert.assertEquals(4, res4[5]);
        Assert.assertEquals(4, res4[6]);
    }
}
