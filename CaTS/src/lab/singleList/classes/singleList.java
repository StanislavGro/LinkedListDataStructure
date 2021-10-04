package lab.singleList.classes;

import lab.singleList.interfaces.DescendingIterator;
import lab.singleList.interfaces.Linked;

import java.util.Iterator;

public class singleList<E extends Comparable<E>> implements Linked<E>, Iterable<E>, DescendingIterator<E>{

    private Node<E> first;   //первый узел
    private Node<E> last;    //последний узел
    private Node<E> current; //текущий узел

    private int size = 0;

    //конструктор
    public singleList(){
        current = new Node<>(null, null);
        first = current;
        last = current;
    }

    //итератор по возрастанию
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            int counter = 0;

            //проверка на наличие следующего элемента
            @Override
            public boolean hasNext() {
                return this.counter < size;
            }

            //получение следующего элемента списка
            @Override
            public E next() {
                return getElemByIndex(counter++);
            }
        };
    }

    //итератор по убыванию
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<>() {
            int counter = size - 1;

            //проверка на наличие следующего элемента
            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            //получение следующего элемента списка
            @Override
            public E next() {
                return getElemByIndex(counter--);
            }
        };
    }

    //класс узла списка
    private class Node<E extends Comparable<E>> implements Comparable<E>{

        private E elem;
        private Node<E> next;

        //конструктор
        private Node(E elem, Node<E> next){
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
        public int compareTo(E o) {
            return elem.compareTo(o);
        }
    }

    //добавление в начало
    @Override
    public void addFirst(E elem) {
        if(size == 0)
            this.current.setElem(elem);
        else
            first = new Node<>(elem, this.first);

        size++;
    }

    //добавление в конец
    @Override
    public void addLast(E elem) {
        if(size == 0)
            this.current.setElem(elem);
        else {
            Node<E> newLastNode = new Node<>(elem, null);
            this.last.next = newLastNode;
            this.last = newLastNode;
        }

        size++;
    }

    //добавление по порядковому номеру (индексу)
    @Override
    public void add(E elem, int index) {

        if(index>size){
            System.out.println("Вы вышли за размер списка! Поэтому добавим элемент просто в конец!");
            this.addLast(elem);
        }
        else if (index == 0)
            this.addFirst(elem);
        else if (index == size)
            this.addLast(elem);
        else {
            Node<E> current2;
            Node<E> newNodeElem = new Node<>(elem, null);

            current = first;

            for(int i = 0; i < index-1; i++){
                current = current.getNextNode();
            }

            current2 = current.next;

            current.next = newNodeElem;
            newNodeElem.next = current2;

            size++;
        }

    }

    //удаление по порядковому номеру (индексу)
    @Override
    public void delete(int index) {

        if(index>=size)
            throw new NullPointerException("Вы вышли за пределы индексирования! Невозможно удалить этот элемент");
        else if (index == 0)
            first = first.next;
        else {
            Node<E> current2;

            current = first;

            for(int i = 0; i < index-1; i++){
                current = current.getNextNode();
            }

            current2 = current.next.next;

            current.next = current2;
        }

        size--;
    }

    //геттер размера списка
    @Override
    public int getSize() {
        return size;
    }

    //проверка на пустоту
    public boolean isEmpty(){
        return (size==0);
    }

    //сортировка
    @Override
    public void sort(int low, int high) {

        if (size == 0)
            return;//завершить выполнение, если длина списка равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        E e = this.getElemByIndex(middle);

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
/*
        if (this.getElemByIndex(i).compareTo(e)==1) {
            i++;
        }

 */

        while (i <= j) {
            while (this.getElemByIndex(i).compareTo(e)<0) {
                i++;
            }

            while (this.getElemByIndex(j).compareTo(e)>0) {
                j--;
            }

            if (i <= j) {//меняем местами

                Node<E> temp = new Node<>(this.getElemByIndex(i),null);
                this.setElemByIndex(this.getElemByIndex(j),i);
                this.setElemByIndex(temp.getElem(),j);

                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            sort(low, j);

        if (high > i)
            sort(i, high);

    }

    //получение элемента по индексу из списка
    @Override
    public E getElemByIndex(int index) {

        if(index>=size)
            throw new NullPointerException("Вы вышли за пределы индексирования!");
        else {
            current = first;
            for(int i = 0; i < index; i++){
                current = current.getNextNode();
            }
            return current.getElem();
        }

    }

    //замена индексного элемента
    @Override
    public void setElemByIndex(E elem, int index) {
        if(index>=size || index < 0)
            throw new NullPointerException("Вы вышли за пределы индексирования!");
        else {
            current = first;
            for(int i = 0; i < index; i++){
                current = current.getNextNode();
            }
            current.setElem(elem);
        }
    }

}