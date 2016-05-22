package algorithm;

/**
 * Created by wangyuanhui on 16/5/22.
 */
public class BinarySearch {
    /***
     * 给定一棵完全二叉树的根节点root，返回这棵树的节点个数。如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
     * 给定树的根结点root，请返回树的大小。
     */
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static int count(TreeNode root) {
        if (root == null) return 0;
        TreeNode leftChild = root.left;
        int depth = 1;
        while(leftChild != null) {
            ++depth;
            leftChild = leftChild.left;
        }

        TreeNode rightChild = root.right;
        int rightLeftDepth = 1;
        while(rightChild != null) {
            ++rightLeftDepth;
            rightChild = rightChild.left;
        }

        if(depth == rightLeftDepth) {
            return 1 + countFullBinaryTreeNodeByDepth(depth - 1) + count(root.right);
        }

        return 1 + count(root.left) + countFullBinaryTreeNodeByDepth(rightLeftDepth - 1);
    }

    private static int countFullBinaryTreeNodeByDepth(int depth) {
        int res = 0;
        for(int i = depth; i>=1; i--) {
            res += Math.pow(2, i-1);
        }
        return res;
    }

    /**
     * 有一个有序数组arr，其中不含有重复元素，请找到满足arr[i]==i条件的最左的位置。如果所有位置上的数都不满足条件，返回-1。
     * 给定有序数组arr及它的大小n，请返回所求值。
     * 测试样例：
     * [-1,0,2,3],4
     * 返回 2
     * @param arr
     * @param n
     * @return
     */
    public static int findLeftMost(int[] arr, int n) {
        if (n == 0) return -1;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == mid) {
                while( mid != 0 && arr[mid - 1] == mid - 1) {
                    mid --;
                }
                return arr[mid];
            }
            if (arr[mid] < mid) {
                left = mid + 1;
                continue;
            }
            if (arr[mid] > mid) {
                right = mid - 1;
            }
        }
        return -1;
    }
    /**
     * 对于一个有序循环数组arr，返回arr中的最小值。有序循环数组是指，有序数组左边任意长度的部分放到右边去，右边的部分拿到左边来。
     * 比如数组[1,2,3,3,4]，是有序循环数组，[4,1,2,3,3]也是。
     * 给定数组arr及它的大小n，请返回最小值。
     * @param arr
     * @param n
     * @return
     */
    public static int findMin(int[] arr, int n) {
        if (n == 1) return arr[0];
        if (arr[0] < arr[n - 1]) return arr[0];
        int left = 0, right = n - 1;
        while (true) {
            int mid = (left + right) / 2;
            if (mid == left) return arr[right];
            if (arr[mid] < arr[left]) {
                right = mid;
                continue;
            }

            if (arr[mid] > arr[left]) {
                left = mid;
                continue;
            }

            if (arr[mid] == arr[left]) {
                left++;
            }
        }
    }

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
