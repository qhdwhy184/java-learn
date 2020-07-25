package algorithm.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CriticalConnectionsinANetwork {
    public List<Integer> criticalConnections(int numRouters, List<List<Integer>> links) {
        System.out.println();
        int[] seen = new int[numRouters];
        int[] low = new int[numRouters];
        Arrays.fill(seen, -1);
        List<Integer>[] graph = new ArrayList[numRouters];

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < numRouters; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        int len = links.size();
        // graph
        for (int i = 0; i < len; i++) {
            // p -> q
            int p = links.get(i).get(0);
            int q = links.get(i).get(1);
            graph[p].add(q);
            graph[q].add(p);
        }

        // not seen, dfs
        for (int i = 0; i < numRouters; i++) {
            if (seen[i] == -1) {
                dfs(i, low, seen, graph,0,result);
            }
        }
        Collections.sort(result);
        return result;
    }
    int time = 0;
    private void dfs(int u, int[] low, int[] seen, List<Integer>[] graph, int node, List<Integer> result) {
        seen[u] = low[u] = ++time;
        for (int j = 0; j < graph[u].size(); j++) {
            int v = graph[u].get(j);
            if (v == node) {
                continue;
            }
            if (seen[v] == -1) {
                dfs(v, low, seen, graph, u,result);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > seen[u]) {
                    int index = graph[v].size() > graph[u].size()? v:u;
                    result.add(index);
                }
            }
            else {
                low[u] = Math.min(low[u], seen[v]);
            }
        }
    }
}
