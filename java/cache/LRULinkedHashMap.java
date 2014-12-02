import java.util.*;

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K, V>{
    private int capacity;
    LRULinkedHashMap(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        System.out.println(eldest.getKey() + " = " + eldest.getValue());
        return size() > capacity;
    }
    
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LRULinkedHashMap<Integer, Integer>(4);
        map.put(9, 5);
        map.put(8, 4);
        map.put(7, 3);
        map.put(6, 2);
        map.put(5, 1);

        for(Iterator<Map.Entry<Integer, Integer>> it=map.entrySet().iterator(); it.hasNext();) {
            System.out.println(it.next().getKey());
        }
    }
}
