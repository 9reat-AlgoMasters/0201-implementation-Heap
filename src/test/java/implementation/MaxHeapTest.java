package implementation;

import exceptions.CustomNoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {
    static MaxHeap heap;
    
    @BeforeEach
    void beforeEach() {
        heap = new MaxHeap();
        heap.add(3);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        heap.add(3);
    }
    
    @DisplayName("isEmpty() 테스트 - True")
    @Test
    void isEmptyTest1() {
        MaxHeap emptyHeap = new MaxHeap();
        assertThat(emptyHeap.isEmpty()).isTrue();
    }
    
    @DisplayName("isEmpty() 테스트 - false")
    @Test
    void isEmptyTest2() {
        assertThat(heap.isEmpty()).isFalse();
    }
    
    @DisplayName("remove 테스트")
    @Test
    void deleteTest1() {
        assertThat(heap.remove()).isEqualTo(10);
        assertThat(heap.size()).isEqualTo(4);
        
        assertThat(heap.remove()).isEqualTo(7);
        assertThat(heap.size()).isEqualTo(3);
        
        assertThat(heap.remove()).isEqualTo(3);
        assertThat(heap.size()).isEqualTo(2);
        
        assertThat(heap.remove()).isEqualTo(3);
        assertThat(heap.size()).isEqualTo(1);
        
        assertThat(heap.remove()).isEqualTo(2);
        assertThat(heap.size()).isEqualTo(0);
    }
    
    @DisplayName("remove 테스트 - 비어있을 때 예외 발생")
    @Test
    void deleteTest2() {
        MaxHeap emptyHeap = new MaxHeap();
        assertThrows(CustomNoSuchElementException.class, emptyHeap::remove);
    }
}