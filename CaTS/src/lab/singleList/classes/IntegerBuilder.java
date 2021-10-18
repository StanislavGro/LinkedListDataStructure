package lab.singleList.classes;

import lab.singleList.interfaces.TypeBuilder;

import java.util.Comparator;
import java.util.Random;

public class IntegerBuilder implements TypeBuilder {

    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
        return new Random().nextInt(100);
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public Comparator getComparator() {
        return (o1,o2)->(Integer)o1-(Integer)o2;
    }

}
