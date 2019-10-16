package algorithm.amz;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  Give a computer with total K memory space, and an array of foreground tasks and background tasks
 *  the computer need to do. Write an algorithm to find a pair of tasks from each array to maximize
 *  the memory usage. Notice the tasks could be done without origin order.
 *
 * Input
 * The input to the function/method consists of three arguments :
 * foregroundTask, an array representing the memory usage of the foreground tasks,
 * backgroundTask, an array representing the memory usage of the background tasks,
 * K, the total memory space of the computer.
 *
 * Output
 * Return a list of pairs of the task ids.
 *
 * Examples 1
 * Input:
 * foregroundTasks = [1, 7, 2, 4, 5, 6]
 * backgroundTasks = [3, 1, 2]
 * K = 6
 *
 * Output:
 * [(3, 2), (4, 1)]
 *
 * Explaination:
 * Here we have 5 foreground tasks: task 0 uses 1 memeory. task 1 uses 7 memeory. task 2 uses 2 memeory..
 * And 5 background tasks: task 0 uses 3 memeory. task 1 uses 1 memeory. task 2 uses 2 memeory..
 * We need to find two tasks with total memory usage sum <= K.
 * So we find the foreground task 3 and background task 2. Total memory usage is 6.
 * And the foreground task 4 and background task 1. Total memory usage is also 6.
 *
 * Examples 2
 * Input:
 * foregroundTasks = [1, 7, 2, 4, 5, 6]
 * backgroundTasks = [3, 1, 2]
 * K = 10
 *
 * Output:
 * [(1, 2))]
 *
 * Explaination:
 * Here we find the foreground task 1 and background task 2. Total memory usage is 7 + 2 = 9, which is < 10.
 */
public class OptimizeMemoryUsage {
    List<Pair> solution(int[] foreTasks, int[] backTasks, int k) {
//        int[][] res = new int[][2]{};
        List<Task> foreTaskLst = new LinkedList<>();
        for(int i = 0; i < foreTasks.length; i++) {
            foreTaskLst.add(new Task(i, foreTasks[i]));
        }

        List<Task> backTaskLst = new LinkedList<>();
        for(int i = 0; i < backTasks.length; i++) {
            backTaskLst.add(new Task(i, backTasks[i]));
        }

        Collections.sort(foreTaskLst);
        Collections.sort(backTaskLst);

        List<Pair> resPair = new ArrayList<>();
        int largestUsage = 0;
        for(int i = 0, j = backTaskLst.size() - 1; i < foreTaskLst.size() && j >= 0;) {
            int usage = foreTaskLst.get(i).mem + backTaskLst.get(j).mem;
            if(usage <= k) {

                if(largestUsage < usage) {
                    largestUsage = usage;
                    resPair.clear();
                    resPair.add(new Pair(foreTaskLst.get(i).id, backTaskLst.get(j).id));
                    if(usage == k) {
                        ++i;
                        --j;
                        continue;
                    }
                }

                if(largestUsage == usage) {
                    resPair.add(new Pair(foreTaskLst.get(i).id, backTaskLst.get(j).id));
                }

                ++i;
                continue;
            }

            if(usage > k) {
                -- j;
            }
        }

        return resPair;

    }

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Task implements Comparable<Task>{
        int id;
        int mem;
        Task(int id, int mem) {
            this.id = id;
            this.mem = mem;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.mem, o.mem);
        }
    }

    public static void main(String[] args) {
        int[] case1ForegroundTasks = {1, 7, 2, 4, 5, 6};
        int[] case2BackgroundTasks = {3, 1, 2};
        System.out.println(new Gson().toJson(new OptimizeMemoryUsage().solution(case1ForegroundTasks, case2BackgroundTasks, 6)));

        System.out.println(new Gson().toJson(new OptimizeMemoryUsage().solution(case1ForegroundTasks, case2BackgroundTasks, 10)));
    }

}
