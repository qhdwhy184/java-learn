package algorithm.amz;

import org.junit.Assert;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://aonecode.com/amazon-online-assessment-oa2-connect-ropes
 *
 * Given n ropes of different lengths, you need to connect these ropes into one rope. You can connect only 2 ropes at
 * a time. The cost required to connect 2 ropes is equal to sum of their lengths. The length of this connected rope is
 * also equal to the sum of their lengths. This process is repeated until n ropes are connected into a single rope. Find
 * the min possible cost required to connect all ropes.
 *
 * Input
 * ropes, an int arrary representing the rope length.
 *
 * Output
 * Return the min possible cost required to connect all ropes.
 *
 * Examples 1
 * Input:
 * ropes = [8, 4, 6, 12]
 *
 * Output:
 * 58
 *
 * Explaination:
 * Explanation: The optimal way to connect ropes is as follows
 * 1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
 * 2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
 * 3. Connect the ropes of length 18 and 12 (cost is 30).
 * Total cost to connect the ropes is 10 + 18 + 30 = 58
 *
 * Examples 2
 * Input:
 * ropes = [20, 4, 8, 2]
 *
 * Output:
 * 54
 *
 * Examples 3
 * Input:
 * ropes = [1, 2, 5, 10, 35, 89]
 *
 * Output:
 * 224
 *
 * Examples 4
 * Input:
 * ropes = [2, 2, 3, 3]
 *
 * Output:
 * 20
 */
public class ConnectRopes {
    public int connectRopes(int[] ropes) {
        if(ropes.length == 0 || ropes.length == 1) {
            return 0;
        }

        if(ropes.length == 2) {
            return ropes[0] + ropes[1];
        }

        LinkedList<Integer> ropeLst = new LinkedList<>();
        for(int rope : ropes) {
            ropeLst.add(rope);
        }
        Collections.sort(ropeLst);

        int cost = 0;
        while(ropeLst.size() > 0) {
            if(ropeLst.size() == 1) {
                cost += ropeLst.get(0);
                break;
            }

            if(ropeLst.size() == 2) {
                cost += (ropeLst.get(0) + ropeLst.get(1));
                break;
            }

            int newRope = ropeLst.get(0) + ropeLst.get(1);
            cost += newRope;
            ropeLst.removeFirst();
            ropeLst.removeFirst();

            int newRopeIdx = 0;
            while(newRopeIdx < ropeLst.size() && ropeLst.get(newRopeIdx) <= newRope){
                ++newRopeIdx;
            }

            if(newRopeIdx < ropeLst.size()) {
                ropeLst.add(newRopeIdx, newRope);
            } else {
                ropeLst.addLast(newRope);
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] testCase1 = new int[]{8, 4, 6, 12};
        int[] testCase2 = new int[]{20, 4, 8, 2};
        int[] testCase3 = new int[]{1, 2, 5, 10, 35, 89};
        int[] testCase4 = new int[]{2, 2, 3, 3};

        System.out.println("testCase1:" + new ConnectRopes().connectRopes(testCase1));
        Assert.assertEquals(58, new ConnectRopes().connectRopes(testCase1));
        System.out.println("testCase2:" + new ConnectRopes().connectRopes(testCase2));
        Assert.assertEquals(54, new ConnectRopes().connectRopes(testCase2));
        System.out.println("testCase3:" + new ConnectRopes().connectRopes(testCase3));
        Assert.assertEquals(224, new ConnectRopes().connectRopes(testCase3));
        System.out.println("testCase4:" + new ConnectRopes().connectRopes(testCase4));
        Assert.assertEquals(20, new ConnectRopes().connectRopes(testCase4));
    }
}
