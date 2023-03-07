package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HeapSort {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("배열의 사이즈를 입력하세요.");
        System.out.print(">>");
        int size = Integer.parseInt(br.readLine());
        System.out.println("숫자를 사이즈 개수만큼 입력하세요. (공백으로 구분)");
        System.out.print(">> ");
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열 입력 받기
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println("<<정렬 기준>>");
        System.out.println("오름차순 : 1");
        System.out.println("내림차순 : 2");
        int k = Integer.parseInt(br.readLine());
        boolean ASC = (k == 1);

        heapSort(arr, size, ASC);
        System.out.println(Arrays.toString(arr));

    }

    private static void heapify(int[] arr, int parent, int size , boolean ASC) {
        if (ASC) {
            // 부모 노드에 대해 왼쪽 자식, 오른쪽 자식과의 크기를 비교하여
            // 부모보다 큰 것이 있다면 자식 중 제일 큰 것을 부모로 만들고, 내려가버린 부모에 대해 heapify를 재귀적으로 호출
            int leftChild = parent * 2 + 1;
            int rightChild = parent * 2 + 2;
            int maxV = parent;
            if (leftChild < size && arr[leftChild] > arr[maxV]) {
                maxV = leftChild;
            }
            if (rightChild < size && arr[rightChild] > arr[maxV]) {
                maxV = rightChild;
            }
            if (maxV != parent) {
                swap(arr, parent, maxV);
                // heapify로 인해 내려간 원래 부모를 다시 heapify를 함으로써
                // 밑 노드들 모두에 대해 작게 만듬
                heapify(arr, maxV, size, ASC);
            }
        }
        else {
            int leftChild = parent * 2 + 1;
            int rightChild = parent * 2 + 2;
            int minV = parent;
            if (leftChild < size && arr[leftChild] < arr[minV]) {
                minV = leftChild;
            }
            if (rightChild < size && arr[rightChild] < arr[minV]) {
                minV = rightChild;
            }
            if (minV != parent) {
                swap(arr, parent, minV);
                // heapify로 인해 내려간 원래 부모를 다시 heapify를 함으로써
                // 밑 노드들 모두에 대해 작게 만듬
                heapify(arr, minV, size, ASC);
            }

        }

    }

    private static void heapSort(int[] arr, int size, boolean ASC) { // 힙정렬
        makeHeap(arr, size, ASC);
        for (int i = size - 1; i >= 0; i--) {
            System.out.printf("========%d단계========\n", size - i);
            printArr(arr); // 단계별 출력
            swap(arr, 0, i); // 가장 큰 것을 밑으로 보내고, 더이상 탐색시키지 않음
            heapify(arr, 0, i, ASC);

        }
    }
    private static void makeHeap(int[] arr, int size, boolean ASC) {
        // 부모 노드가 자식 노드보다 크게 만듬.
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, i, size, ASC);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println("\n");
    }


}

