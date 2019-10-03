package algorithm.Codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. 1 <= N <= 26
 * 2. S contains descriptions of rectangular ship of area not greater than 4 cells
 * 3. ships do not overlap
 * 4. each map cell can appear in string T at most once
 * 5. S & T contains only valid positions in specific format
 */
public class HittedShipSunkShip {
    private static final String ENGLISH_COMMA = ",";
    private static final String SPACE = " ";
    class Ship {
        Set<String> body;
        boolean hitted;
        boolean sunk;
        Ship(){
            body = new HashSet<>();
            hitted = false;
            sunk = false;
        }

    }
    public String solution(int N , String S, String T) {
        List<Ship> ships = new ArrayList<>();
        String[] shipPositions = S.split(ENGLISH_COMMA);
        for(String position : shipPositions) {
            Ship ship = new Ship();
            String[] coor = position.split(SPACE);
            int  leftTopY = Integer.parseInt(coor[0].substring(0,1));
            char  leftTopX = coor[0].substring(1,2).charAt(0);
            int  rightDownY = Integer.parseInt(coor[1].substring(0,1));
            char  rightDownX = coor[1].substring(1,2).charAt(0);
            for(int i = leftTopY; i <= rightDownY; i++) {
                for( char j = leftTopX; j <= rightDownX; j++) {
                    ship.body.add(i+""+j);
                }
            }
            ships.add(ship);
        }

        String[] hits = T.split(ENGLISH_COMMA);
        for(String hit : hits) {
            for(Ship ship : ships) {
                if(ship.body.contains(hit)) {
                    ship.hitted = true;
                    ship.body.remove(hit);
                    if(ship.body.isEmpty()) {
                        ship.sunk = true;
                    }
                }
            }
        }

        int sunk = 0;
        int hitted = 0;
        for(Ship ship : ships) {
            if(ship.sunk) {
                ++sunk;
            } else if (ship.hitted) {
                ++hitted;
            }
        }

        return sunk + "," + hitted;
    }

    public static void main(String[] args) {
        System.out.println("12" + new HittedShipSunkShip().solution(12, "1A 2A", "12A"));
        System.out.println("3" + new HittedShipSunkShip().solution(3, "1A 1B, 2C 2C", "1B"));
        System.out.println("4" + new HittedShipSunkShip().solution(4, "1B 2C, 2D 4D", "2B 2D 3D 4D 4A"));
    }
}
