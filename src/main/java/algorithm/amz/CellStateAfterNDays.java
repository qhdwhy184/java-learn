package algorithm.amz;

import com.google.gson.Gson;

/**
 * Eight houses, represented as cells, are arranged in a straight line. Each day every cell competes with its adjacent
 * cells (neighbors). An integer value 1 represents an active cell and a value of 0 represents an inactive cell. If the
 * neighbors on both the sides of a cell are either active or inactive, the cell becomes inactive on the next day;
 * otherwise the cell becomes active. The two cells on each end have a single adjacent cell, so assume that the
 * unoccupied space on the opposite side is an inactive cell. Even after updating the cell state, consider its previous
 * state when updating the state of other cells. The state information of all cells should be updated simultaneously.
 *
 * Write an algorithm to output the state of the cells after the given number of days.
 *
 * Input
 * The input to the function/method consists of two arguments:
 * states, a list of integers representing the current state of cells;
 * days, an integer representing the number of days.
 *
 * Output
 * Return a list of integers representing the state of the cells after the given number of days.
 *
 * Examples 1
 * Input:
 * [1, 0, 0, 0, 0, 1, 0, 0], 1
 *
 * Output:
 * 0 1 0 0 1 0 1 0
 *
 * Examples 2
 * Input:
 * [1, 1, 1, 0, 1, 1, 1, 1], 2
 *
 * Output:
 * 0 0 0 0 0 1 1 0
 */
public class CellStateAfterNDays {
    public static int[] solution(int[] cells, int days) {
        int[] res = new int[8];
        while(days-- > 0) {
            for(int i = 0; i < cells.length; i++) {
                if(i == 0) {
                    if (cells[i + 1] == 0) {
                        res[i] = 0;
                    } else {
                        res[i] = 1;
                    }
                    continue;
                }

                if(i == cells.length - 1) {
                    if (cells[i - 1] == 0) {
                        res[i] = 0;
                    } else {
                        res[i] = 1;
                    }
                    continue;
                }

                if(cells[i - 1] != cells[i + 1]) {
                    res[i] = 1;
                } else {
                    res[i] = 0;
                }
            }

            for(int i = 0; i < res.length; i++) {
                cells[i] = res[i];
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] case1c = {1, 0, 0, 0, 0, 1, 0, 0};
        int case1d = 1;
        System.out.println(new Gson().toJson(CellStateAfterNDays.solution(case1c, case1d)));

        int[] case2c = {1, 1, 1, 0, 1, 1, 1, 1};
        int case2d = 2;
        System.out.println(new Gson().toJson(CellStateAfterNDays.solution(case2c, case2d)));

    }

}
