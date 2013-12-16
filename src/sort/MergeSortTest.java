package sort;

import org.junit.*;

import static org.junit.Assert.*;

public class MergeSortTest {
    MergeSort mergeSort;
    
    @Before
    public void setUp() {
        mergeSort =  new MergeSort();
    }
    
    @Test
    public void should_return_1_for_merge_sort_1() {
        int[] input = {1};
        int[] expected = {1};
//        System.out.println("hello,world");
        assertArrayEquals(expected, mergeSort.sort(input));
    }

    @Test
    public void should_return_12_for_merge_sort_21() {
        int[] input = {2,1};
        int[] expected = {1,2};
        assertArrayEquals(expected, mergeSort.sort(input));
    }

    @Test
    public void should_return_1234_for_merge_sort_3214() {
        int[] input = {3,2,1,4};
        int[] expected = {1,2,3,4};
        assertArrayEquals(expected, mergeSort.sort(input));
    }

    @Test
    public void should_return_12345_for_merge_sort_54321() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, mergeSort.sort(input));
    }
}

