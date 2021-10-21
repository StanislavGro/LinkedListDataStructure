package lab.singleList.classes.logic;

import lab.singleList.interfaces.TypeBuilder;

import java.util.HashMap;
import java.util.Set;

public class TypeFactory {

    public static final HashMap<String, TypeBuilder> typeFactory; //мапа хранения типов данных и соответсвующих билдеров

    static {

        typeFactory = new HashMap<>();
        typeFactory.put("String", new StringBuilder());
        typeFactory.put("Integer", new IntegerBuilder());

    }

    //Возвращает множество типов данных
    public static Set<String> getAllTypes() {
        return typeFactory.keySet();
    }

    //
    public static TypeBuilder getBuilder(String name) {
        return typeFactory.get(name);
    }


}
