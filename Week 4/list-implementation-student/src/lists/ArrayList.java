/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class ArrayList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    E[] array;
    int size;

    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + array[i].hashCode();
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
            for (int i = 0; i < size; i++){
                if (!(this.get(i).equals(other.get(i)))){
                    return false;
                }
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
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return this.array[index];
    }


    @Override
    public void add(E e) {
        if(this.size() +1 > this.array.length){
            E[] newarray = (E[]) new Object[2*this.size()];
            for (int i = 0; i < this.size(); i ++){
                newarray[i] = this.array[i];
            }
            this.array = newarray;
        }
        this.array[this.size()]=e;
        this.size += 1;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size() + 1 > this.array.length) {
            E[] newarray = (E[]) new Object[2 * this.size()];
            for (int i = 0; i < this.size(); i++) {
                newarray[i] = this.array[i];
            }
            this.array = newarray;
        }
        for (int i = this.size() -1; i >= index; i--){
            this.array[i+1] = this.get(i);
        }
        this.array[index] = e;
        this.size +=1;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E result = this.get(index);
        for (int i = this.size() -1; i > index; i--){
            this.set(i-1, this.get(i));
        }
        this.set(this.size()-1, null);
        this.size --;
        return result;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.array[index] = e;
        return e;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < this.size(); i ++){
            if (this.get(i).equals(e)){
                return i;
            }
    }
        return -1;

    }
}