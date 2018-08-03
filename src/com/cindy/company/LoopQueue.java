package com.cindy.company;

public class LoopQueue<E> implements QueueInterface<E> {
    private E[] data;
    private int front, tail;
    //循环队列中的元素个数
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            //队列满了，需要扩容
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        //把原有的队列中的元素放在新的扩容后的队列里
        for (int i = 0; i < size; i++) {
            //旧的队首元素的位置相对新队队首位置有front个偏移，并且要和旧队列长度取模后才能得到在新的队列里的位置
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        return ret;
    }

    @Override
    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d,capacity = %d \n front = %d,tail = %d \n", size, getCapacity(),front,tail));

        res.append("front[");
//        for (int i = front; i != tail; i = (i + 1) % data.length) {
//            res.append(data[i]);
//            if ((i + 1) % data.length != tail)
//                res.append(", ");
//        }
        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);
            if ((i + front) % size != ((tail-1 ) % size)) {
                res.append(",");
            }
        }
        res.append("]tail");
        return res.toString();
    }
}
