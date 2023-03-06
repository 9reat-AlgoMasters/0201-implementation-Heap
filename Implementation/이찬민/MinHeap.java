package implementation;

import exceptions.CustomNoSuchElementException;

import java.util.ArrayList;
import java.util.List;

public class MinHeap implements Heap {
    List<Integer> arr;
    int size;

    public MinHeap() {
        this.arr = new ArrayList<>();
        this.size = arr.size();
        arr.add(0);
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
        arr.add(num);
        size++;
        int t = size;
        int pIdx = size / 2;

        while (t > 1 && arr.get(pIdx) > arr.get(t)) {
            int temp = arr.get(t);
            arr.set(t, arr.get(pIdx));
            arr.set(pIdx, temp);

            t = pIdx;
            pIdx = t / 2;
        }

    }

    @Override
    public int remove() {
        if (size == 0) {
            throw new CustomNoSuchElementException();
        }

        int min = arr.get(1);
        arr.set(1, arr.get(size));
        arr.remove(size);
        size--;

        int t = 1;

        while (t * 2 <= size) {
            int mPos = t * 2;
            int mVal = arr.get(mPos);

            int temp = arr.get(t);

            if ((mPos + 1) <= size && arr.get(mPos + 1) < mVal) {
                mPos = mPos + 1;
                mVal = arr.get(mPos);
            }

            if (mVal > temp) {
                break;
            }

            arr.set(t, arr.get(mPos));
            arr.set(mPos, temp);
            t = mPos;

        }

        return min;
    }

    @Override
    public void clear() {
        arr.clear();
    }
}
