package algorithm.amz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

/**
 *
 * https://aonecode.com/amazon-online-assessment-questions#s
 *
 * Given an N-ary tree, find the subtree with maximum average. Return the root of the subtree.
 * A subtree of a tree is any node of that tree plus all its descendants. The average value of a subtree is the
 * sum of its values, divided by the number of nodes.
 *
 * Example 1:
 * Input:
 *                   1
 *
 *           /       |       \
 *
 *        -5        13         4
 *       /   \     /   \
 *      1    2    4   -2
 *
 * Output: 13
 * Explanation: For the node with value = 13 we have an average of (13 + 4 + -2) / 3 = 5 which is the maximum.
 *
 * Example 2:
 *
 * Input:
 *                    1
 *              /    /  \     \
 *             -5   21   5    -1
 *
 * Output: 21
 * Explanation:
 * For the node with value = 1 we have an average of (- 5 + 21 + 5 - 1) / 5 = 4.
 * For the node with value = -5 we have an average of (-5 / 1) = -5.
 * For the node with value = 21 we have an average of (21 / 1) = 21.
 * For the node with value = 5 we have an average of (5 / 1) = 5.
 * For the node with value = -1 we have an average of (-1 / 1) = -1.
 * So the subtree of 21 is the maximum.
 */
public class SubtreeWithMaximumAverage {
    public int solution(Node root) {
        Res r = cal(root);
        return r.maxRootVal;
    }

    private Res cal(Node node) {
        if(node == null) {
            return new Res();
        }

        double maxAvg = Double.MIN_VALUE;
        int maxRootVal = Integer.MIN_VALUE;
        Res currentNodeRes = new Res(1, node.val);
        for(Node c : node.childs) {
            Res r = cal(c);
            if(maxAvg < r.maxAvg) {
                maxAvg = r.maxAvg;
                maxRootVal = r.maxRootVal;
            }
            currentNodeRes.add(r);
        }

        double currentNodeAvg = currentNodeRes.avg();
        if(maxAvg < currentNodeAvg) {
            currentNodeRes.maxAvg = currentNodeAvg;
            currentNodeRes.maxRootVal = node.val;
        } else {
            currentNodeRes.maxAvg = maxAvg;
            currentNodeRes.maxRootVal = maxRootVal;
        }

        return currentNodeRes;
    }

    class Res{
        int count;
        int sum;
        int maxRootVal;
        double maxAvg;
        public Res() {
            this.count = 0;
            this.sum = 0;
        }

        public Res(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }

        public Res add(Res another) {
            return new Res(this.count + another.count, this.sum + another.sum);
        }

        public double avg() {
            BigDecimal bdCount = new BigDecimal(Integer.toString(count));
            BigDecimal bdSum = new BigDecimal(Integer.toString(sum));
            return bdSum.divide(bdCount, HALF_UP).doubleValue();
        }
    }

    static class Node {
        List<Node> childs = new ArrayList<>();
        int val;
        public Node(int val){
            this.val = val;
        }

        public Node(int val, List<Node> childs){
            this.val = val;
            this.childs = childs;
        }
    }

    public static void main(String[] args) {
        List<Node> n1_n2 = new ArrayList<>(Arrays.asList(new Node(1), new Node(2)));
        Node n_5 = new Node(-5);
        n_5.childs = n1_n2;
        List<Node> n4_n_2 = new ArrayList<>(Arrays.asList(new Node(4), new Node(-2)));
        Node n13 = new Node(13);
        n13.childs = n4_n_2;
        Node n4 = new Node(4);
        Node case1root = new Node(1);

        List<Node> n_5_n13_n4 = new ArrayList<>(Arrays.asList(n_5, n13, n4));
        case1root.childs = n_5_n13_n4;


        Node case2root = new Node(1);
        case2root.childs = new ArrayList<>(Arrays.asList(new Node(-5), new Node(21),
                new Node(5), new Node(-1)));

        System.out.println("case1:" + new SubtreeWithMaximumAverage().solution(case1root));
        System.out.println("case2:" + new SubtreeWithMaximumAverage().solution(case2root));
    }
}
