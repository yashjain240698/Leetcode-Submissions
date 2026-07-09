class Solution {
    class Pair {
        int node;
        int price;
        int stop;

        Pair(int node, int price, int stop) {
            this.node = node;
            this.price = price;
            this.stop = stop;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Pair>> adj = new HashMap<>();

        // for (int[] e : flights) {
        //     List<Pair> temp = adj.getOrDefault(e[0], new ArrayList<>());
        //     temp.add(new Pair(e[1], e[2], 0));
        //     adj.put(e[0], temp);
        // }

        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        // PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.price - b.price);
        ans[src] = 0;
        
        // pq.add(new Pair(src, 0, 0));

        // while(!pq.isEmpty()){
        //     Pair p = pq.poll();
        //      if(p.stop > k)
        //         continue;

        //     List<Pair> l = adj.getOrDefault(p.node, new ArrayList<>());
        //     for(Pair e: l){
        //        if(p.price + e.price < ans[e.node]){
        //         System.out.println(ans[e.node]);
        //         ans[e.node] = p.price + e.price;
        //         System.out.println(p.node + " " + e.node + " " + ans[e.node] + " " + p.stop);
        //         pq.offer(new Pair(e.node, ans[e.node], p.stop+1));
        //        }

        //     } 
        // }
        // return ans[dst] == Integer.MAX_VALUE ? -1 : ans[dst];

        int loop = k + 1;
        while (loop-- != 0) {
            int[] temp = new int[n];
            Arrays.fill(temp, Integer.MAX_VALUE);
            temp[src] = 0;
            for (int[] ele : flights) {
                if (ans[ele[0]] == Integer.MAX_VALUE) {
                    continue;
                }
                if (temp[ele[1]] > ans[ele[0]] + ele[2])
                    temp[ele[1]] = ans[ele[0]] + ele[2];
            }
            ans = temp;
        }
        return ans[dst] == Integer.MAX_VALUE ? -1 : ans[dst];
    }
}