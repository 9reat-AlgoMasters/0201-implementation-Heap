package implementation;

import exceptions.CustomNoSuchElementException;

import java.util.ArrayList;
import java.util.List;

public class MinHeap implements Heap {
    
    private List<Integer> heap;
    private int size;
    
    public MinHeap() {
        heap = new ArrayList<>();
        this.size = 0;
        heap.add(0); // 1번 인덱스부터 쓰기 위함
        
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void add(int num) {
        heap.add(num);
        size++;
        bubbleUp(size);
    }
    
    private void bubbleUp(int numIdx) {
        if (numIdx == 1 || heap.get(numIdx / 2) <= heap.get(numIdx)) {
            return;
        }
    
        swap(numIdx, numIdx / 2);
        bubbleUp(numIdx / 2);
    }
    
    @Override
    public int remove() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException("heap이 비어있습니다.");
        }
    
        int removedValue = heap.get(1);
        swap(1, size);
        size--;
        bubbleDown(1);
        return removedValue;
    }
    
    private void bubbleDown(int numIdx) {
        if (numIdx*2 > size) {
            return;
        }
        
        int nextIdx = numIdx*2;
        if (numIdx * 2 + 1 <= size && heap.get(numIdx * 2 + 1) < heap.get(nextIdx)) {
            nextIdx = numIdx * 2 + 1;
        }
    
        if (heap.get(numIdx) < heap.get(nextIdx)) {
            return;
        }
    
        swap(numIdx, nextIdx);
        bubbleDown(nextIdx);
    }
    
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    @Override
    public void clear() {
        heap.clear();
    }
}
