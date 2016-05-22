package algorithm;

/**
 * Created by wangyuanhui on 16/5/22.
 */
public class BinarySearch {
    /**
     * 对于一个有序数组arr，再给定一个整数num，请在arr中找到num这个数出现的最左边的位置。
     * 给定一个数组arr及它的大小n，同时给定num。请返回所求位置。若该元素在数组中未出现，请返回-1。
     * @param arr
     * @param n
     * @param num
     * @return
     */
    public static int findLeftMostAppearence(int[] arr, int n, int num) {
        if(arr.length == 0 || n == 0) return -1;
        if(arr[0] > num || arr[arr.length - 1] < num) return -1;
        int left = 0, right = n - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == num) {
                if(mid == 0 || arr[mid - 1] != num) {
                    return mid;
                }
                right = mid - 1;
            }

            if (arr[mid] > num) {
                right = mid - 1;
            }

            if (arr[mid] < num) {
                left = mid + 1;
            }
        }
        return -1;
    }
    /**
     * 局部最小值位置练习题
     *
     * 定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，如果arr[0]<arr[1]，那么arr[0]是局部最小；
     * 如果arr[N-1]<arr[N-2]，那么arr[N-1]是局部最小；如果0<i<N-1，既有arr[i]<arr[i-1]又有arr[i]<arr[i+1]，那么arr[i]是局部最小。
     * 给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，只需返回arr中任意一个局部最小出现的位置即可。
     * @param arr
     * @return
     */
    public static int findLocalLess(int[] arr) {
        if(arr.length == 0) return -1;
        if(arr.length == 1) return 0;
        if(arr[0] < arr[1]) return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length-1;
        int leftEnd = 0;
        int rightEnd = arr.length - 1;
        while (leftEnd + 1 < rightEnd) {
            int index = (leftEnd + rightEnd) / 2;
            if (arr[index - 1] > arr[index] && arr[index + 1] > arr[index]) {
                return index;
            }

            if (arr[index - 1] > arr[index]) {
                leftEnd = index;
            } else {
                rightEnd = index;
            }
        }
        return -1;
    }
}
