package lab.singleList.classes;

import lab.singleList.interfaces.TypeBuilder;

import java.util.HashMap;
import java.util.Set;

public class TypeFactory {

    public static final HashMap<String, TypeBuilder> typeFactory;

    static {

        typeFactory = new HashMap<>();
        typeFactory.put("String", new StringBuilder());
        typeFactory.put("Integer", new IntegerBuilder());

    }

    public static Set<String> getAllTypes() {
        return typeFactory.keySet();
    }

    public static TypeBuilder getBuilder(String name) {
        return typeFactory.get(name);
    }


}
