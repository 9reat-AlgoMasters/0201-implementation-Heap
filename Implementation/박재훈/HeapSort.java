package implementation;

import java.util.Scanner;

public class HeapSort {
    static int[] arr;
    static int size;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("정렬할 숫자의 개수를 입력하세요>>");
        size = sc.nextInt();

        //부모-자식 인덱스 계산의 편의상 배열의 1번 인덱스부터 size번째까지를 채움
        arr = new int[size+1];
        System.out.println("정렬할 숫자들을 입력하세요>>");
        for (int i = 1; i <= size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("1 : 오름차순 | 2: 내림차순");
        if(sc.nextInt() == 1) {
            heapSortAsc();
        }else{
            heapSortDesc();
        }
        for (int i = 1; i <= size; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    static void heapSortAsc(){
        //배열을 MaxHeap으로 구성
        //마지막 원소의 부모부터 루트까지 자식-부모가 MaxHeap 관계대로 배치됐는지 확인, 재배치
        for (int i = size; i > 0 ; i--) {
            makeMaxHeap(i, size);
        }

        for (int i = size; i > 0; i--) {
            //루트값(최댓값) 배열 i번째 자리에 배치 => 값이 큰 순으로 뒤에서 부터 채움 => 오름차순
            int temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;

            // 루트에 새 값이 올라오면서 힙구조 바뀌었으므로 재구성 필요
            // 자리를 찾은 루트값은 재구성에서 제외 (lastIdx 인자값으로 i-1)
            makeMaxHeap(1, i-1);
        }

        System.out.print("오름차순 정렬 : ");
    }

    private static void makeMaxHeap(int idx, int lastIdx) {
        while(idx * 2 <= lastIdx) {
            int biggerChild, biggerChildIdx;
            //오른쪽 자식노드가 있고, 그 노드가 왼쪽 자식노드 값보다 큼
            if (idx * 2 + 1 <= lastIdx && arr[idx * 2] < arr[idx * 2 + 1]) {
                biggerChild = arr[idx * 2 + 1];
                biggerChildIdx = idx * 2 + 1;
                //오른쪽 자식노드 없거나 왼쪽 자식노드가 더 큼
            } else {
                biggerChild = arr[idx * 2];
                biggerChildIdx = idx * 2;
            }
            //더 큰 자식노드 값과 기준 값 비교, 위치 조정
            if (biggerChild > arr[idx]) {
                int temp = arr[idx];
                arr[idx] = biggerChild;
                arr[biggerChildIdx] = temp;

                idx = biggerChildIdx;
            } else {
                return;
            }
        }
    }

    static void heapSortDesc(){
        //오름차순과 반대로 MinHeap 구성
        for (int i = size; i > 0 ; i--) {
            makeMinHeap(i, size);
        }

        for (int i = size; i > 0; i--) {
            int temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;

            makeMinHeap(1, i-1);
        }
        System.out.print("내림차순 정렬 : ");
    }

    static void makeMinHeap(int idx, int lastIdx) {
        while(idx * 2 <= lastIdx) {
            int smallerChild, smallerChildIdx;
            //오른쪽 자식노드가 있고, 그 노드가 왼쪽 자식노드 값보다 작음
            if (idx * 2 + 1 <= lastIdx && arr[idx * 2] > arr[idx * 2 + 1]) {
                smallerChild = arr[idx * 2 + 1];
                smallerChildIdx = idx * 2 + 1;
                //오른쪽 자식노드 없거나 왼쪽 자식노드가 더 작음
            } else {
                smallerChild = arr[idx * 2];
                smallerChildIdx = idx * 2;
            }
            //더 작은 자식노드 값과 기준 값 비교, 위치 조정
            if (smallerChild < arr[idx]) {
                int temp = arr[idx];
                arr[idx] = smallerChild;
                arr[smallerChildIdx] = temp;

                idx = smallerChildIdx;
            } else {
                return;
            }
        }
    }
}
