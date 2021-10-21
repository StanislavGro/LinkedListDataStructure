package lab.singleList.classes.logic;

import lab.singleList.classes.gui.GUI;
import lab.singleList.interfaces.TypeBuilder;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new GUI();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n*Односвязный список*\n\n");

        System.out.print("Выберите тип данных, с которым будете работать: ");
        String type = scanner.nextLine();

        TypeBuilder builder = TypeFactory.getBuilder(type);

        singleList<Object> list = new singleList<>();

        cli();

        loop:

        while(true){

            System.out.print("Выберите функцию: ");
            int numFunk = scanner.nextInt();

            switch (numFunk) {

                case(1):

                    System.out.print("Выберите кол-во объектов: ");
                    int number = scanner.nextInt();

                    for (int i = 0; i < number; i++)
                        list.addLast(builder.create());

                    System.out.println("**Успешно**");

                    break;
                case(2):

                    System.out.print("Введите индекс: ");
                    int index = scanner.nextInt();

                    if(type.equals("Integer")) {
                        System.out.print("Введите число: ");
                        Integer num = scanner.nextInt();
                        list.add(num,index);
                    }
                    else if(type.equals("String")){
                        System.out.print("Введите строку: ");
                        String temp = scanner.nextLine();
                        String str = scanner.nextLine();
                        list.add(str,index);
                    }
                    else {
                        System.out.print("Вы ввели нерпавильный тип данных: ");
                        break loop;
                    }

                    break;
                case(3):

                    if(type.equals("Integer")) {
                        System.out.print("Введите число: ");
                        Integer num = scanner.nextInt();
                        list.addLast(num);
                    }
                    else if(type.equals("String")){
                        System.out.print("Введите строку: ");
                        String temp = scanner.nextLine();
                        String str = scanner.nextLine();
                        list.addLast(str);
                    }
                    else {
                        System.out.print("Вы ввели нерпавильный тип данных: ");
                        break loop;
                    }
                    break;
                case(4):

                    System.out.print("Номер объекта, который нужно удалить: ");
                    int deleteNum = scanner.nextInt();

                    list.delete(deleteNum);

                    break;
                case(5):

                    System.out.print("Индекс объекта, которого нужно найти: ");
                    int findNum = scanner.nextInt();

                    System.out.println("Найденный объект: " + list.getElemByIndex(findNum));

                    break;
                case(6):
                    list.sort(builder.getComparator());
                    break;
                case(7):

                    if(list==null)
                        System.out.println("Ошибка! Список пуст! Сохранять нечего!");
                    else {
                        ObjectOutputStream oos = null;

                        try {
                            FileOutputStream fos = new FileOutputStream("cliFile.data");
                            if(fos!=null) {
                                oos = new ObjectOutputStream(fos);
                                Serialization serialization = new Serialization(list);
                                oos.writeObject(serialization);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if(oos!=null){
                                try {
                                    oos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                    }


                    break;
                case(8):

                    try {
                        FileInputStream fis = new FileInputStream("cliFile.data");
                        ObjectInputStream ois = new ObjectInputStream(fis);

                        Serialization serialization = (Serialization) ois.readObject();

                        list = serialization.getList();

                    }catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case(9):
                    list.forEach(System.out::println);
                    break;
                case(10):
                    break loop;
                default:
                    System.out.println("Повторите попытку!");
                    break;


            }
        }

        scanner.close();

    }

    private static void cli(){

        System.out.println("\nСписок функций:");
        System.out.println("1. Сгенерировать случайные объекты");
        System.out.println("2. Вставить объект в произвольное место");
        System.out.println("3. Вставить объект в конец");
        System.out.println("4. Удалить объект по номеру");
        System.out.println("5. Найти объект по номеру");
        System.out.println("6. Отсортировать список");
        System.out.println("7. Сохранить данные");
        System.out.println("8. Загрузить данные");
        System.out.println("9. Вывести все объекты");
        System.out.print("10. Выход\n\n");

    }

}
