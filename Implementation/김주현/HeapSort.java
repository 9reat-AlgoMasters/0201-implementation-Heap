package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HeapSort {
    static final boolean ASC = true;
    static final int ROOT = 1;
    
    static int lastIndex;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        System.out.print("숫자 개수 : ");
        int N = Integer.parseInt(br.readLine());
        lastIndex = N;
        System.out.print("숫자 입력(띄어쓰기로 구분)\n-> ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        for (int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print("정렬 방법 (1:오름차순, 2:내림차순) : ");
        boolean order = br.readLine().equals("1");
    
        sort(arr, order);
        System.out.println("\n< 정렬결과 >");
        printArr(arr);
    }
    
    private static void sort(int[] arr, boolean order) {
        makeHeap(arr, order);
        System.out.printf("%s힙을 만들었습니다!\n", order == ASC ? "최대" : "최소");
        printArr(arr);
        heapToSorted(arr, order);
    }
    
    
    private static void makeHeap(int[] arr, boolean order) {
        int firstParent = (arr.length -1) / 2;
        for (int i = firstParent; i >= 1; i--) {
            if (order == ASC) maxHeapBubbleDown(arr, i, lastIndex);
            else minHeapBubbleDown(arr, i, lastIndex);
        }
    }
    
    private static void maxHeapBubbleDown(int[] arr, int parent, int lastIndex) {
        if (parent*2 > lastIndex) {
            return;
        }
        
        int biggerChildIdx = parent*2;
        if (parent * 2 + 1 <= lastIndex && arr[parent * 2 + 1] > arr[biggerChildIdx]) {
            biggerChildIdx = parent * 2 + 1;
        }
    
        if (arr[parent] < arr[biggerChildIdx]) {
            swap(arr, parent, biggerChildIdx);
            maxHeapBubbleDown(arr, biggerChildIdx, lastIndex);
        }
    }
    
    private static void minHeapBubbleDown(int[] arr, int parent, int lastIndex) {
        if (parent*2 > lastIndex) {
            return;
        }
        
        int smallerChildIdx = parent*2;
        if (parent * 2 + 1 <= lastIndex && arr[parent * 2 + 1] < arr[smallerChildIdx]) {
            smallerChildIdx = parent * 2 + 1;
        }
        
        if (arr[parent] > arr[smallerChildIdx]) {
            swap(arr, parent, smallerChildIdx);
            minHeapBubbleDown(arr, smallerChildIdx, lastIndex);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static void heapToSorted(int[] arr, boolean order) {
        for (int i = lastIndex; i > 1; i--) {
            swap(arr, ROOT, i);
            if (order == ASC) maxHeapBubbleDown(arr, ROOT, i - 1);
            else minHeapBubbleDown(arr, ROOT, i - 1);
        }
    }
    
    private static void printArr(int[] arr) {
        for (int i = 1; i <= lastIndex; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}
