package lab.singleList.classes.logic;

import lab.singleList.interfaces.Comparator;
import lab.singleList.interfaces.TypeBuilder;

import java.security.SecureRandom;
import java.util.Random;

public class StringBuilder implements TypeBuilder {

    public static int codeLength = 8;

    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public Object create() {

        int min = 65;// A
        int max = 90;// Z

        java.lang.StringBuilder sb = new java.lang.StringBuilder(codeLength);

        Random random = new SecureRandom();

        for (int i = 0; i < codeLength; i++) {

            Character c;

            do {

                c = (char) (random.nextInt((max - min) + 1) + min);

            } while (sb.indexOf(c.toString()) > -1);

            sb.append(c);
        }

        String output = sb.toString();
        return output;

    }

    @Override
    public Comparator getComparator() {
        return ((o1, o2) -> ((String)o1).compareTo((String)o2));
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void write() {

    }

}
