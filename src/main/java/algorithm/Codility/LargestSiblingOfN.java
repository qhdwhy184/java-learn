package algorithm.Codility;

import java.util.*;

/**
 * 213 ->321
 * 355->553
 * >100,000,000 -> -1
 * 0 <= N <= 2,147,483,647
 */
public class LargestSiblingOfN {
    public int getLargest(int N) {
        int number = N;
        LinkedList<Integer> numberList = new LinkedList<>();
        while(number > 0){
            int temp = number % 10;
            numberList.addFirst(temp);
            number /= 10;
        }

        if(numberList.size() >= 10) return -1;

        numberList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int res = 0;
        for(Integer digit : numberList) {
            res = res * 10 + digit;
        }

        int max_value = 100000000;
        return res <= max_value ? res : -1;
    }

    public static void main(String[] args) {
        System.out.println("213:"+new LargestSiblingOfN().getLargest(213));
        System.out.println("355:"+new LargestSiblingOfN().getLargest(355));
        System.out.println("0:"+new LargestSiblingOfN().getLargest(0));
        System.out.println("2147483647:"+new LargestSiblingOfN().getLargest(2147483647));
        System.out.println("1000000000:"+new LargestSiblingOfN().getLargest(1000000000));
        System.out.println("100000000:"+new LargestSiblingOfN().getLargest(100000000));
        System.out.println("100000001:"+new LargestSiblingOfN().getLargest(100000001));
        System.out.println("100000001:"+new LargestSiblingOfN().getLargest(10927801));
    }
}


