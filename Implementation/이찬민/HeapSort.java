package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class HeapSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("원하는 정렬을 선택하세요>>" );
        StringBuilder sb = new StringBuilder();
        sb.append("1. 오름차순 정렬\n");
        sb.append("2. 내림차순 정렬\n");

        sb.append("3. 종료\n");

        System.out.println(sb.toString());

        int select = Integer.parseInt(br.readLine());
        while (!(select < 4 && select > 0)) {
            System.out.println("정확한 숫자를 입력해주세요!!");
            System.out.println(sb.toString());
            select = Integer.parseInt(br.readLine());
        }

        if (select == 3) {
            System.exit(0);
        }

        sb.setLength(0);
        System.out.println("갯수 입력: ");
        int N = Integer.parseInt(br.readLine());
        System.out.println("숫자들을 입력해주세요 (띄워쓰기 사용)>> ");

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        switch (select) {
            case 1:
                System.out.println("오름차순 정렬");
                maxheapSort(arr);

                print(arr);

                break;
            case 2:
                System.out.println("내림차순 정렬");
                minheapSort(arr);

                print(arr);

                break;
        }


    }

    static void maxheapSort(int[] arr) {
        int size = arr.length;
        
        // 0개, 1개 밖에 없다면 return (index 오류 뱉을뿐더러 해줄 이유가 없다)
        if (size < 3) {
            return;
        }
        
        // 마지막 값의 부모
        int pIdx = (size - 1) / 2;
        
        //모든 서브루트를 maxheap으로 (배열을 maxheap으로 바꿔주는 부분)
        makeHeap(arr, pIdx, size, 1);

        // 맨앞의 루트 노드 배열의 맨뒤로 빼주고 그리고 맨뒤로 보낸 값을 제외하고
        // 다시 maxheap구성
        for (int i = size-1; i >= 1; i--) {
            swap(arr, 1, i);
            heapifyUp(arr, 1, i - 1);
        }
    }

    static void heapifyUp(int[] arr, int pIdx, int lastIdx) {
        // 재귀 x 버전
        while ((pIdx * 2) <= lastIdx) {
            int left = (pIdx * 2);
            int right = (pIdx * 2) + 1;
            int parent = pIdx;

            // 왼쪽 자식과 비교
            if (arr[left] > arr[parent]) {
                parent = left;
            }

            // 오른쪽 자식과 비교
            if (right <= lastIdx && arr[right] > arr[parent]) {
                parent = right;
            }

            // 바뀌지 않는다면 return
            if (pIdx == parent) {
                return;
            }

            swap(arr, pIdx, parent);
            pIdx = parent;

        }
    }

    static void minheapSort(int[] arr) {
        int size = arr.length;

        // 0개, 1개 밖에 없다면 return (index 오류 뱉을뿐더러 해줄 이유가 없다)
        if (size < 3) {
            return;
        }

        // 마지막 값의 부모
        int pIdx = (size - 1) / 2;

        //모든 서브루트를 minheap으로 (배열을 minheap으로 바꿔주는 부분)
        makeHeap(arr, pIdx, size, 2);

        // 맨앞의 루트 노드 배열의 맨뒤로 빼주고 그리고 맨뒤로 보낸 값을 제외하고
        // 다시 maxheap구성
        for (int i = size-1; i >= 1; i--) {
            swap(arr, 1, i);
            heapifyDown(arr, 1, i - 1);
        }
    }

    static void heapifyDown(int[] arr, int pIdx, int lastIdx) {
        // 재귀 버전
        if ((pIdx * 2) <= lastIdx) {
            int minPos = (pIdx * 2);
            int minVal = arr[minPos];

            // 오른쪽 자식과 비교
            if (minPos + 1 <= lastIdx && arr[minPos] < minVal) {
                minPos += 1;
                minVal = arr[minPos];
            }

            if (arr[pIdx] > minVal) {
                swap(arr, pIdx, minPos);
                heapifyUp(arr, minPos, lastIdx);
            }


        }
    }

    static void makeHeap(int[] arr, int pIdx, int size, int k) {
        //모든 서브루트를 minheap으로 (배열을 minheap으로 바꿔주는 부분)
        for (int i = pIdx; i >= 1; i--) {
            if (k == 1) {
                heapifyUp(arr, i, size - 1);
            }
            if (k == 2) {
                heapifyDown(arr, i, size - 1);
            }
        }
    }

    static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}

