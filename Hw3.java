/*
Course: CSC310
Project: Hw3
Date: 2 / 13 / 2019
Author: Jonathan Lawrence Melkulcok
Purpose: This script manipulates the stack using a custom made method and can 
         evaluate mathematical expressions.  I used some java utilities from 
         some example code online to make the job easier and cleaner looking.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class MinStack {
    //declare arraylists used to store data
    private final List<Integer> stack = new ArrayList<>();
    private final List<Integer> min = new ArrayList<>();

    //push(x) -- Push element x onto stack. 
    void push(int x) {
        stack.add(x);
        if (min.isEmpty() || x <= min.get(min.size() - 1))
            min.add(x);
    }
    //pop() -- Removes the element on top of the stack.
    int pop() {
        int popped = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        if (popped == min.get(min.size() - 1))
            min.remove(min.size() - 1);
        return popped;
    }
    //top() -- Get the top element. 
    int top() {
        return stack.get(stack.size() - 1);
    }
    //getMin() -- Retrieve the minimum element in the stack. 
    int min() {
        return min.get(min.size() - 1);
    }
}

//various methods to get numeric information about a stack
class ValStack {
    List<Integer> stack = new ArrayList<>();
    //add a value to the stack
    void push(int x) {
        stack.add(x);
    }
    //removes value from stack
    int pop() {
        int popped = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return popped;
    }
    //returns last item in list
    int top() {
        return stack.get(stack.size() - 1);
    }
    //returns number of elements in stack
    int size() {
        return stack.size();
    }
}

//various methods to get symbolic information about a stack
class OpStack {
    List<String> stack = new ArrayList<>();
    //add a value to the stack
    void push(String x) {
        stack.add(x);
    }
    //removes last value from stack
    String pop() {
        String popped = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return popped;
    }
    //returns last value in stack
    String top() {
        return stack.get(stack.size() - 1);
    }
    int size() {
        return stack.size();
    }
}

class PostStack {
    List<Integer> stack = new ArrayList<>();
    //add a value to the stack
    void push(int x) {
        stack.add(x);
    }
    //removes value from stack
    int pop() {
        int popped = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return popped;
    }
    //returns last item in list
    int top() {
        return stack.get(stack.size() - 1);
    }
    //returns number of elements in stack
    int size() {
        return stack.size();
    }
}

public class Hw3 {
    
    //initialize variables
    static ValStack valstack = new ValStack();
    static OpStack opstack = new OpStack();
    static MinStack minstack = new MinStack();
    static PostStack postStack = new PostStack();

    static void do_Operation() {
        int x = valstack.pop();
        int y = valstack.pop();
        String oper = opstack.pop();
        switch (oper) {
            case "+":
                valstack.push(y + x);
                break;
            case "-":
                valstack.push(y - x);
                break;
            case "*":
                valstack.push(y * x);
                break;
            case "/":
                valstack.push(y / x);
                break;
            default:
                System.out.println("Unknown Operation");
        }
    }

    static int check_Precedence(String s) {
        switch (s) {
            case "<=":
            case ">=":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return 0;
    }

    static void repOp(String refOp) {
        while (valstack.size() > 1 && check_Precedence(refOp) <= check_Precedence(opstack.top()))
            do_Operation();
    }

    static boolean isNumber(String s) {
        switch (s) {
            case "<=":
            case ">=":
                return false;
            case "+":
            case "-":
                return false;
            case "*":
            case "/":
                return false;
        }
        return true;
    }

    static int evalExpression(String ex) {        
        //use java utility to break input string into easy to work with bits
        StringTokenizer e = new StringTokenizer(ex);
        //parse entire string
        while (e.hasMoreTokens()) {
            String tok = e.nextToken(" ");
            if (isNumber(tok))
                valstack.push(Integer.parseInt(tok));
            else {
                repOp(tok);
                opstack.push(tok);
            }
        }
        repOp("$");
        return valstack.top();
    }
    
    static double evalPost(String exp) { 
        
        // Scan all characters one by one 
        for(int i=0;i<exp.length();i++) { 
            char c = exp.charAt(i); 
              
            //If char is a number put it on the stack
            if(Character.isDigit(c)) 
                postStack.push(c - '0'); 
              
            // Grab elements from stack and apply the operator 
            else { 
                double val1 = postStack.pop(); 
                double val2 = postStack.pop(); 
                  
                switch(c) { 
                    case '+': 
                        postStack.push(val2+val1); 
                        break;                  
                    case '-': 
                        postStack.push(val2- val1); 
                        break;                      
                    case '/': 
                        postStack.push(val2/val1); 
                        break;                      
                    case '*': 
                        postStack.push(val2*val1); 
                        break; 
                } 
            } 
        } 
        return postStack.pop();     
    } 

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 1;
        
        //simple menu for testing purposes
        System.out.println("Menu: (Press button to do action) \n1  to push int \n"
                + "2  to pop top \n3  display min. \n4  display top \n"
                + "5  to evaluate expression \n6  to evaluate postfix\n-1 to exit"); 
        while(n > 0){
            n = in.nextInt();
            switch (n) {
            case 1:
                System.out.println("Enter Value: ");
                minstack.push(in.nextInt()); 
                break;
            case 2:
                minstack.pop(); 
                break;
            case 3:
                System.out.println(minstack.min());
                break;
            case 4:
                System.out.println(minstack.top());
                break;
            case 5:
                in.nextLine();
                System.out.print("Enter Expression (eg. 4 - 3 * 2 + 7): ");
                System.out.println(evalExpression(in.nextLine() + " + 0"));
                break;
            case 6:
                in.nextLine();
                System.out.print("Enter Postfix Expression (eg. 52+83-*4/): ");
                System.out.println(evalPost(in.nextLine()));
                break;
            }
            System.out.print("Menu: ");
        }
        System.out.println("Thank You!");
    }
}

//fix eval for inequalities
//fix double/float for postfix