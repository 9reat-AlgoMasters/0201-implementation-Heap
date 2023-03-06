package implementation;

import java.io.*;
import java.util.*;
// 참고 https://st-lab.tistory.com/225
// 참고 https://www.youtube.com/watch?v=iyl9bfp_8ag
public class HeapSort {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("***Heap Sort***\n");
        System.out.printf("정렬할 숫자를 띄어쓰기 하여 입력하세요 >>");

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
//        System.out.println("오름차순은 1번, 내림차순은 2번을 입력하세요 >> ");
//        int k = Integer.parseInt(br.readLine());

        System.out.print(" 정렬 전 => ");
        System.out.println(Arrays.toString(arr));
        System.out.println("댕 졸리네욤.. 과거에 안한 나자신.. 반성해라....");

        Sort();


        System.out.print(" 정렬 후 => ");
        System.out.println(Arrays.toString(arr));

    }

    static void Sort() {
        int size = arr.length;
        if (size < 2) { //원소가 1개이거나 0개일때는 정렬할 필요없음
            return;
        }

        int parentIndex = getParent(size - 1); //배열의 사이즈 -1 은 가장 마지막 인덱스
        //가장 마지막 노드의 부모노드의 인덱스를 가지고 옴.

        //max heap 만들기
        for (int i = parentIndex; i >= 0; i--){
            heapify(i, size - 1);
            /*
            i (부모 인덱스)를 줄여가면서 즉 이진트리에서 확인하고 있는 서브트리 부분을 높여가며
            heap 조건을 만족시키도록 한다.
            heap 조건 : 부모 노드는 두 자식보다 큰 수를 가지거나(max heap) 작은 수를 가져야 한다.(min heap)
            +) 가장 마지막 인덱스의 부모노드가 부모노드들 중 마지막 노드이기 때문에 거기서 부터 0(최상위 노드)까지 모든 부모노드가
            heap 조건을 만족하는 지 확인하면 된다!
            */
        }
        for (int i = size -1 ; i > 0 ; i--) {
            /*
            마지막 인덱스와 0번째 인덱스와 바꿔준 후 0 ~ i-1까지에 대해 heapify를 진행한다.
            가장 큰값을 가지는 노드를 0번째 노드로 만들었다면 그 노드를 가장 마지막 노드와 교환 후
            마지막 노드를 제외한 나머지 트리에 대해 heapify
             */
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(0, i-1);

        }

    }

    /*
    힙 생성 알고리즘 : 하나의 노드를 제외하고는 최대힙이 구성되어 있는 상태를 가정하여
    제외한 특정 노드의 두 자식 중 더 큰 자식과 자신의 위치를 변경하는 알고리즘
     */
    static void heapify(int parentIndex, int lastIndex) {
        int leftChildIndex = parentIndex * 2 + 1; //왼쪽 자식 노드의 인덱스
        int rightChildIndex = parentIndex * 2 + 2; //오른쪽 자식 노드의 인덱스
        int largestIndex = parentIndex; //서브 트리중 가장 큰 값을 가지는 노드가 부모라고 가정한다.


        //왼쪽 자식 노드 인덱스가 범위 안에 있고 부모 보다 크면
        if (leftChildIndex <= lastIndex && arr[largestIndex] < arr[leftChildIndex]) {
            //큰값을 가르키는 largestIndex 값을 왼쪽 자식 노드를 가르키게 바꿔줌
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex <= lastIndex && arr[largestIndex] < arr[rightChildIndex]) {
            largestIndex = rightChildIndex;
        }

        //만약 부모보다 큰 값을 가지는 자식노드가 있었다면 위에서 변경되었고
        // 없었다면 largestIndex는 그대로 부모노드인덱스일 것이다.
        // 변경되어 같지 않다면 부모와 큰 값을 가지는 자식 노드와 바꿔준다.
        if (parentIndex != largestIndex) {
            int temp = arr[largestIndex];
            arr[largestIndex] = arr[parentIndex];
            arr[parentIndex] = temp;
            heapify(largestIndex, lastIndex);
            // largestindex에는 값이 큰 자식의 노드를 가르키고 있었으며 부모와 swap했을때
            // 그 값이 아래 서브트리 즉 lagestindex를 부모로 가지는 서브트리가 heap조건에
            //만족하는지 확인
        }

    }
    static int getParent(int idx) {
        //(자식의 인덱스 -1 ) /2 = 부모 인덱스
        return (idx-1)/2;
    }
}
