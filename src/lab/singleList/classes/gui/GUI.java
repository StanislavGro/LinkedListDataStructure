package lab.singleList.classes.gui;

import lab.singleList.classes.logic.Serialization;
import lab.singleList.classes.logic.singleList;
import lab.singleList.interfaces.TypeBuilder;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class GUI extends JFrame implements KeyListener {

    public dialogWindow dialog;
    public singleList<Object> singleList = new singleList<>();
    public TypeBuilder typeBuilder;

    private JMenuBar jMenuBar;
    private JMenu jFile;
    private JMenu jActions;
    private JMenu jSort;
    private JMenuItem jFSave;
    private JMenuItem jFLoad;
    private JMenuItem jCreate;
    private JMenuItem jInsertByIndex;
    private JMenuItem jInsertInEnd;
    private JMenuItem jDeleteByIndex;
    private JMenuItem jSortList;


    public GUI(){

        super("List");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(1080, 600));

        dialog = new dialogWindow(this);


        jMenuBar = new JMenuBar();
        jFile = new JMenu("File");

        jFSave = new JMenuItem("Save");
        jFLoad = new JMenuItem("Load");
        jFile.add(jFSave);
        jFile.add(jFLoad);
        jFSave.setEnabled(false);
        jMenuBar.add(jFile);

        jActions = new JMenu("Actions");
        jCreate = new JMenuItem("Add random elements");
        jInsertByIndex = new JMenuItem("Insert by Index");
        jInsertInEnd = new JMenuItem("Insert at the end");
        jDeleteByIndex = new JMenuItem("Delete");
        jActions.add(jCreate);
        jActions.add(jInsertByIndex);
        jActions.add(jInsertInEnd);
        jActions.add(jDeleteByIndex);
        jInsertInEnd.setEnabled(false);
        jInsertByIndex.setEnabled(false);
        jDeleteByIndex.setEnabled(false);
        jMenuBar.add(jActions);

        jSort = new JMenu("Sort");
        jSortList = new JMenuItem("Sort");
        jSort.add(jSortList);
        jSort.setEnabled(false);
        jMenuBar.add(jSort);

        setJMenuBar(jMenuBar);
        addKeyListener(this);

        pack();
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        requestFocus();

        jFSave.addActionListener(e -> {

            if(singleList==null)
                System.out.println("Ошибка! Список пуст! Сохранять нечего!");
            else {
                ObjectOutputStream oos = null;

                try {
                    FileOutputStream fos = new FileOutputStream("guiFile.data");
                    if(fos!=null) {
                        oos = new ObjectOutputStream(fos);
                        Serialization serialization = new Serialization(singleList);
                        oos.writeObject(serialization);
                    }
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                finally {
                    if(oos!=null){
                        try {
                            oos.close();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                }


            }

        });

        jFLoad.addActionListener(e -> {
            try {
                FileInputStream fis = new FileInputStream("guiFile.data");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Serialization serialization = (Serialization) ois.readObject();

                singleList = serialization.getList();

            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            }

            jFSave.setEnabled(true);
            jInsertInEnd.setEnabled(true);
            jInsertByIndex.setEnabled(true);
            jDeleteByIndex.setEnabled(true);
            jSort.setEnabled(true);


            System.out.println();
            singleList.forEach(System.out::println);

        });

        jCreate.addActionListener(e -> {

            JDialog jDialog = new JDialog(this, true);

            jDialog.setPreferredSize(new Dimension(250,155));

            JPanel jPanel = new JPanel(new GridLayout(3, 1));


            JLabel textInfo = new JLabel("Введите число объектов", SwingConstants.CENTER);
            JButton btnOK = new JButton("OK");
            JTextArea consoleArea= new JTextArea();
            consoleArea.setLineWrap(true);

            jPanel.add(textInfo);
            jPanel.add(consoleArea);
            jPanel.add(btnOK);
            jDialog.add(jPanel);

            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    {
                        int offset = 0;
                        try {
                            offset = consoleArea.getLineOfOffset(consoleArea.getCaretPosition());
                            int start = consoleArea.getLineStartOffset(offset);
                            int end = consoleArea.getLineEndOffset(offset);
                            String command = consoleArea.getText(start, end - start);
                            offset = Integer.parseInt(command);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }

                        for (int i = 0; i < offset; i++)
                            singleList.addLast(typeBuilder.create());


                        jDialog.dispose();

                    }
                }
            });

            jDialog.pack();
            jDialog.setResizable(false);
            jDialog.setDefaultCloseOperation(0);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jFSave.setEnabled(true);
            jInsertInEnd.setEnabled(true);
            jInsertByIndex.setEnabled(true);
            jDeleteByIndex.setEnabled(true);
            jSort.setEnabled(true);

            System.out.println();
            singleList.forEach(System.out::println);

        });

        jInsertByIndex.addActionListener(e -> {

            JDialog jDialog = new JDialog(this, true);

            jDialog.setPreferredSize(new Dimension(250,155));

            JPanel jPanel = new JPanel(new GridLayout(5, 1));

            JLabel textInfo;
            JLabel textInfo2;

            if(typeBuilder.typeName()=="Integer"){
                textInfo = new JLabel("Введите число", SwingConstants.CENTER);
            }else {
                textInfo = new JLabel("Введите строку", SwingConstants.CENTER);
            }

            textInfo2 = new JLabel("Введите индекс", SwingConstants.CENTER);

            JButton btnOK = new JButton("OK");
            JTextArea consoleArea= new JTextArea();
            consoleArea.setLineWrap(true);

            JTextArea consoleArea2= new JTextArea();
            consoleArea2.setLineWrap(true);

            jPanel.add(textInfo);
            jPanel.add(consoleArea);
            jPanel.add(textInfo2);
            jPanel.add(consoleArea2);
            jPanel.add(btnOK);
            jDialog.add(jPanel);

            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    {
                        int number = 0;
                        try {
                            number = consoleArea.getLineOfOffset(consoleArea.getCaretPosition());
                            int start = consoleArea.getLineStartOffset(number);
                            int end = consoleArea.getLineEndOffset(number);
                            String command = consoleArea.getText(start, end - start);
                            number = Integer.parseInt(command);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }

                        int number2 = 0;
                        try {
                            number2 = consoleArea2.getLineOfOffset(consoleArea2.getCaretPosition());
                            int start = consoleArea2.getLineStartOffset(number2);
                            int end = consoleArea2.getLineEndOffset(number2);
                            String command = consoleArea2.getText(start, end - start);
                            number2 = Integer.parseInt(command);
                            if(typeBuilder.typeName()=="Integer"){
                                singleList.add(number, number2);
                            }
                            else {
                                singleList.add(command, number2);
                            }
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }

                        jDialog.dispose();

                    }
                }
            });

            jDialog.pack();
            jDialog.setResizable(false);
            jDialog.setDefaultCloseOperation(0);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jFSave.setEnabled(true);
            jInsertInEnd.setEnabled(true);
            jInsertByIndex.setEnabled(true);
            jDeleteByIndex.setEnabled(true);
            jSort.setEnabled(true);

            System.out.println();
            singleList.forEach(System.out::println);



        });

        jInsertInEnd.addActionListener(e -> {

            JDialog jDialog = new JDialog(this, true);

            jDialog.setPreferredSize(new Dimension(250,155));

            JPanel jPanel = new JPanel(new GridLayout(3, 1));

            JLabel textInfo;

            if(typeBuilder.typeName()=="Integer"){
                textInfo = new JLabel("Введите число", SwingConstants.CENTER);
            }else {
                textInfo = new JLabel("Введите строку", SwingConstants.CENTER);
            }

            JButton btnOK = new JButton("OK");
            JTextArea consoleArea= new JTextArea();
            consoleArea.setLineWrap(true);

            jPanel.add(textInfo);
            jPanel.add(consoleArea);
            jPanel.add(btnOK);
            jDialog.add(jPanel);

            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    {
                        int number = 0;
                        try {
                            number = consoleArea.getLineOfOffset(consoleArea.getCaretPosition());
                            int start = consoleArea.getLineStartOffset(number);
                            int end = consoleArea.getLineEndOffset(number);
                            String command = consoleArea.getText(start, end - start);
                            number = Integer.parseInt(command);
                            if(typeBuilder.typeName()=="Integer"){
                                singleList.addLast(number);
                            }
                            else {
                                singleList.addLast(command);
                            }
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }

                        jDialog.dispose();

                    }
                }
            });

            jDialog.pack();
            jDialog.setResizable(false);
            jDialog.setDefaultCloseOperation(0);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jFSave.setEnabled(true);
            jInsertInEnd.setEnabled(true);
            jInsertByIndex.setEnabled(true);
            jDeleteByIndex.setEnabled(true);
            jSort.setEnabled(true);

            System.out.println();
            singleList.forEach(System.out::println);


        });

        jDeleteByIndex.addActionListener(e -> {
            JDialog jDialog = new JDialog(this, true);

            jDialog.setPreferredSize(new Dimension(250,155));

            JPanel jPanel = new JPanel(new GridLayout(3, 1));


            JLabel textInfo = new JLabel("Индекс удаляемого объекта", SwingConstants.CENTER);
            JButton btnOK = new JButton("OK");
            JTextArea consoleArea= new JTextArea();
            consoleArea.setLineWrap(true);

            jPanel.add(textInfo);
            jPanel.add(consoleArea);
            jPanel.add(btnOK);
            jDialog.add(jPanel);

            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    {
                        int offset = 0;
                        try {
                            offset = consoleArea.getLineOfOffset(consoleArea.getCaretPosition());
                            int start = consoleArea.getLineStartOffset(offset);
                            int end = consoleArea.getLineEndOffset(offset);
                            String command = consoleArea.getText(start, end - start);
                            offset = Integer.parseInt(command);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }

                        singleList.delete(offset);
                        jDialog.dispose();

                    }
                }
            });

            jDialog.pack();
            jDialog.setResizable(false);
            jDialog.setDefaultCloseOperation(0);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jFSave.setEnabled(true);
            jInsertInEnd.setEnabled(true);
            jInsertByIndex.setEnabled(true);
            jDeleteByIndex.setEnabled(true);
            jSort.setEnabled(true);

            System.out.println();
            singleList.forEach(System.out::println);
        });

        jSortList.addActionListener(e -> {
            singleList.sort(typeBuilder.getComparator());
            System.out.println();
            singleList.forEach(System.out::println);

        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
