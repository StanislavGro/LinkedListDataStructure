package lab.singleList.interfaces;

public interface TypeBuilder {

    String typeName();
    Object create(int range);
    lab.singleList.interfaces.Comparator getComparator();
    Object read();
    void write();

}
