package algorithm.amz;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * K Nearest Post Offices
 *
 * Find the k post offices located closest to you, given your location and a list of locations of
 * all post offices available.
 *
 * Locations are given in 2D coordinates in [X, Y], where X and Y are integers.
 * Euclidean distance is applied to find the distance between you and a post office.
 * Assume your location is [m, n] and the location of a post office is [p, q], the Euclidean distance
 * between the office and you is SquareRoot((m - p) * (m - p) + (n - q) * (n - q)).
 * K is a positive integer much smaller than the given number of post offices. from aonecode.com
 *
 * e.g.
 * Input
 * you: [0, 0]
 * post_offices: [[-16, 5], [-1, 2], [4, 3], [10, -2], [0, 3], [-5, -9]]
 * k = 3
 *
 * Output from aonecode.com
 * [[-1, 2], [0, 3], [4, 3]]
 */
public class KNearestPostOffices {


    public int[][] solution(int[] youInput, int[][] postOfficeInput, int k) {
        int[][] res = new int[k][2];

        if(k == 0) {
            return res;
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>(k);
        Pair you = new Pair(youInput[0], youInput[1]);
        List<Pair> postOffices = new ArrayList<>(postOfficeInput.length);
        for(int[] oi : postOfficeInput) {
            Pair o = new Pair(oi[0], oi[1]);
            postOffices.add(o);
        }

        for(Pair o : postOffices) {
            o.distance = (you.x - o.x) * (you.x - o.x) + (you.y - o.y) * (you.y - o.y);
            if(queue.size() < k) {
                queue.offer(o);
            }

            if(queue.size() == k) {
                if(queue.peek().distance > o.distance) {
                    queue.poll();
                    queue.offer(o);
                }
            }
        }

        int i = 0;
        for(Pair o : queue) {
            res[i][0] = o.x;
            res[i][1] = o.y;
            ++i;
        }

        return res;
    }

    static class Pair implements Comparable<Pair>{
        int x;
        int y;
        long distance;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return (-1) * Long.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) {
        int[] case1_you = new int[]{0,0};
        int[][] case1_postOfficeInput = new int[][]{{-16, 5}, {-1, 2}, {4, 3}, {10, -2}, {0, 3}, {-5, -9}};
        int case1_k = 3;
        System.out.println(
                "case1:" + new Gson().toJson(new KNearestPostOffices().solution(case1_you, case1_postOfficeInput, case1_k)));
    }
}
