/*
Course: CSC310
Project: Hw5
Date: 3 / 20 / 2019
Author: Jonathan Lawrence Melkulcok
Purpose: impliments a recursive tower of hanoi algorith aand a recusive in/pre order tree traversal
 */

class Node { 
    String key; 
    Node left, right; 
  
    public Node(String item) { 
        key = item; 
        left = right = null; 
    } 
} 
  
class BinaryTree { 
    Node root;   
    BinaryTree() { 
        root = null; 
    } 
  
    void printInorder(Node node) { 
        if (node == null) 
            return; 
        printInorder(node.left); 
        System.out.print(node.key + " "); 
        printInorder(node.right); 
    } 
  
    void printPreorder(Node node) { 
        if (node == null) 
            return;  
        System.out.print(node.key + " ");  
        printPreorder(node.left);   
        printPreorder(node.right); 
    } 
}

public class HW5RecursionTreeTraverals {
 
    static void towerOfHanoi(int n, char pre, char dest, char aux) { 
        if (n == 1) { 
            System.out.println("Move disk 1 from peg " +  pre + " to peg " + dest); 
            return; 
        } 
        towerOfHanoi(n-1, pre, aux, dest); 
        System.out.println("Move disk " + n + " from peg " +  pre + " to peg " + dest); 
        towerOfHanoi(n-1, aux, dest, pre); 
    }    
    
    public static void main(String[] args) {
        
        towerOfHanoi(4, 'A', 'C', 'B');
        
        BinaryTree tree = new BinaryTree(); 
        tree.root = new Node("1"); 
        tree.root.left = new Node(""); 
        tree.root.right = new Node("2"); 
        tree.root.right.left = new Node("3"); 
        System.out.println(); 
        System.out.print("Inorder: "); 
        tree.printInorder(tree.root); 
        System.out.println(); 
        System.out.print("Preorder: "); 
        tree.printPreorder(tree.root);
        System.out.println(); 
    } 
}