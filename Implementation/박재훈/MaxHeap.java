package implementation;

public class MaxHeap implements Heap {
    //배열은 밴 첫 인덱스(0) 비우고 인덱스 1부터 채움(부모와 자식 인덱스 계산 쉬워짐)
    private int[] array;
    //원소의 개수
    private int size;
    //배열 용량. 초기값 임의로 5로 설정
    private int capacity = 5;


    public MaxHeap() {
        array = new int[capacity];
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
        //용량이 꽉찼으면 배열 크기를 2배로 확장, 기존 배열 정보를 복사
        if(++size == capacity){
            int[] temp = new int[size];
            System.arraycopy(array, 0, temp, 0, size);
            capacity *= 2;
            array = new int[capacity];
            System.arraycopy(temp, 0, array, 0, size);
        }
        //맨 끝 인덱스를 변수에 저장
        int idx = size;
        //삽입된 숫자를 루트노드까지 크기 비교
        while(idx > 1){
            //새로 삽입될 위치부터 부모와 크기 비교
            // 부모보다 크면 부모의 위치를 내려주고 idx 변수를 기존 부모 인덱스 위치로
            if(array[idx / 2] < num){
                array[idx] = array[idx / 2];
                idx = idx / 2;
            }else {// 부모가 더 크면 올바른 위치 찾은것, 반복문 탈출
                break;
            }
        }
        array[idx] = num;
    }
    
    @Override
    public int remove() {
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        //반환할 값 = 루트노드의 값
        int removedval = array[1];
        //배열 맨 끝값을 루트 위치로 올리고 자리를 찾아줄 것
        int num = array[size--];
        //루트노드부터 내려오면서
        int idx = 1;
        //자식이 있으면 자식과 크기 비교
        while(idx * 2 <= size){
            int biggerChild, biggerChildIdx;
            //오른쪽 자식노드가 있고, 그 노드가 왼쪽 자식노드 값보다 큼
            if(idx * 2 + 1 <= size && array[idx * 2] < array[idx * 2 + 1]) {
                biggerChild = array[idx * 2 + 1];
                biggerChildIdx = idx * 2 + 1;
            //오른쪽 자식노드 없거나 왼쪽 자식노드가 더 큼
            }else{
                biggerChild = array[idx * 2];
                biggerChildIdx = idx * 2;
            }
            //더 큰 자식노드 값과 비교, 위치 조정
            if(biggerChild > num){
                array[idx] = biggerChild;
                idx = biggerChildIdx;
            }else{
                break;
            }
        }
        array[idx] = num;
        return removedval;
    }
    
    @Override
    public void clear() {
        size = 0;
        array = new int[capacity];
    }
}
