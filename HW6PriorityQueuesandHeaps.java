/*
Course: CSC310
Project: Hw5
Date: 3 / 27 / 2019
Author: Jonathan Lawrence Melkulcok
 */

import java.util.*;

class PrioQ {

    private List<Integer> queue = new LinkedList<>();

    void add(int x) {
        if (queue.isEmpty())
            queue.add(x);
        else {
            for (int i = 0; i < queue.size(); i++) {
                if (x <= queue.get(i)) {
                    queue.add(i, x);
                    break;
                } 
                else if (i == queue.size() - 1) {
                    queue.add(x);
                    break;
                }
            }
        }
    }

    int min() {
        return queue.get(0);
    }

    int removeMin() {
        int rem = queue.get(0);
        queue.remove(0);
        return rem;
    }

    void sort() {
        while (queue.size() > 0) {
            System.out.print(queue.get(0) + " ");
            queue.remove(0);
        }
        System.out.println();
    }
}

class BinMinHeap {

    private List<Integer> heap = new LinkedList<>();

    void insert(int k) {
        if (this.is_empty()){
            heap.add(k);
            return;
        }
        downheap(k);
        //upheap(k);
    }

    int find_min() {
        return heap.get(0);
    }

    int del_min() {   
        int pop = heap.get(0);
        heap.remove(0);
        return pop;
    }

    boolean is_empty() {
        if (heap.size() == 0) 
            return true;
        return false;
    }

    int size() {
        return heap.size();
    }

    void downheap(int x) {
        //check to see if element new min
        if (x < find_min()){
            heap.add(0, x);
            return;
        }
        int i = 1;     
        heap.add(x);
        
        //recursively move through the tree and find he best spot
        if (!isLeaf(i)) { 
            if (heap.get(i) > heap.get(2*i) || heap.get(i) > heap.get(2*i+1)) { 
                if (heap.get(2*i) < heap.get(2*i+1)) { 
                    swap(i, 2*i); 
                    upheap(2*i); 
                } 
                else { 
                    swap(i, 2*i+1); 
                    upheap(2*i+1); 
                } 
            } 
        } 
    }

    void upheap(int x) {
        int currentpos = heap.size(); 
        heap.add(x);
        
        //start at end of heap and move toward min until spot found
        while (heap.get(currentpos) < heap.get(parent(currentpos))) { 
            swap(currentpos, parent(currentpos)); 
            currentpos = parent(currentpos); 
        } 
    }

    void build_heap(LinkedList l) {
        heap.clear();
        heap = l;
    }
    
    private int parent(int pos) {
        return pos / 2; 
    }
    
    private boolean isLeaf(int pos) { 
        if (pos >= (heap.size() / 2) && pos <= heap.size()) 
            return true; 
        return false; 
    } 
    
    private void swap(int fpos, int spos) { 
        int tmp = heap.get(fpos); 
        heap.set(fpos, heap.get(spos));
        heap.set(spos, tmp);
    } 
}

public class HW6PriorityQueuesandHeaps { 

    static void heapifyit(int[] heap, int size, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < size && heap[l] > heap[largest]) 
            largest = l;
        if (r < size && heap[r] > heap[largest])
            largest = r;
        if (largest != i) {
            int swap = heap[i];
            heap[i] = heap[largest];
            heap[largest] = swap;
            heapifyit(heap, size, largest);
        }
    }

    static void inPlaceHeapSort(int[] heap) {
        int n = heap.length;

        for (int i = n - 1; i >= 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            heapifyit(heap, i, 0);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PrioQ test = new PrioQ();
        int[] a = {1, 6, 3, 7, 5, 9};
        for (int i = 0; i < a.length; i++) {
            test.add(a[i]);
        }
        test.sort();

        int[] heap = {9, 7, 5, 2, 6, 4};

        inPlaceHeapSort(heap);

        BinMinHeap min = new BinMinHeap();
        min.insert(5);
        min.insert(7);
        min.insert(3);
        min.insert(11);
        
        System.out.println(min.del_min());
        System.out.println(min.del_min());
        System.out.println(min.del_min());
        System.out.println(min.del_min());
    }
}