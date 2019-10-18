package algorithm.amz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/discuss/interview-question/330356/Amazon-or-Online-Assessment-2019-or-Longest-string-without-3-consecutive-characters
 * Longest String Without 3 Consecutive Characters
 * Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive
 * characters are same. There can be at max A 'a', B 'b' and C 'c'.
 *
 * Example 1:
 *
 * Input: A = 1, B = 1, C = 6
 * Output: "ccbccacc"
 *
 * Example 2:
 *
 * Input: A = 1, B = 2, C = 3
 * Output: "acbcbc"
 */
public class LongestStringWithout3ConsecutiveChar {
    public String solution(int A, int B, int C) {
        Pair a = new Pair('a', A);
        Pair b = new Pair('b', B);
        Pair c = new Pair('c', C);

        PriorityQueue<Pair> queue = new PriorityQueue<>();

        if(A > 0) {
            queue.add(a);
        }

        if(B > 0) {
            queue.add(b);
        }

        if(C > 0) {
            queue.add(c);
        }

        StringBuilder res = new StringBuilder();
        while(!queue.isEmpty()) {
            Pair largest = queue.poll();
            if(largest.num >= 2) {
                res.append(largest.ch);
                res.append(largest.ch);
                largest.num -= 2;
            } else if(largest.num == 1) {
                res.append(largest.ch);
                largest.num = 0;
            }

            if(queue.isEmpty()) {
               break;
            }

            Pair mid = queue.poll();
            if(mid.num >= 2) {
                res.append(mid.ch);
                res.append(mid.ch);
                mid.num -= 2;
            }else if(mid.num == 1) {
                res.append(mid.ch);
                mid.num = 0;
            }

            if(largest.num != 0) {
                queue.add(largest);
            }

            if(mid.num != 0) {
                queue.add(mid);
            }
        }

        return res.toString();
    }

    static class Pair implements Comparable<Pair>{
        char ch;
        int num;

        Pair(char ch, int num) {
            this.ch = ch;
            this.num = num;
        }

        @Override
        public int compareTo(Pair o) {
            return o.num - this.num;
        }
    }

    public static void main(String[] args) {
        System.out.println("case 1-1-6:" + new LongestStringWithout3ConsecutiveChar().solution(1,1,6));
        System.out.println("case 1-2-3:" + new LongestStringWithout3ConsecutiveChar().solution(1,2,3));
        System.out.println("case 1-1-10:" + new LongestStringWithout3ConsecutiveChar().solution(1,1,10));
        System.out.println("case 0-0-10:" + new LongestStringWithout3ConsecutiveChar().solution(0,0,10));
        System.out.println("case 0-0-1:" + new LongestStringWithout3ConsecutiveChar().solution(0,0,1));
        System.out.println("case 0-1-0:" + new LongestStringWithout3ConsecutiveChar().solution(0,1,0));
        System.out.println("case 0-10-0:" + new LongestStringWithout3ConsecutiveChar().solution(0,10,0));
        System.out.println("case 1-0-0:" + new LongestStringWithout3ConsecutiveChar().solution(1,0,0));
        System.out.println("case 10-0-0:" + new LongestStringWithout3ConsecutiveChar().solution(10,0,0));
        System.out.println("case 2-5-10:" + new LongestStringWithout3ConsecutiveChar().solution(2,5,10));
        System.out.println("case 9-8-8:" + new LongestStringWithout3ConsecutiveChar().solution(9,8,8));
    }

    /**
     *  public static String generateString(Map<Character, Integer> map) {
     * 		PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
     * 	            new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
     *
     * 		int cnt = 0;
     * 		for (Map.Entry<Character, Integer> e: map.entrySet()) {
     * 			cnt += e.getValue();
     * 			maxHeap.add(e);
     *                }
     *
     * 	    // only one char can be on hold
     * 	    Map.Entry<Character, Integer> onHold = null;
     *
     * 	    StringBuilder sb = new StringBuilder();
     *
     * 	    while (!maxHeap.isEmpty()) {
     * 	    	Map.Entry<Character, Integer> cur = maxHeap.poll();
     * 	    	sb.append(cur.getKey());
     *
     * 	    	if (onHold != null) {
     * 	    		maxHeap.add(onHold);
     * 	    		onHold = null;
     *            }
     *     		int curValue = cur.getValue();
     *     		if (curValue > 1) {
     *     			cur.setValue(curValue-1);
     *     			if (sb.length() >= 2 && cur.getKey() == sb.charAt(sb.length()-2)) { // on hold
     * 	    			onHold = cur;
     *                } else {  // add back to heap
     *     				maxHeap.add(cur);
     *                }
     *            }
     *
     *        }
     * 	    return sb.length() == cnt ? sb.toString(): "";* 	}
     */
}
