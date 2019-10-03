package algorithm.Codility;

import java.util.*;


public class FixCode {

    int solution(int[] A) {
        int n = A.length;
        int[] L = new int[n + 1];
        L[0] = -1;
        for (int i = 0; i < n; i++) {
            L[i + 1] = A[i];
        }
        int count = 0;
        int pos = (n) / 2;
        int candidate = L[pos+1];
        for (int i = 1; i <= n; i++) {
            if (L[i] == candidate)
                count = count + 1;
        }
        if (count > pos)
            return candidate;
        return (-1);
    }

    public static void main(String[] args) {
        System.out.println(new FixCode().solution(new int[]{2})); //2
        System.out.println(new FixCode().solution(new int[]{2,2}));//2
        System.out.println(new FixCode().solution(new int[]{2,2,2,2,2,3,4,4,6}));//2
        System.out.println(new FixCode().solution(new int[]{2,2,2,2,2,3,4,4,4,6}));//-1
        System.out.println(new FixCode().solution(new int[]{2,2,2,2,4,4,4,4,4}));//4
        System.out.println(new FixCode().solution(new int[]{2,2,2,2,2,4,4,4,4,4}));//-1
        System.out.println(new FixCode().solution(new int[]{2,2,2,3,3,3,3,3,4,4}));//-1
        System.out.println(new FixCode().solution(new int[]{2,2,3,3,3,3,3,3,4,4}));//3
        System.out.println(new FixCode().solution(new int[]{2,2,2,3,3,3,3,3,4}));//3
        System.out.println(new FixCode().solution(new int[]{2,2,2,3,3,3,3,4}));//-1
        System.out.println(new FixCode().solution(new int[]{2,3,3,3,3,3,4,4}));//3
        System.out.println(new FixCode().solution(new int[]{0,0,0,0,3,3,4,4}));//-1
        System.out.println(new FixCode().solution(new int[]{0,0,0,0,0,3,3,4,4}));//0
    }

}
