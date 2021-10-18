package lab.singleList.interfaces;

import java.util.Comparator;

public interface TypeBuilder {

    String typeName();
    Object create();
    Object read();
    Comparator getComparator();

}
