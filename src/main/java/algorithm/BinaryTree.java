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
     * 首先我们介绍二叉树先序序列化的方式，假设序列化的结果字符串为str，初始时str等于空字符串。先序遍历二叉树，如果遇到空节点，就在str的末尾加上“#!”，“#”表示这个节点为空，节点值不存在，当然你也可以用其他的特殊字符，“!”表示一个值的结束。如果遇到不为空的节点，假设节点值为3，就在str的末尾加上“3!”。现在请你实现树的先序序列化。
     * 给定树的根结点root，请返回二叉树序列化后的字符串。
     */
    public static String toString(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        firstOrderSerialize(root, sb);
        return sb.toString();
    }

    private static void firstOrderSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#!");
            return;
        }
        sb.append(node.val).append("!");
        firstOrderSerialize(node.left, sb);
        firstOrderSerialize(node.right, sb);
    }


    /**
     * 有一棵二叉树，请设计一个算法，按照层次打印这棵二叉树。
     * 给定二叉树的根结点root，请返回打印结果，结果按照每一层一个数组进行储存，
     * 所有数组的顺序按照层数从上往下，且每一层的数组内元素按照从左往右排列。保证结点数小于等于500。
     */
    public static int[][] printTree(TreeNode root) {
        List<List<Integer>> listResult = new ArrayList<List<Integer>>();
        if (root == null) return convertToResult(listResult);
        printNodeWithDepthAndIndex(root, 0, listResult);
        return convertToResult(listResult);
    }

    private static void printNodeWithDepthAndIndex(TreeNode node, int depth,
                                                   List<List<Integer>> resultRow){
        if(node == null) return;
        if(resultRow.size() < depth + 1) {
            resultRow.add(new ArrayList<Integer>());
        }
        resultRow.get(depth).add(node.val);
        if (node.left != null) {
            printNodeWithDepthAndIndex(node.left, depth + 1, resultRow);
            printNodeWithDepthAndIndex(node.right, depth + 1, resultRow);
        } else {
            printNodeWithDepthAndIndex(node.right, depth + 1, resultRow);
        }
    }

    private static int[][] convertToResult(List<List<Integer>> listResult){
        int firstDimension = listResult.size();
        int[][] result = new int [firstDimension][];
        for(int i = 0; i < firstDimension; i++) {
            List<Integer> resultRow = listResult.get(i);
            result[i] = new int[resultRow.size()];
            for(int j=0; j < resultRow.size(); j++) {
                result[i][j] = resultRow.get(j);
            }
        }
        return result;
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
