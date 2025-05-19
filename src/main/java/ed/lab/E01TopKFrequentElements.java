package ed.lab;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k)
    {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);

        Queue<Integer> heap = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));
        heap.addAll(count.keySet());

        int[] result = new int[k];
        for (int i = 0; i < k; i++)
        {
            result[i] = heap.poll();
        }

        return result;
    }
}