package lab.singleList.interfaces;

public interface TypeBuilder {
    String typeName();
    Object create();
    lab.singleList.interfaces.Comparator getComparator();
}
