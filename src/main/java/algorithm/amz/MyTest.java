package algorithm.amz;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.HashSet;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Objects;
    import java.util.Queue;
    import java.util.Set;

public class MyTest {
    int numberAmazonTreasureTrucks(int rows, int column,
                                   List<List<Integer>> grid)
    {
        // WRITE YOUR CODE HERE
        int res = 0;

        Set<Pair> accessed = new HashSet<>();

        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < column; j++) {
                int val = grid.get(i).get(j);
                if(val == 0) {
                    continue;
                }

                Pair cur = new Pair(i,j);
                if(accessed.contains(cur)) {
                   continue;
                }

                if(val == 1) {
                    ++ res;
                    accessed.add(cur);
                    markAllRelative(cur, rows, column, grid, accessed);
                }
            }
        }
        return res;
    }

    private void markAllRelative(Pair start, int rows, int column, List<List<Integer>> grid, Set<Pair> accessed) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            Pair cur = queue.poll();

            Pair left = new Pair(cur.x - 1, cur.y);
            if(left.x >= 0
                    && grid.get(left.x).get(left.y) == 1 && !accessed.contains(left)) {
                queue.add(left);
                accessed.add(left);
            }

            Pair right = new Pair(cur.x + 1, cur.y);
            if(right.x < rows
                    && grid.get(right.x).get(right.y) == 1 && !accessed.contains(right)) {
                queue.add(right);
                accessed.add(right);
            }

            Pair up = new Pair(cur.x, cur.y - 1);
            if(up.y >= 0
                    && grid.get(up.x).get(up.y) == 1 && !accessed.contains(up)) {
                queue.add(up);
                accessed.add(up);
            }

            Pair down = new Pair(cur.x, cur.y + 1);
            if(down.y < column
                    && grid.get(down.x).get(down.y) == 1 && !accessed.contains(down)) {
                queue.add(down);
                accessed.add(down);
            }
        }
    }

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(1,1,0,0));
        grid.add(Arrays.asList(0,0,1,0));
        grid.add(Arrays.asList(0,0,0,0));
        grid.add(Arrays.asList(1,0,1,1));
        grid.add(Arrays.asList(1,1,1,1));
        System.out.println(new MyTest().numberAmazonTreasureTrucks(5, 4, grid));
    }
}
