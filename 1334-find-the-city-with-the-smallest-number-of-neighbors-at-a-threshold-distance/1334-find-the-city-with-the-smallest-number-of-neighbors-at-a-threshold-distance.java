class Solution {
    PriorityQueue<Pair> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
    //Set<Integer> st = new HashSet<>();

    public void helper(int idx, Map<Integer, List<Pair>> mp, int distanceThreshold, int[] dist) {
        //st = new HashSet<>();
        q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        q.offer(new Pair(idx, 0));
        //st.add(idx);
        //int th = distanceThreshold;
        //List<Integer> tmp;
        while (!q.isEmpty()) {
            Pair k = q.poll();
            for (int i = 0; i < mp.getOrDefault(k.node, new ArrayList<>()).size(); i++) {
                if (dist[mp.get(k.node).get(i).node] < mp.get(k.node).get(i).dist + k.dist) {
                    continue;
                }

                if (distanceThreshold >= k.dist + mp.get(k.node).get(i).dist) {
                    dist[mp.get(k.node).get(i).node] = k.dist + mp.get(k.node).get(i).dist;
                    q.add(new Pair(mp.get(k.node).get(i).node, dist[mp.get(k.node).get(i).node]));
                }

            }
        }
        int cnt = 0;
        for (int i : dist) {
            if (i != Integer.MAX_VALUE)
                cnt++;
        }
        ans.put(idx, cnt);
    }

    Map<Integer, Integer> ans = new HashMap<>();

    class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //int[][] dp = new int[n][n];
        Map<Integer, List<Pair>> mp = new HashMap<>();
        List<Pair> temp;
        for (int i = 0; i < edges.length; i++) {
            temp = mp.getOrDefault(edges[i][0], new ArrayList<>());
            temp.add(new Pair(edges[i][1], edges[i][2]));
            mp.put(edges[i][0], temp);
            temp = mp.getOrDefault(edges[i][1], new ArrayList<>());
            temp.add(new Pair(edges[i][0], edges[i][2]));
            mp.put(edges[i][1], temp);
        }
        int sz = 10000000;
        int finalans = -1;
        int minVal = Integer.MAX_VALUE;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            ans.put(i, 0);
            dist[i] = 0;
            helper(i, mp, distanceThreshold, dist);
            minVal = Math.min(minVal, ans.get(i));
        }
        System.out.println("min : " + minVal);
        for (int i = 0; i < n; i++) {
            // System.out.println("i : " + i + " size : " + ans.get(i).size());
            // for (Integer k : ans.get(i)) {
            //     System.out.print(k + " ");
            // }
            if (ans.get(i) == minVal) {
                finalans = Math.max(finalans, i);
            }
        }
        return finalans;
    }
}