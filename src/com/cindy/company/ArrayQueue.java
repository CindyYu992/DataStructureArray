package com.cindy.company;

public class ArrayQueue<E> implements QueueInterface<E> {
    private DynamicArray<E> dynamicArray;
    public ArrayQueue(){
        dynamicArray = new DynamicArray<>();
    }

    public ArrayQueue(DynamicArray dynamicArray) {
        this.dynamicArray = dynamicArray;
    }

    public ArrayQueue(int capacity){
        dynamicArray = new DynamicArray<E>(capacity);
    }

    @Override
    public void enqueue(E e) {
        dynamicArray.addLast(e);
    }

    @Override
    public E dequeue() {
        return dynamicArray.removeFirst();
    }

    @Override
    public E getFront() {
        return dynamicArray.getFirst();
    }

    @Override
    public int getSize() {
        return dynamicArray.getSize();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue:size = %d,", dynamicArray.getSize()));
        res.append("front[");
        for (int i = 0; i < dynamicArray.getSize(); i++) {
            res.append(dynamicArray.get(i));
            if (i !=  dynamicArray.getSize() - 1)
                res.append(", ");
        }
        res.append("]tail");
        return res.toString();
    }
}
