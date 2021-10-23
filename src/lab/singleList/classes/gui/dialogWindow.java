package lab.singleList.classes.gui;

import lab.singleList.classes.logic.TypeFactory;

import javax.swing.*;
import java.awt.*;

public class dialogWindow extends JDialog {

    private GUI gui;

    public dialogWindow(GUI gui){

        super(gui, true);

        this.gui = gui;

        setPreferredSize(new Dimension(300,180));

        JPanel jPanel = new JPanel(new GridLayout(7, 1));

        JLabel info = new JLabel("Выберите тип данных", SwingConstants.CENTER);
        String[] items = { "Integer", "String" };
        JComboBox<String> jComboBox = new JComboBox<String>(items);
        JLabel info5 = new JLabel("", SwingConstants.CENTER);
        JLabel info2 = new JLabel("Если структура выходит за пределы окна", SwingConstants.CENTER);
        info2.setForeground(Color.RED);
        JLabel info3 = new JLabel("используйте стрелки ↑↓→← для перемещения", SwingConstants.CENTER);
        info3.setForeground(Color.RED);
        JLabel info4 = new JLabel("или компьютерную мышь", SwingConstants.CENTER);
        info4.setForeground(Color.RED);

        jComboBox.addActionListener(e -> {
            gui.requestFocus();
            String string = (String) jComboBox.getSelectedItem();
            gui.typeBuilder = TypeFactory.getBuilder(string);
            dispose();
        });

        jPanel.add(info);
        jPanel.add(jComboBox);
        jPanel.add(info5);
        jPanel.add(info2);
        jPanel.add(info3);
        jPanel.add(info4);
        add(jPanel);

        pack();

        setResizable(false);
        setDefaultCloseOperation(0);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
