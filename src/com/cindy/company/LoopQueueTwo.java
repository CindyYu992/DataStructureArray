package com.cindy.company;

public class LoopQueueTwo<E> implements QueueInterface<E> {
    private E[] data;//底层用数组实现
    private int capacity;//循环队列容量
    private int front; //队首标识
    private int tail; // 队尾标识
    private int size; //队列中元素的个数

    public LoopQueueTwo(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
        this.capacity = capacity;
    }
    public LoopQueueTwo() {

    }
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void enqueue(E e) {
        if (front == tail && size != 0) {
            //队列满了，需要扩容
            reSize(getCapacity() * 2);
        }
        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length;
        System.out.println("tail=" + tail);
    }

    private void reSize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        //把原来队列里的元素放到对应的新队列里----原队首要放到新队的0号位置
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        front = 0;
        tail = data.length;
        data = newData;
        System.out.println("new tail=" + tail);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        front = (front + 1) % getSize();
        //缩容
        if (data.length == getCapacity() / 4 && getCapacity() / 2 != 0) {
            reSize(getCapacity() / 2);
        }
        size--;
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (data.length == 0);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue:size = %d,capacity = %d, length = %d\n front = %d,tail = %d \n", size, getCapacity(), data.length, front, tail));

        res.append("front[");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
//        for (int i = 0; i < size; i++) {
//            res.append(data[(i + front) % data.length]);
//
//            if ((i + front + 1) % size != (tail % size)) {
//
//                res.append(", ");
//
//            }
//        }
        res.append("]tail");
        return res.toString();
    }
}
