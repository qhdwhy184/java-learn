package algorithm.amz;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Min Cost to Repair Edges (Minimum Spanning Tree II)
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been
 * broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes
 * are once again accessible from each other.
 *
 * Input:
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes connected by an edge.
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes between
 * which an edge is currently broken and the cost of repearing that edge, respectively
 * (e.g. [1, 2, 12] means to repear an edge between nodes 1 and 2, the cost would be 12).
 *
 * Example 1:
 * Input:
 * n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]],
 * edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
 * Output: 20
 * Explanation:
 * There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
 * We can connect these components into a single component by repearing the edges between nodes 1
 * and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
 *
 * Example 2:
 * Input:
 * n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]],
 * edgesToRepair = [[1, 6, 410], [2, 4, 800]]
 * Output: 410
 *
 * Example 3:
 * Input:
 * n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]],
 * edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
 * Output: 79
 */
public class MinCosttoRepairEdges_MST {
    public int solution(int n, List<List<Integer>> edges, List<List<Integer>> edgesToRepair) {
        Set<Pair> toRepair = new HashSet<>();
        for(List<Integer> brokenEdge : edgesToRepair) {
            toRepair.add(new Pair(brokenEdge.get(0), brokenEdge.get(1)));
        }

        int[] nodeGroup = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            nodeGroup[i] = i;
        }

        for(List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            if(!toRepair.contains(new Pair(start, end))
                    && !toRepair.contains(new Pair(end, start))) {
                nodeGroup[start] = end;
            }
        }

        Collections.sort(edgesToRepair, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2) - o2.get(2);
            }
        });

        int cost = 0;
        for(List<Integer> edgeToRepair : edgesToRepair) {
            int x = findGroupNo(edgeToRepair.get(0), nodeGroup);
            int y = findGroupNo(edgeToRepair.get(1), nodeGroup);
            if(x != y) {
                cost += edgeToRepair.get(2);
                nodeGroup[x] = y;
            }
        }
        return cost;
    }

    private int findGroupNo(int currentNode, int[] nodeGroup) {
        return currentNode == nodeGroup[currentNode] ?
                currentNode :
                (nodeGroup[currentNode] = findGroupNo(nodeGroup[currentNode], nodeGroup));
    }

    static class Pair {
        int start;
        int end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return start == pair.start &&
                    end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public static void main(String[] args) {
        int test1_N = 5, expected = 20;
        List<List<Integer>> test1_edges = new ArrayList<>();
        test1_edges.add(Arrays.asList(1,2));
        test1_edges.add(Arrays.asList(2,3));
        test1_edges.add(Arrays.asList(3,4));
        test1_edges.add(Arrays.asList(4,5));
        test1_edges.add(Arrays.asList(1,5));
        List<List<Integer>> test1_edges_to_repair = new ArrayList<>();
        test1_edges_to_repair.add(Arrays.asList(1,2, 12));
        test1_edges_to_repair.add(Arrays.asList(3,4, 30));
        test1_edges_to_repair.add(Arrays.asList(1,5, 8));

        System.out.println("testCase1:" + new MinCosttoRepairEdges_MST().solution(test1_N, test1_edges, test1_edges_to_repair));
        Assert.assertEquals(expected, new MinCosttoRepairEdges_MST().solution(test1_N, test1_edges, test1_edges_to_repair));

        int test2_N = 6, test2_expected = 410;
        List<List<Integer>> test2_edges = new ArrayList<>();
        test2_edges.add(Arrays.asList(1,2));
        test2_edges.add(Arrays.asList(2,3));
        test2_edges.add(Arrays.asList(4,5));
        test2_edges.add(Arrays.asList(3,5));
        test2_edges.add(Arrays.asList(1,6));
        test2_edges.add(Arrays.asList(2,4));
        List<List<Integer>> test2_edges_to_repair = new ArrayList<>();
        test2_edges_to_repair.add(Arrays.asList(1,6, 410));
        test2_edges_to_repair.add(Arrays.asList(2,4, 800));

        System.out.println("testCase2:" + new MinCosttoRepairEdges_MST().solution(test2_N, test2_edges, test2_edges_to_repair));
        Assert.assertEquals(test2_expected, new MinCosttoRepairEdges_MST().solution(test2_N, test2_edges, test2_edges_to_repair));

        int test3_N = 6, test3_expected = 79;
        List<List<Integer>> test3_edges = new ArrayList<>();
        test3_edges.add(Arrays.asList(1,2));
        test3_edges.add(Arrays.asList(2,3));
        test3_edges.add(Arrays.asList(4,5));
        test3_edges.add(Arrays.asList(5,6));
        test3_edges.add(Arrays.asList(1,5));
        test3_edges.add(Arrays.asList(2,4));
        test3_edges.add(Arrays.asList(3,4));


        List<List<Integer>> test3_edges_to_repair = new ArrayList<>();
        test3_edges_to_repair.add(Arrays.asList(1,5, 110));
        test3_edges_to_repair.add(Arrays.asList(2,4, 84));
        test3_edges_to_repair.add(Arrays.asList(3,4, 79));

        System.out.println("testCase3:" + new MinCosttoRepairEdges_MST().solution(test3_N, test3_edges, test3_edges_to_repair));
        Assert.assertEquals(test3_expected, new MinCosttoRepairEdges_MST().solution(test3_N, test3_edges, test3_edges_to_repair));


    }
}
