package algorithm.amz;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged
 * rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to
 * find the treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must
 * start from the top-left corner of the map and can move one block up, down, left or right at a
 * time. The treasure island is marked as X in a block of the matrix. X will not be at the top-left
 * corner. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous
 * blocks. You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is
 * always safe. Output the minimum number of steps to get to the treasure.
 *
 * Example:
 *
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 * Solution
 * Java BFS: https://leetcode.com/playground/uQoVfEmr
 * Time complexity: O(r * c).
 * Space complexity: O(r * c).
 */

public class TreasureIsland {
    public int minRouts(char[][] input){

        int maxRow = input.length;
        int maxColumn = input[0].length;
        Point[][] matrix = new Point[input.length][input[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Point();
            }
        }
        Queue<Point> queue = new LinkedList<>();
        Point startPoint = new Point(0, 0, Color.GREY, 0);
        queue.add(new Point(0, 0, Color.GREY, 0));
        matrix[0][0] = startPoint;
        while(!queue.isEmpty()) {
            Point currPoint = queue.remove();
            if(input[currPoint.r][currPoint.c] == 'X') {
                return currPoint.step;
            }
            matrix[currPoint.r][currPoint.c].color = Color.BLACK;
            if(currPoint.r + 1 < maxRow
                    && (input[currPoint.r + 1][currPoint.c] == 'O' || input[currPoint.r + 1][currPoint.c] == 'X')
                    && matrix[currPoint.r + 1][currPoint.c].color == Color.WHITE) {
                queue.add(new Point(currPoint.r + 1, currPoint.c, Color.GREY, currPoint.step + 1));
                matrix[currPoint.r + 1][currPoint.c].color = Color.GREY;
            }
            // if() row -1
            if(currPoint.r - 1 >= 0
                    && (input[currPoint.r - 1][currPoint.c] == 'O' || input[currPoint.r - 1][currPoint.c] == 'X')
                    && matrix[currPoint.r - 1][currPoint.c].color == Color.WHITE) {
                queue.add(new Point(currPoint.r - 1, currPoint.c, Color.GREY, currPoint.step + 1));
                matrix[currPoint.r - 1][currPoint.c].color = Color.GREY;
            }

            // if() column + 1
            if(currPoint.c + 1 < maxColumn
                    && (input[currPoint.r][currPoint.c + 1] == 'O' || input[currPoint.r][currPoint.c + 1] == 'X')
                    && matrix[currPoint.r][currPoint.c + 1].color == Color.WHITE) {
                queue.add(new Point(currPoint.r, currPoint.c + 1, Color.GREY, currPoint.step + 1));
                matrix[currPoint.r][currPoint.c + 1].color = Color.GREY;
            }
            // if() column - 1
            if(currPoint.c - 1 >= 0
                    && (input[currPoint.r][currPoint.c - 1] == 'O' || input[currPoint.r][currPoint.c - 1] == 'X')
                    && matrix[currPoint.r][currPoint.c - 1].color == Color.WHITE) {
                queue.add(new Point(currPoint.r, currPoint.c - 1, Color.GREY, currPoint.step + 1));
                matrix[currPoint.r][currPoint.c - 1].color = Color.GREY;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        char[][] testCase1 = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}};
        char[][] testCase2 = new char[][]{
                {'O', 'O'},
                {'D', 'X'}};
        char[][] testCase3 = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'X'},
                {'O', 'D', 'D', 'O'}};
        char[][] testCase4 = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'D', 'X'}};
        char[][] testCase5 = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'D'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'D', 'X'}};
        char[][] testCase6 = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'D'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'X', 'O'}};
        char[][] testCase7 = new char[][]{
                {'O', 'O', 'O', 'X'},
                {'D', 'O', 'D', 'D'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'D', 'O'}};
        char[][] testCase8 = new char[][]{
                {'O', 'O', 'X', 'D'},
                {'D', 'O', 'D', 'D'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'D', 'O'}};
        char[][] testCase9 = new char[][]{
                {'O', 'O', 'O', 'D'},
                {'D', 'O', 'D', 'D'},
                {'X', 'O', 'O', 'O'},
                {'O', 'D', 'D', 'O'}};
        char[][] testCase10 = new char[][]{
                {'O', 'O', 'O', 'D'},
                {'D', 'O', 'D', 'D'},
                {'O', 'X', 'O', 'O'},
                {'O', 'D', 'D', 'O'}};
        System.out.println("testCase1:" + new TreasureIsland().minRouts(testCase1));
        Assert.assertEquals(5, new TreasureIsland().minRouts(testCase1));
        System.out.println("testCase2:" + new TreasureIsland().minRouts(testCase2));
        Assert.assertEquals(2, new TreasureIsland().minRouts(testCase2));
        System.out.println("testCase3:" + new TreasureIsland().minRouts(testCase3));
        Assert.assertEquals(5, new TreasureIsland().minRouts(testCase3));
        System.out.println("testCase4:" + new TreasureIsland().minRouts(testCase4));
        Assert.assertEquals(6, new TreasureIsland().minRouts(testCase4));
        System.out.println("testCase5:" + new TreasureIsland().minRouts(testCase5));
        Assert.assertEquals(6, new TreasureIsland().minRouts(testCase5));
        System.out.println("testCase6:" + new TreasureIsland().minRouts(testCase6));
        Assert.assertEquals(5, new TreasureIsland().minRouts(testCase6));
        System.out.println("testCase7:" + new TreasureIsland().minRouts(testCase7));
        Assert.assertEquals(3, new TreasureIsland().minRouts(testCase7));
        System.out.println("testCase8:" + new TreasureIsland().minRouts(testCase8));
        Assert.assertEquals(2, new TreasureIsland().minRouts(testCase8));
        System.out.println("testCase9:" + new TreasureIsland().minRouts(testCase9));
        Assert.assertEquals(4, new TreasureIsland().minRouts(testCase9));
        System.out.println("testCase10:" + new TreasureIsland().minRouts(testCase10));
        Assert.assertEquals(3, new TreasureIsland().minRouts(testCase10));
    }

}

class Point {
    int r;
    int c;
    Color color;
    int step;
    Point(int r, int c, Color color, int step) {
        this.r = r;
        this.c = c;
        this.color = color;
        this.step = step;
    }

    Point() {
        this.color = Color.WHITE;
    }
}

enum Color {
    WHITE, GREY, BLACK
}

