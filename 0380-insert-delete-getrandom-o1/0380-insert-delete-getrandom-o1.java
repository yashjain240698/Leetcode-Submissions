class RandomizedSet {
    private Set<Integer> st = new HashSet<>();
    public RandomizedSet() {
        st = new HashSet<>();
    }
    
    public boolean insert(int val) {
        boolean ans = st.contains(val);
        st.add(val);
        return !ans;
    }
    
    public boolean remove(int val) {
        boolean ans = st.contains(val);
        st.remove(val);
        return ans;
    }
    
    public int getRandom() {
        List<Integer> l = new ArrayList<>(st);
        Random r = new Random();
        int idx = r.nextInt(st.size());
        return l.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */