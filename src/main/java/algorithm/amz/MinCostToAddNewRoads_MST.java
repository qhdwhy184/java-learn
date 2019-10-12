package algorithm.amz;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Min Cost To Add New Roads
 * There are N cities numbered from 1 to N.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2together. (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together. The cost is the sum of the connection costs used. If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 *
 * Example 2:
 *
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 * Note:
 *
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 */
public class MinCostToAddNewRoads_MST {
    private int findGroupNo(int currentCityNo, int[] cityGroups){
        return currentCityNo == cityGroups[currentCityNo] ?
                cityGroups[currentCityNo] :
                (cityGroups[currentCityNo] = findGroupNo(cityGroups[currentCityNo], cityGroups));
    }

    public int solution(int N, int[][] connections){
        int cost = 0;

        int[] cityGroups = new int[N+1];
        for(int i = 1; i <= N; i++) {
            cityGroups[i] = i;
        }

        List<List<Integer>> connectionLst = new LinkedList<>();
        for(int i = 0; i < connections.length; i++) {
            List<Integer> connection = new ArrayList<>(3);
            for(int j = 0; j < connections[i].length; j++) {
                connection.add(connections[i][j]);
            }
            connectionLst.add(connection);
        }

        Collections.sort(connectionLst, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2) - o2.get(2);
            }
        });

        for(List<Integer> connection : connectionLst) {
            int x = findGroupNo(connection.get(0), cityGroups);
            int y = findGroupNo(connection.get(1), cityGroups);
            if (x != y) {
                cost += connection.get(2);
                cityGroups[x] = y;
            }
        }

        int finalGroup = cityGroups[1];
        for(int i = 2; i <= N; i++) {
            if(finalGroup != cityGroups[i]) {
                return -1;
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        int[][] testCase1 = new int[][]{
                {1,2,5},
                {1,3,6},
                {2,3,1}};
        int[][] testCase2 = new int[][]{
                {1,2,3},
                {3,4,4}};
        int[][] testCase3 = new int[][]{
                {1,2,5},
                {1,3,4},
                {2,3,3},
                {2,4,2},
                {3,5,1},
                {4,5,6}}; // N = 5, target = 10
        int[][] testCase4 = new int[][]{
                {1,3,4},
                {2,4,2},
                {3,5,1}}; // N = 5, target = -1
//        char[][] testCase3 = new char[][]{
//                {'O', 'O', 'O', 'O'},
//                {'D', 'O', 'D', 'O'},
//                {'O', 'O', 'O', 'X'},
//                {'O', 'D', 'D', 'O'}};
//        char[][] testCase4 = new char[][]{
//                {'O', 'O', 'O', 'O'},
//                {'D', 'O', 'D', 'O'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'D', 'D', 'X'}};
//        char[][] testCase5 = new char[][]{
//                {'O', 'O', 'O', 'O'},
//                {'D', 'O', 'D', 'D'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'D', 'D', 'X'}};
//        char[][] testCase6 = new char[][]{
//                {'O', 'O', 'O', 'O'},
//                {'D', 'O', 'D', 'D'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'D', 'X', 'O'}};
//        char[][] testCase7 = new char[][]{
//                {'O', 'O', 'O', 'X'},
//                {'D', 'O', 'D', 'D'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'D', 'D', 'O'}};
//        char[][] testCase8 = new char[][]{
//                {'O', 'O', 'X', 'D'},
//                {'D', 'O', 'D', 'D'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'D', 'D', 'O'}};
//        char[][] testCase9 = new char[][]{
//                {'O', 'O', 'O', 'D'},
//                {'D', 'O', 'D', 'D'},
//                {'X', 'O', 'O', 'O'},
//                {'O', 'D', 'D', 'O'}};
//        char[][] testCase10 = new char[][]{
//                {'O', 'O', 'O', 'D'},
//                {'D', 'O', 'D', 'D'},
//                {'O', 'X', 'O', 'O'},
//                {'O', 'D', 'D', 'O'}};
        System.out.println("testCase1:" + new MinCostToAddNewRoads_MST().solution(3, testCase1));
        Assert.assertEquals(6, new MinCostToAddNewRoads_MST().solution(3, testCase1));
        System.out.println("testCase2:" + new MinCostToAddNewRoads_MST().solution(4, testCase2));
        Assert.assertEquals(-1, new MinCostToAddNewRoads_MST().solution(4, testCase2));
        System.out.println("testCase3:" + new MinCostToAddNewRoads_MST().solution(5, testCase3));
        Assert.assertEquals(10, new MinCostToAddNewRoads_MST().solution(5, testCase3));
        System.out.println("testCase4:" + new MinCostToAddNewRoads_MST().solution(5, testCase4));
        Assert.assertEquals(-1, new MinCostToAddNewRoads_MST().solution(5, testCase4));
//        System.out.println("testCase3:" + new TreasureIsland().minRouts(testCase3));
//        Assert.assertEquals(5, new TreasureIsland().minRouts(testCase3));
//        System.out.println("testCase4:" + new TreasureIsland().minRouts(testCase4));
//        Assert.assertEquals(6, new TreasureIsland().minRouts(testCase4));
//        System.out.println("testCase5:" + new TreasureIsland().minRouts(testCase5));
//        Assert.assertEquals(6, new TreasureIsland().minRouts(testCase5));
//        System.out.println("testCase6:" + new TreasureIsland().minRouts(testCase6));
//        Assert.assertEquals(5, new TreasureIsland().minRouts(testCase6));
//        System.out.println("testCase7:" + new TreasureIsland().minRouts(testCase7));
//        Assert.assertEquals(3, new TreasureIsland().minRouts(testCase7));
//        System.out.println("testCase8:" + new TreasureIsland().minRouts(testCase8));
//        Assert.assertEquals(2, new TreasureIsland().minRouts(testCase8));
//        System.out.println("testCase9:" + new TreasureIsland().minRouts(testCase9));
//        Assert.assertEquals(4, new TreasureIsland().minRouts(testCase9));
//        System.out.println("testCase10:" + new TreasureIsland().minRouts(testCase10));
//        Assert.assertEquals(3, new TreasureIsland().minRouts(testCase10));
    }
}
