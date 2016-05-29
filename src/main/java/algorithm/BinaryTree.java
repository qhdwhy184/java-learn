package algorithm;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wangyuanhui on 16/5/27.
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new Gson().toJson(convertLoop(root)));
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 请用非递归方式实现二叉树的先序、中序和后序的遍历打印
     * 给定一个二叉树的根结点root，请依次返回二叉树的先序，中序和后续遍历(二维数组的形式)。
     */
    public static int[][] convertLoop(TreeNode root) {
        int[][] result = new int[3][];
        if (root == null) return result;
        List<Integer> resultPre = preOrderLoop(root);
        List<Integer> resultMid = midOrderLoop(root);
        List<Integer> resultAfter = afterOrderLoop(root);

        result[0] = new int[resultPre.size()];
        result[1] = new int[resultMid.size()];
        result[2] = new int[resultAfter.size()];
        for (int i = 0; i < result[0].length; i++) {
            result[0][i] = resultPre.get(i);
            result[1][i] = resultMid.get(i);
            result[2][i] = resultAfter.get(i);
        }
        return result;
    }

    private static List<Integer> preOrderLoop(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode currentNode = root;

        while (currentNode != null || !stack.empty()) {
            while (currentNode != null) {
                result.add(currentNode.val);
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if(!stack.empty()) {
                currentNode = stack.pop();
                currentNode = currentNode.right;
            }
        }

        return result;
    }

    private static List<Integer> midOrderLoop(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode currentNode = root;
        do {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if(!stack.empty()) {
                currentNode = stack.pop();
                result.add(currentNode.val);
                    currentNode = currentNode.right;
            }
        } while (!stack.empty() || currentNode != null);

        return result;
    }

    private static List<Integer> afterOrderLoop(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode preNode = null;
        while (!stack.empty()) {
            TreeNode currentNode = stack.peek();
            if(currentNode.left == null && currentNode.right == null
                    || preNode != null && (preNode == currentNode.left || preNode == currentNode.right)){
                result.add(currentNode.val);
                stack.pop();
                preNode = currentNode;
                continue;
            }

            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }

        return result;
    }

    /**
     * 请用递归方式实现二叉树的先序、中序和后序的遍历打印。
     * 给定一个二叉树的根结点root，请依次返回二叉树的先序，中序和后续遍历(二维数组的形式)。
     */
    public static int[][] convert(TreeNode root) {
        int[][] result = new int[3][];
        if (root == null) return result;
        List<Integer> resultPre = new ArrayList<Integer>();
        List<Integer> resultMid = new ArrayList<Integer>();
        List<Integer> resultAfter = new ArrayList<Integer>();
        preOrder(root, resultPre);
        midOrder(root, resultMid);
        afterOrder(root, resultAfter);
        result[0] = new int[resultPre.size()];
        result[1] = new int[resultMid.size()];
        result[2] = new int[resultAfter.size()];
        for (int i = 0; i < result[0].length; i++) {
            result[0][i] = resultPre.get(i);
            result[1][i] = resultMid.get(i);
            result[2][i] = resultAfter.get(i);
        }
        return result;
    }

    private static void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    private static void midOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        midOrder(root.left, result);
        result.add(root.val);
        midOrder(root.right, result);
    }

    private static void afterOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        afterOrder(root.left, result);
        afterOrder(root.right, result);
        result.add(root.val);
    }
}
