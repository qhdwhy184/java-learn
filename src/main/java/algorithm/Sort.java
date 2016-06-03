package algorithm;

import com.google.gson.Gson;

/**
 * Created by wangyuanhui on 16/5/30.
 */
public class Sort {
    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,4,2,3};
        try {
//            heapSort(arr, arr.length);
            System.out.println(new Gson().toJson(heapSort(arr,arr.length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对于一个int数组，请编写一个堆排序算法，对数组元素排序。
     * 给定一个int数组A及数组的大小n，请返回排序后的数组。
     * 测试样例：
     * [1,2,3,5,2,3],6
     * [1,2,2,3,3,5]
     * @param A
     * @param n
     * @return
     */
    public static int[] heapSort(int[] A, int n) throws Exception {
        if(A == null || n < 0) throw new Exception("invalid arguments");
        if(n <= 1) return A;
        buildHeap(A, n);
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            System.out.println("C");
            result[i] = A[0];
            swap(0, n - 1 - i, A);
            buildHeap(A, n - i - 1);
        }
        return result;
    }

    private static void buildHeap(int[] A, int n) {
        if(n == 1) return;
        for(int i = ((n - 1) - 1) / 2; i >= 0; i--) {
            for (int j = i; j <= ((n - 1) - 1) / 2; ) {
                if(2 * j + 2 <= n - 1) {
                    if(A[j] <= A[2 * j + 2] && A[j] <= A[2 * j + 1]) {
                        break;
                    }

                    if(A[2 * j + 2] <= A[2 * j + 1]) {
                        swap(2*j + 2, j, A);
                        j = 2*j + 2;
                    } else {
                        swap(2*j + 1, j, A);
                        j = 2*j + 1;
                    }

                } else {
                    if(A[j] <= A[2 * j + 1]) {
                        break;
                    } else {
                        swap(j, 2*j+1, A);
                        j = 2*j + 1;
                    }
                }
            }
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
