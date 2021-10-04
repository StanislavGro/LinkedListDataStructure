package lab.singleList.classes;

//ЭТОТ КЛАСС ПРОСТО ПРИМЕР И СЛУЖИТ ДЛЯ ПРОВЕРКИ ДОБАВЛЕНИЯ ЭЛЕМЕНТОВ С СПИСОК 

public class exampleClass implements Comparable<exampleClass> {

    int i;

    public exampleClass(int i) {
        this.i=i;
    }

    @Override
    public int compareTo(exampleClass o) {
        return this.i-o.i;
    }
}
