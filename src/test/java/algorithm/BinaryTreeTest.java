package algorithm;

import org.junit.Test;

/**
 * Created by wangyuanhui on 16/5/29.
 */
public class BinaryTreeTest {
    @Test
    public void checkTest(){
        boolean result = BinaryTree.check(mockData1());
        assert (result);

        result = BinaryTree.check(mockData2());
        assert (result);

        result = BinaryTree.check(mockData3());
        assert (!result);


        result = BinaryTree.check(mockData4());
        assert (!result);
    }
    @Test
    public void printTreeTest(){
        int[][] result = BinaryTree.printTree(mockData1());
        assert (result[0][0] == 0);

        result = BinaryTree.printTree(mockData2());
        assert (result[0][0] == 0);
        assert (result[1][0] == 1);
        assert (result[1][1] == 2);

        result = BinaryTree.printTree(mockData3());
        assert (result[0][0] == 0);
        assert (result[1][0] == 1);
        assert (result[2][0] == 2);


        result = BinaryTree.printTree(mockData4());
        assert (result[0][0] == 0);
        assert (result[1][0] == 1);
        assert (result[2][0] == 2);
    }


    @Test
    public void convertLoopTest(){

        int[][] result = BinaryTree.convertLoop(mockData1());
        assert (result[0][0] == 0);
        assert (result[1][0] == 0);
        assert (result[2][0] == 0);

        result = BinaryTree.convertLoop(mockData2());
        assert (result[0][0] == 0);
        assert (result[0][1] == 1);
        assert (result[0][2] == 2);
        assert (result[1][0] == 1);
        assert (result[1][1] == 0);
        assert (result[1][2] == 2);
        assert (result[2][0] == 1);
        assert (result[2][1] == 2);
        assert (result[2][2] == 0);

        result = BinaryTree.convertLoop(mockData3());
        assert (result[0][0] == 0);
        assert (result[0][1] == 1);
        assert (result[0][2] == 2);
        assert (result[1][0] == 2);
        assert (result[1][1] == 1);
        assert (result[1][2] == 0);
        assert (result[2][0] == 2);
        assert (result[2][1] == 1);
        assert (result[2][2] == 0);

        result = BinaryTree.convertLoop(mockData4());
        assert (result[0][0] == 0);
        assert (result[0][1] == 1);
        assert (result[0][2] == 2);
        assert (result[1][0] == 0);
        assert (result[1][1] == 1);
        assert (result[1][2] == 2);
        assert (result[2][0] == 2);
        assert (result[2][1] == 1);
        assert (result[2][2] == 0);
    }

    /**
     * 0
     * @return
     */
    private BinaryTree.TreeNode mockData1(){
        return new BinaryTree.TreeNode(0);
    }

    /**
     *  0
     * 1 2
     * @return
     */
    private BinaryTree.TreeNode mockData2(){
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(0);
        root.left = new BinaryTree.TreeNode(1);
        root.right = new BinaryTree.TreeNode(2);
        return root;
    }

    /**
     *   0
     *  1
     * 2
     * @return
     */
    private BinaryTree.TreeNode mockData3(){
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(0);
        root.left = new BinaryTree.TreeNode(1);
        root.left.left = new BinaryTree.TreeNode(2);
        return root;
    }

    /**
     * 0
     *  1
     *   2
     * @return
     */
    private BinaryTree.TreeNode mockData4(){
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(0);
        root.right = new BinaryTree.TreeNode(1);
        root.right.right = new BinaryTree.TreeNode(2);
        return root;
    }
}
