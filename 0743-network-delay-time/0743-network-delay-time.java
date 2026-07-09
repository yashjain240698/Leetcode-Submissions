class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] ele : times) {
            List<int[]> tmp = adj.getOrDefault(ele[0], new ArrayList<>());
            tmp.add(new int[] { ele[1], ele[2] });
            adj.put(ele[0], tmp);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        pq.add(new int[] { k, 0 });

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            List<int[]> l = adj.getOrDefault(temp[0], new ArrayList<>());
            for (int i = 0; i < l.size(); i++) {
                if (temp[1] + l.get(i)[1] < dist[l.get(i)[0]]) {
                    dist[l.get(i)[0]] = temp[1] + l.get(i)[1];
                    pq.offer(new int[] { l.get(i)[0], dist[l.get(i)[0]] });
                }
            }
        }
        int mAns = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE){
                //System.out.println(dist[i] + " " + i);
                return -1;
            }
            mAns = Math.max(mAns, dist[i]);
        }
        return mAns;
    }
}