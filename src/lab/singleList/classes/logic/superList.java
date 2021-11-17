package lab.singleList.classes.logic;

import lab.singleList.interfaces.Comparator;

public class superList {

    superElem first = null, last = null;

    public void create(singleList inputList){
        for(singleList.Node p = inputList.first; p != null; p = p.next)
            add(new singleList<>(p.elem));
    }

    public void add(singleList list){
        if (first == null)
            first = last = new superElem(list);
        else{
            last.next = new superElem(list);
            last = last.next;
        }
    }

    public singleList sort(singleList list, Comparator comparator){

        this.create(list);

        do {

            superElem temp = first;

            while(first != null && first.next != null){
                first.data = first.data.merge(first.next.data,comparator);
                first.next = first.next.next;
                first = first.next;
            }

            last = first;
            first = temp;

        } while (first.next != null);

        return first.data;
    }

    public class superElem{

        public singleList data;
        public superElem next;

        public superElem(singleList data) {
            this.data = data;
            this.next = null;
        }
    }

}
