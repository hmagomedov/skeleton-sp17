import java.io.*;

public class LinkedListDeque<T> {
    public class Node {
        T val;
        Node prev = null;
        Node next = null;
        public Node(T val, Node prev, Node next){
            this.val = val;
            this.next = next;
            this.prev = prev;
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
        for(int i = 0; i < size; i++){
            System.out.print(curr.val);
            System.out.print(" ");
            curr = curr.next;
        }
    }

    public T removeFirst(){
        Node firstNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return firstNode.val;
    }

    public T removeLast(){
        Node lastNode = sentinel.prev;
        sentinel.prev = sentinhel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return lastNode.val;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        Node curr = sentinel.next;
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr.val;
    }

    public static void main(String[] Args){
        LinkedListDeque<Integer> list = new LinkedListDeque<Integer>();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.removeLast();
        list.printDeque();
    }
}