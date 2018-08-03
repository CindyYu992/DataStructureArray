package com.cindy.company;

public class DynamicArray<E> {
    private E[] data;
    private int size;//指向第一个没有元素的空位置

    public DynamicArray(E[] data, int size) {
        this.data = data;
        this.size = size;
    }

    /**
     * @param capacity 构造函数，传入数组的容量capacity构造Array
     */
    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认数组的容量capacity=10
     */
    public DynamicArray() {
        this(10);
    }

    /**
     * 获取数组中的元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     **/
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     **/
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向所有元素后添加一个元素
     **/
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向所有元素前添加一个元素
     **/
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向数组的第index位置添加一个元素
     **/
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,index is illegal,required:index>=0 && index<=size");

        //如果数组控件满了，进行扩容操作resize
        if (size == data.length) resize(2 * data.length);

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 数组扩容
     **/
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d,capacity = %d \n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed,index is illegal,required:index>=0 && index<=size");
        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed,index is illegal,required:index>=0 && index<=size");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     **/
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e) || data[i] == e) return true;
        }
        return false;
    }

    /**
     * 查找数组中是否有元素e,返回索引,如果不存在则返回-1
     **/
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) return i;
        }
        return -1;
    }

    /**
     * 删除指定索引index位置的元素
     * 返回被删除的元素e
     **/
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed,index is illegal,required:index>=0 && index<=size");
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //把被删除的位置置空。
        data[size] = null;//Loitering objects.在程序中闲逛的对象。

        if (size == data.length / 4){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素，返回删除的元素
     **/
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素，返回删除的元素
     **/
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 查看数组中是否包含某元素e，如果包含则删除
     **/
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }
    public E getLast(){
        return get(size-1);
    }
    public E getFirst(){
        return get(0);
    }
}
