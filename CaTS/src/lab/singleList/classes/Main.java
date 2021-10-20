package lab.singleList.classes;

import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.TypeBuilder;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        IntegerBuilder.range = 100;
        StringBuilder.codeLength = 7;

        TypeBuilder integerTypeBuilder = TypeFactory.getBuilder("Integer");
        TypeBuilder stringTypeBuilder = TypeFactory.getBuilder("String");

        if(integerTypeBuilder == null || stringTypeBuilder == null) return;

        singleList<Object> integerList = new singleList<>();

        for (int i = 0; i < 10; i++)
            integerList.addLast(integerTypeBuilder.create());

        System.out.println("Before quickSort:");
        integerList.forEach(System.out::println);
        System.out.println();

        integerList.sort(integerTypeBuilder.getComparator());

        System.out.println("After quickSort:");
        integerList.forEach(System.out::println);
        System.out.println();
    }


}
