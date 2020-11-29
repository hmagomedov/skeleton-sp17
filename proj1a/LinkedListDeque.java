import java.io.*;

public class LinkedListDeque<T> {
    public static class Node {
        T val = 0;
        Node prev = null;
        Node next = null;
        public Node(T val, Node prev, Node next){
            this.val = val;
            this.next = next;
        }
    }

    int size = 0;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T x) {
        Node newFirst = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
        size++;
    }

    public void addLast(T x){
        Node newLast = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node curr = sentinel.next;
        while(curr){
            System.out.print(curr.val);
            System.out.print(" ");
            curr = curr.next;
        }
    }

    public Item removeFirst(){
        firstNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return firstNode;
    }

    public Item removeLast(){
        lastNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return lastNode;
    }

    public Item get(int index){
        if(index >= size){
            return null;
        }
        Node curr = sentinel.next;
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr;
    }
}