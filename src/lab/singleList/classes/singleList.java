package lab.singleList.classes;

import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.someAction;
import lab.singleList.interfaces.Linked;

public class singleList<E> implements Linked<E>{

    private Node first;   //первый узел
    private Node last;    //последний узел
    private Node current; //текущий узел

    private int size = 0;

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    //конструктор
    public singleList(){
        current = new Node(null, null);
        first = current;
        last = current;
    }

    //поиск элемента по индексу
    @Override
    public E getElemByIndex(int index) {

        if(index>=size)
            throw new NullPointerException("Вы вышли за пределы индексирования!");
        else {
            current = first;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            return current.elem;
        }

    }

    //вставка в начало
    @Override
    public void addFirst(E elem) {

        if(size == 0)
            this.current.elem = elem;
        else
            first = new Node(elem, this.first);

        size++;

    }

    //вставка в конец
    @Override
    public void addLast(E elem) {

        if(size == 0)
            this.current.elem = elem;
        else {
            Node newLastNode = new Node(elem, null);
            this.last.next = newLastNode;
            this.last = newLastNode;
        }

        size++;
    }

    //вставка по порядковому номеру (индексу)
    @Override
    public void add(E elem, int index) {

        if(index>=size){

            if (index > size)
                System.out.println("Вы вышли за размер списка! Поэтому добавим элемент просто в конец!");

            this.addLast(elem);

        }
        else if (index == 0)
            this.addFirst(elem);
        else {

            Node temp;
            current = first;

            for(int i = 0; i < index-1; i++)
                current = current.next;

            temp = new Node(elem, current.next);

            current.next = temp;

            size++;
        }

    }

    //удаление по порядковому номеру (индексу)
    @Override
    public void delete(int index) {

        if(size != 0) {
            if (index >= size)
                throw new NullPointerException("Вы вышли за пределы индексирования! Невозможно удалить этот элемент");
            else if (index == 0) {
                if(size == 1)
                    current.elem = null;
                else {
                    first = first.next;
                    current = first;
                }
            } else {

                current = first;

                for (int i = 0; i < index-1; i++)
                    current = current.next;

                current.next = (current.next).next;

            }

            size--;
        }
        else
            throw new NullPointerException("Массив абослютно пуст!!!");
    }

    //проверка на пустоту
    public boolean isEmpty(){
        return (size==0);
    }

    //геттер размера списка
    @Override
    public int getSize() {
        return size;
    }

    //замена индексного элемента
    @Override
    public void setElemByIndex(E elem, int index) {
        if(index>=size || index < 0)
            throw new NullPointerException("Вы вышли за пределы индексирования!");
        else {
            current = first;
            for(int i = 0; i < index; i++)
                current = current.next;
            current.elem = elem;
        }
    }

    public void sort(Comparator comparator) {
        first = mergeSort(first, comparator);
        last = getNode(size - 1);
    }

    private Node mergeSort(Node h, Comparator comparator)
    {
        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node middleNext = middle.next;

        middle.next = null;

        Node left = mergeSort(h, comparator);

        Node right = mergeSort(middleNext, comparator);

        return sortedMerge(left, right, comparator);
    }

    private Node sortedMerge(Node a, Node b, Comparator comparator)
    {
        Node result;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (comparator.compare(a.elem, b.elem) <= 0) {
            result = a;
            result.next = sortedMerge(a.next, b, comparator);
        }
        else {
            result = b;
            result.next = sortedMerge(a, b.next, comparator);
        }
        return result;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node tmp = first;

        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    private Node getMiddle(Node h)
    {
        if (h == null)
            return null;
        Node fast = h.next;
        Node slow = h;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public void forEach(someAction<E> someAction) {
        Node temp = first;
        for(int i = 0; i < size; i++) {
            someAction.toDo(temp.elem);
            temp = temp.next;
        }
    }

    //класс узла списка
    private class Node {

        private E elem;
        private Node next;

        //конструктор
        private Node(E elem, Node next){
            this.elem = elem;
            this.next = next;
        }

        //геттер элемента
        public E getElem() {
            return elem;
        }

        //сеттер элемента
        public void setElem(E elem) {
            this.elem = elem;
        }

        //геттер узла
        public Node getNextNode(){
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "elem=" + elem +
                    '}';
        }
    }

}