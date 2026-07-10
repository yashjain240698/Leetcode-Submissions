class RandomizedSet {
    private List<Integer> list = new ArrayList<>();
    private Map<Integer,Integer> mp = new HashMap<>();
    private Random rand;
    public RandomizedSet() {
        list = new ArrayList<>();
        mp = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        boolean ans = mp.containsKey(val);
        if(ans == false){
            list.add(val);
            mp.put(val, list.size()-1);
        }
        return !ans;
    }
    
    public boolean remove(int val) {
        boolean ans = mp.containsKey(val);
        if(ans){
            int idxToRemove = mp.get(val);
            int lastElement = list.get(list.size()-1);
            list.set(idxToRemove, lastElement);
            mp.put(lastElement, idxToRemove);

            list.remove(list.size()-1);
            mp.remove(val);
        }
        return ans;
    }
    
    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */