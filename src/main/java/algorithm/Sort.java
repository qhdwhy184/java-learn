package algorithm;

import com.google.gson.Gson;

/**
 * Created by wangyuanhui on 16/5/30.
 */
public class Sort {
    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,4,2,3};
        try {
            quickSort(arr,arr.length);
            System.out.println(new Gson().toJson(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 对于一个int数组，请编写一个快速排序算法，对数组元素排序。
     * 给定一个int数组A及数组的大小n，请返回排序后的数组。
     * 测试样例：
     * [1,2,3,5,2,3],6
     * [1,2,2,3,3,5]
     */
    public static int[] quickSort(int[] A, int n) throws Exception {
        if(n < 0) throw new Exception("Illegal argument:n");
        if(n == 0 || n == 1 || A == null) return A;
        quickSortPartition(0, n-1, A);
        return A;
    }

    private static void quickSortPartition(int left, int right, int[] toSort){
        System.out.println("A");
        if(left >= right) return;
        int begin = left, end = right - 1;
        int pivot = right;
        while(begin <= end) {
            while(begin <= end && toSort[begin] <= toSort[pivot]) {
                ++begin;
            }

            if(begin <= end){
                swap(begin, pivot, toSort);
                pivot = begin;
                ++begin;
            }

            while(begin <= end && toSort[end] >= toSort[pivot]) {
                --end;
            }

            if(begin <= end){
                swap(end, pivot, toSort);
                pivot = end;
                --end;
            }
        }
        quickSortPartition(left,pivot - 1,toSort);
        quickSortPartition(pivot + 1,right,toSort);
    }

    private static void swap(int one, int pivot, int[] toSort) {
        int temp = toSort[one];
        toSort[one] = toSort[pivot];
        toSort[pivot] = temp;
    }
}
