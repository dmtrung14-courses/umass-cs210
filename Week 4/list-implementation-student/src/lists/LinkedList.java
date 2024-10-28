/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class LinkedList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Node<E> n = head;
        while (n != null) {
            result = prime * result + head.data.hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List other = (List) obj;
        if (size != other.size()){
            return false;
        }
        else{
            Node<E> a = this.head;
            int cur = 0;
            while (cur < this.size()){
                if (!(a.data.equals(other.get(cur)))){
                    return false;
                }
                cur += 1;
                a = a.next;
            }
        }
        // TODO before returning true, make sure each element of the lists are equal!
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = this.head;
        for (int i = 1; i <= index; i++){
            result= result.next;
        }
        return result.data;
    }
    
    @Override
    public void add(E e) {
        Node<E> node = new Node<>();
        node.data = e;
        if (this.size() == 0){
            this.head = node;
            this.tail = node;
        }
        else{
            this.tail.next = node;
            this.tail = node;
        }
        this.size += 1;

    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index > this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = new Node<>();
        node.data = e;
        if (this.size() == 0){
            this.head = node;
            this.tail = node;
        }
        else if(index ==0){
            Node<E> temp = this.head;
            this.head = node;
            node.next = temp;
        }
        else if(index == this.size()){
            this.tail.next = node;
            this.tail = node;
        }
        else{
            Node<E> curr = this.head;
            for (int i = 1; i < index; i ++ ){
                curr = curr.next;
            }
            Node<E> temp = curr.next;
            curr.next = node;
            node.next = temp;
        }
        this.size += 1; 
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = this.head;
        Node<E> result = null;
        for (int i = 1; i < index; i ++){
            curr = curr.next;
        }
        if (index == 0){
            result = curr;
            this.head = this.head.next;
        }
        else if (index == this.size() -1){
            result = curr.next;
            curr.next= null;
            this.tail = curr;
        }
        else{
            result= curr.next;
            curr.next = curr.next.next;
            
        }
        this.size --;
        return result.data;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index >= this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = this.head;
        for(int i = 1; i <= index; i ++ ){
            curr = curr.next;
        }
        curr.data = e;
        return e;
    }

    @Override
    public int indexOf(E e) {
        
        int count = 0;
        Node<E> curr = this.head;
        while (curr != null){
            if (curr.data.equals(e)){
                return count;
            }
            else{
                count += 1;
                curr = curr.next;
            }
        }
        return -1;

    }
    public Node<E> getHead(){
        return this.head;
    }
}
