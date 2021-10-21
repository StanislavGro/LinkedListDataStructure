package lab.singleList.classes.logic;


import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.TypeBuilder;

public class IntegerBuilder implements TypeBuilder {

    public static int range = 100;

    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
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
