package lab.singleList.classes.logic;

import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.someAction;
import lab.singleList.interfaces.Linked;

import java.io.Serializable;

public class singleList<E> implements Linked<E>, Serializable {

    public Node first;   //первый узел
    public Node last;    //последний узел
    public Node current; //текущий узел

    private int size = 0;

    //конструктор
    public singleList(){
        current = new Node(null, null);
        first = current;
        last = current;
    }

    public singleList(E elem){
        current = new Node(null, null);
        first = current;
        last = current;
        addLast(elem);
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

            while (current.next!=null)
                current = current.next;

            last = current;

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

    public void setNewSize() {

        size = 0;
        current = first;

        while(current!=null){
            size++;
            current = current.next;
        }
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

    public singleList merge(singleList secondList, Comparator comparator){

        singleList out = new singleList();

        while (first != null && secondList.first != null){
            if (comparator.compare(first.elem, secondList.first.elem) < 0){
                out.addLast(first.elem);
                first = first.next;
            }
            else{
                out.addLast(secondList.first.elem);
                secondList.first = secondList.first.next;
            }
        }
        while(first != null){
            out.addLast(first.elem);
            first = first.next;
        }
        while(secondList.first != null){
            out.addLast(secondList.first.elem);
            secondList.first = secondList.first.next;
        }
        return out;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node tmp = first;

        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public void forEach(someAction<E> someAction) {

        if(size != 0) {
            Node temp = first;
            for (int i = 0; i < size; i++) {
                someAction.toDo(temp.elem);
                temp = temp.next;
            }
        }
        else
            System.out.println("**Пусто**");
    }

    //класс узла списка
    public class Node implements Serializable {

        public E elem;
        public Node next;

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
/*
    public void sort(Comparator comparator) {
        first = mergeSort(first, comparator);
        last = getNode(size - 1);
    }

 */
/*
    private Node mergeSort(Node h, Comparator comparator) {
        if(h == null)
            return null;
        else if(h.next==null)
            return h;

        Node a = h, newH = null, Pair = null;
        int number = 1;

        while(number<size) {

            for (int i = 0; i < size; i=i+2*number) {
                if(newH!=null) {
                    if (Pair != null) {
                        Pair = newH;
                        for(int k = 0; k < i-1; k++)
                            Pair = Pair.next;
                        Pair.next = sortIt(a, i, number, comparator); //сравнивает пары и создает последовательность отсортированных узлов
                    }
                    else {
                        Pair = sortIt(a, i, number, comparator); //сравнивает пары и создает последовательность отсортированных узлов
                        newH = Pair;
                    }
                    Pair = Pair.next;
                }
                else {
                    newH = sortIt(a, i, number, comparator); //сравнивает пары и создает последовательность отсортированных узлов
                    Pair = newH;
                }
            }

            //newH = Pair;
            number*=2;
            a=newH;
            Pair = null;
        }

        return newH;

    }
*/
/*
    private Node sortIt(Node a, int iter,int number, Comparator comparator){

        int temp = iter, temp2 = number, aNum = 0, bNum = 0;

        while(a!=null && temp!=0){
            a = a.next;
            temp-=1;
        }

        Node b = a, result = null, resultLink = null;

        while(b!=null && temp2!=0){
            b = b.next;
            temp2-=1;
        }

        if(b !=null) {
            while (true) {
                if (aNum != number && bNum != number && a != null && b!=null) {
                    if (comparator.compare(a.elem, b.elem) <= 0) {
                        if (result != null) {
                            resultLink.next = new Node(a.elem, null);
                            resultLink = resultLink.next;
                        }
                        else {
                            result = new Node(a.elem, null);
                            resultLink = result;
                        }
                        aNum += 1;
                        a = a.next;
                    } else {
                        if (result != null){
                            resultLink.next = new Node(b.elem, null);
                            resultLink = resultLink.next;
                        }
                        else {
                            result = new Node(b.elem, null);
                            resultLink = result;
                        }
                        bNum += 1;
                        b = b.next;
                    }
                } else if (aNum != number && a!=null) {
                    resultLink.next = new Node(a.elem,null);
                    resultLink = resultLink.next;
                    a = a.next;
                    aNum+=1;
                } else if (bNum != number && b != null) {
                    resultLink.next = new Node(b.elem,null);
                    resultLink = resultLink.next;
                    b = b.next;
                    bNum+=1;
                } else
                    break;
            }
        }
        else{
            while (aNum != number && a!=null){
                if (result != null) {
                    resultLink.next = new Node(a.elem, null);
                    resultLink = resultLink.next;
                }
                else {
                    result = new Node(a.elem, null);
                    resultLink = result;
                }
                aNum += 1;
                a = a.next;
            }
        }

        return result;
    }
     */