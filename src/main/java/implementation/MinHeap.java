package implementation;

import exceptions.CustomNoSuchElementException;

import java.util.ArrayList;
import java.util.List;

public class MinHeap implements Heap {
    int size;
    List<Integer> element;
    public MinHeap() {
        this.size = 0;
        this.element = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(int num) {
        element.add(num);
        size++;
        makeHeap(size);
    }

    @Override
    public int remove() {
        if (size == 0) {
            throw new CustomNoSuchElementException();
        }
        else {
            int k = element.remove(0);
            size--;
            makeHeap(size);
            return k;
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.element = new ArrayList<>();

    }

    public void heapify(int parent, int size) {
        int leftChild = parent * 2 + 1;
        int rightChild = parent * 2 + 2;
        int minV = parent;
        if (leftChild < size && element.get(leftChild) < element.get(minV)) {
            minV = leftChild;
        }
        if (rightChild < size && element.get(rightChild) < element.get(minV)) {
            minV = rightChild;
        }
        if(minV != parent) {
            swap(parent, minV);
            heapify(minV, size);
        }
    }

    private void makeHeap(int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i, size);
        }
    }

    private void swap(int a, int b) {
        int tmp = this.element.get(a);
        element.set(a, element.get(b));
        element.set(b, tmp);
    }

    public void printElement() {
        for (int i = 0; i < size; i++) {
            System.out.print(element.get(i)+ " ");
        }
        System.out.println();
    }
}
