package implementation;

import java.util.Scanner;

public class HeapSort {
    static int[] arr;
    static int size;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("정렬할 숫자의 개수를 입력하세요>>");
        size = sc.nextInt();

        arr = new int[size];
        System.out.println("정렬할 숫자들을 입력하세요>>");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        heapSort();
    }

    static void heapSort(){
        MinHeap heap = new MinHeap();
        for (int i = 0; i < size; i++) {
            heap.add(arr[i]);
        }

        int[] asc = new int[size];
        int[] desc = new int[size];

        for (int i = 0; i < size; i++) {
            int val = heap.remove();
            asc[i] = val;
            desc[size-1-i] = val;
        }

        System.out.print("오름차순 정렬 : ");
        for (int i = 0; i < size; i++) {
            System.out.print(asc[i]+" ");
        }
        System.out.println();
        System.out.print("내림차순 정렬 : ");
        for (int i = 0; i < size; i++) {
            System.out.print(desc[i]+" ");
        }
    }
}
