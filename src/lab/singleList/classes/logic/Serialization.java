package lab.singleList.classes.logic;

import java.io.Serializable;

public class Serialization implements Serializable {

    private static final long serialVersionUID = 1L;

    private singleList list;

    public Serialization(singleList list){
        this.list = list;
    }

    public singleList getList() {
        return list;
    }
}
