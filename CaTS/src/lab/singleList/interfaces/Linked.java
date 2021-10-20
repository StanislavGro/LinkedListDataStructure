package lab.singleList.interfaces;

import lab.singleList.classes.singleList;

import java.util.function.Consumer;

public interface Linked<E>{

    void addFirst(E elem);
    void addLast(E elem);
    void add(E elem, int index);
    void delete(int index);

    int getSize();

    boolean isEmpty();

    E getElemByIndex(int index);
    void setElemByIndex(E elem, int index);

}
