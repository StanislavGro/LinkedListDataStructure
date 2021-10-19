package lab.singleList.classes;


import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.TypeBuilder;

import java.util.Random;

public class IntegerBuilder implements TypeBuilder {

    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create(int range) {

        return (int)(Math.random()*range+1);
    }

    @Override
    public Comparator getComparator() {
        return ((o1, o2) -> (Integer)o1-(Integer)o2);
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void write() {

    }
}
