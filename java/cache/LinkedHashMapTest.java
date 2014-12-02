import java.util.*;

public class LinkedHashMapTest {
    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(10, 0.75f, true);
        map.put(9, 3);
        map.put(8, 2);
        map.put(7, 1);
        map.put(6, 0);
        for(Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            System.out.println(it.next().getKey());
        }

        map.get(9);
        for(Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            System.out.println(it.next().getKey());
        }
    }
}
