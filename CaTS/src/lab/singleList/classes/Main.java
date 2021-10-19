package lab.singleList.classes;

import lab.singleList.interfaces.TypeBuilder;

import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        TypeBuilder integerTypeBuilder = TypeFactory.getBuilder("Integer");
        TypeBuilder stringTypeBuilder = TypeFactory.getBuilder("String");

        System.out.println(stringTypeBuilder.create(5));
        System.out.println(integerTypeBuilder.create(16));

        /*
        //Список с экземпляром класса
        Random randomGenerator = new Random();

        singleList<exampleClass> list = new singleList<>();

        for (int i = 0; i < 8; i++)
            list.add(new exampleClass(randomGenerator.nextInt(100) + 1),i);

        System.out.println("\n** Результат до сортировки **\n");

        for(exampleClass c: list){
            System.out.println(c.i);
        }

        System.out.println("\n** Результат после сортировки **\n");

        list.sort(0, list.getSize()-1); //ОБЯЗАТЕЛЬНО size-1 если сортируем до последнего элемента

        for(exampleClass c: list){
            System.out.println(c.i);
        }

        System.out.println();

        //Список с ссылочным типоп

        singleList<String> strList = new singleList<>();

        strList.addFirst("You are Cool");

        System.out.println("Размер строкового списка: " + strList.getSize());

        strList.add("I LOVE YOU", 15); //вышли за размер - исключение

        System.out.println("Размер списка:" + strList.getSize());

        System.out.println("Значение списка с индексом 0: " + strList.getElemByIndex(0));
        System.out.println("Значение списка с индексом 1: " + strList.getElemByIndex(1));

        System.out.println("А теперь пуст? " + strList.isEmpty());

        strList.add("Ur the best man", 1);
        strList.add("You will succeed", 3);

        System.out.println("\n*Финальные значения списка*");

        System.out.println("Прямой итератор:\n");

        for(String s: strList){
            System.out.println(s);
        }

        System.out.println();

        System.out.println("Обратный итератор:\n");

        Iterator descendingIterator = strList.descendingIterator();

        while (descendingIterator.hasNext())
            System.out.println(descendingIterator.next());

        System.out.println("\n*Удаления*");

        strList.delete(0);

        System.out.println("Размер списка: " + strList.getSize());

         */
    }


}
