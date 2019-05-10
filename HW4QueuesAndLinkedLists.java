
import java.util.LinkedList;

/*
Course: CSC310
Project: Hw4
Date: 2 / 23 / 2019
Author: Jonathan Lawrence Melkulcok
Purpose: impliments several methods for queues and linked lists, basic main for testing
 */

class MyCircularDeque {

    int size, k;
    DoubleListNode head, tail;

    //sets size of que, initializes 
    public MyCircularDeque(int k) {
        head = new DoubleListNode(-1);
        tail = new DoubleListNode(-1);
        head.pre = tail;
        tail.next = head;
        this.k = k;
        this.size = 0;
    }

    //adds an item to front
    public boolean insertFront(int value) {
        if (size == k) 
            return false;
        DoubleListNode node = new DoubleListNode(value);
        node.next = head;
        node.pre = head.pre;
        head.pre.next = node;
        head.pre = node;
        size++;
        return true;
    }

    //adds item to rear
    public boolean insertLast(int value) {
        if (size == k) 
            return false;
        DoubleListNode node = new DoubleListNode(value);
        node.next = tail.next;
        tail.next.pre = node;
        tail.next = node;
        node.pre = tail;
        size++;
        return true;
    }

    //remove item from front
    public boolean deleteFront() {
        if (size == 0) 
            return false;
        head.pre.pre.next = head;
        head.pre = head.pre.pre;
        size--;
        return true;
    }

    //delete item from rear
    public boolean deleteLast() {
        if (size == 0) 
            return false;
        tail.next.next.pre = tail;
        tail.next = tail.next.next;
        size--;
        return true;
    }

    //read front value
    public int getFront() {
        return head.pre.val;
    }

    //read rer value
    public int getRear() {
        return tail.next.val;
    }

    //see if deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //see if deque is at capacity
    public boolean isFull() {
        return size == k;
    }   
}

class DoubleListNode {

    DoubleListNode pre, next;
    int val;

    public DoubleListNode(int val) {
        this.val = val;
    } 
}

class SimpleQueue {
    private LinkedList<Object> data = new LinkedList<Object>();

    public void enqueue(Object item) {
        data.addLast(item);
    }

    public Object dequeue() {
        return data.removeFirst();
    }

    public Object first() {
        return data.getFirst();
    }

    public int len() {
        return data.size();
    }

    public boolean is_Empty() {
        return data.isEmpty();
    }
    
    public boolean search(Object item) {
        return data.contains(item);
    }
}
    
public class HW4QueuesAndLinkedLists {

    public static DoubleListNode mergeTwoLists(DoubleListNode dln1, DoubleListNode dln2) {

        //check for null cases
        if (dln1 == null && dln2 != null)
            return dln2;

        if (dln1 != null && dln2 == null)
            return dln1;

        DoubleListNode list1 = dln1;
        DoubleListNode list2 = dln2;

        DoubleListNode head = new DoubleListNode(0);
        DoubleListNode merged = head;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                merged.next = list1;
                list1 = list1.next;
            } else {
                merged.next = list2;
                list2 = list2.next;
            }
            merged = merged.next;
        }

        while (list1 != null) {
            merged.next = list1;
            list1 = list1.next;
            merged = merged.next;
        }

        while (list2 != null) {
            merged.next = list2;
            list2 = list2.next;
            merged = merged.next;
        }
        return head.next;
    }

    public static void display(DoubleListNode head2) {
        DoubleListNode temp = head2;
        temp = temp.next;
        while (temp.next != null) {
            if (temp.val != -1)
                System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        //test cases from assignment for #1
        MyCircularDeque circle = new MyCircularDeque(3);
        System.out.println(circle.insertLast(1));
        System.out.println(circle.insertLast(2));
        System.out.println(circle.insertFront(3));
        System.out.println(circle.insertFront(4));
        System.out.println(circle.getRear());
        System.out.println(circle.isFull());
        System.out.println(circle.deleteLast());
        System.out.println(circle.insertFront(4));
        System.out.println(circle.getFront());
        
        //test case #2
        MyCircularDeque one = new MyCircularDeque(3);
        one.insertFront(1);
        one.insertFront(2);
        one.insertFront(4);
        MyCircularDeque two = new MyCircularDeque(3);
        two.insertFront(1);
        two.insertFront(3);
        two.insertFront(4);
        
        display(one.tail);
        display(two.tail);
        display(mergeTwoLists(one.tail, two.tail));
        
        //test case 3
        SimpleQueue sim = new SimpleQueue();
        sim.enqueue(5);
        System.out.println(sim.len());
        System.out.println(sim.first());
        sim.dequeue();
        System.out.println(sim.is_Empty());
        System.out.println(sim.search(5));
    }
}
