package algorithm.amz;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a map Map<String, List<String>> userMap, where the key is a username and the value is a
 * list of user's songs. Also given a map Map<String, List<String>> genreMap, where the key is a
 * genre and the value is a list of songs belonging to this genre. The task is to return a map
 * Map<String, List<String>>, where the key is a username and the value is a list of the user's
 * favorite genres. Favorite genre is a genre with the most song.
 *
 * Example :
 * Input:
 *
 * userMap = {
 *    "David": ["song1", "song2", "song3", "song4", "song8"],
 *    "Emma":  ["song5", "song6", "song7"]
 * },
 * genreMap = {
 *    "Rock":    ["song1", "song3"],
 *    "Dubstep": ["song7"],
 *    "Techno":  ["song2", "song4"],
 *    "Pop":     ["song5", "song6"],
 *    "Jazz":    ["song8", "song9"]
 * }
 * Output:
 * {
 *    "David": ["Rock", "Techno"],
 *    "Emma":  ["Pop"]
 * }
 * Explanation:
 * David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
 * Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
 */
public class FavoriteGenres {
    public Map<String, List<String>> solution(Map<String, List<String>> userMap,
                                              Map<String, List<String>> genreMap) {
        Map<String, String> song2genre = new HashMap<>();
        for(Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
            List<String> songs = entry.getValue();
            String genre = entry.getKey();
            for(String song: songs){
                song2genre.put(song, genre);
            }
        }

        Map<String, Map<String, Integer>> user2genre = new HashMap<>();

        for(Map.Entry<String, List<String>> entry : userMap.entrySet()) {
            String user = entry.getKey();
            Map<String, Integer> genre2Count = new HashMap<>();
            user2genre.put(user, genre2Count);

            List<String> songs = entry.getValue();
            for(String song : songs) {
                String genre = song2genre.get(song);
                if(!genre2Count.containsKey(genre)) {
                    genre2Count.put(genre, 0);
                }
                genre2Count.put(genre, genre2Count.get(genre) + 1);
            }
        }

        Map<String, List<String>> res = new HashMap<>();
        for(Map.Entry<String, Map<String, Integer>> entry : user2genre.entrySet()) {
            String user = entry.getKey();
            Map<String, Integer> genre2count = entry.getValue();
            Set<String> favoriteGenres = new HashSet<>();
            int curMax = 0;
            for(Map.Entry<String, Integer> genre2CountEntry : genre2count.entrySet()) {
                if(curMax < genre2CountEntry.getValue()) {
                    favoriteGenres.clear();
                    curMax = genre2CountEntry.getValue();
                    favoriteGenres.add(genre2CountEntry.getKey());
                    continue;
                }
                if(curMax == genre2CountEntry.getValue()) {
                    favoriteGenres.add(genre2CountEntry.getKey());
                }
            }
            res.put(user, new ArrayList<>(favoriteGenres));
        }
        return res;
    }

    public static void main(String[] args) {
        // expect: {David=[Rock, Techno], Emma=[Pop]}
        Map<String, List<String>> case1UserMap = new HashMap<>();
        case1UserMap.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        case1UserMap.put("Emma", Arrays.asList("song5", "song6", "song7"));
        Map<String, List<String>> case1GenreMap = new HashMap<>();
        case1GenreMap.put("Rock", Arrays.asList("song1", "song3"));
        case1GenreMap.put("Dubstep", Arrays.asList("song7"));
        case1GenreMap.put("Techno", Arrays.asList("song2", "song4"));
        case1GenreMap.put("Pop", Arrays.asList("song5", "song6"));
        case1GenreMap.put("Jazz", Arrays.asList("song8", "song9"));


        // expect: {David=[Rock, Techno, Jazz], Emma=[Pop], Abby=[Techno]}
        Map<String, List<String>> case2UserMap = new HashMap<>();
        case2UserMap.put("David", Arrays.asList("song1", "song4", "song8"));
        case2UserMap.put("Emma", Arrays.asList("song5", "song6", "song7"));
        case2UserMap.put("Abby", Arrays.asList("song2"));
        Map<String, List<String>> case2GenreMap = new HashMap<>();
        case2GenreMap.put("Rock", Arrays.asList("song1", "song3"));
        case2GenreMap.put("Dubstep", Arrays.asList("song7"));
        case2GenreMap.put("Techno", Arrays.asList("song2", "song4"));
        case2GenreMap.put("Pop", Arrays.asList("song5", "song6"));
        case2GenreMap.put("Jazz", Arrays.asList("song8", "song9"));

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
        System.out.println("testCase1:" + new FavoriteGenres().solution(case1UserMap, case1GenreMap));
        System.out.println("testCase2:" + new FavoriteGenres().solution(case2UserMap, case2GenreMap));
//        Assert.assertEquals(6, new FavoriteGenres().solution(case1UserMap, case1GenreMap));
//        System.out.println("testCase2:" + new MinCostToAddNewRoads_MST().solution(4, testCase2));
//        Assert.assertEquals(-1, new MinCostToAddNewRoads_MST().solution(4, testCase2));
//        System.out.println("testCase3:" + new MinCostToAddNewRoads_MST().solution(5, testCase3));
//        Assert.assertEquals(10, new MinCostToAddNewRoads_MST().solution(5, testCase3));
//        System.out.println("testCase4:" + new MinCostToAddNewRoads_MST().solution(5, testCase4));
//        Assert.assertEquals(-1, new MinCostToAddNewRoads_MST().solution(5, testCase4));
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
