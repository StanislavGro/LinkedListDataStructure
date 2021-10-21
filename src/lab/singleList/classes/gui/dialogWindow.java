package lab.singleList.classes.gui;

import lab.singleList.classes.logic.TypeFactory;

import javax.swing.*;
import java.awt.*;

public class dialogWindow extends JDialog {

    private GUI gui;

    public dialogWindow(GUI gui){

        super(gui, true);

        this.gui = gui;

        setPreferredSize(new Dimension(200,125));

        JPanel jPanel = new JPanel(new GridLayout(3, 1));

        JLabel info = new JLabel("Выберите тип данных", SwingConstants.CENTER);
        String[] items = { "Integer", "String" };
        JComboBox<String> jComboBox = new JComboBox<String>(items);

        jComboBox.addActionListener(e -> {
            gui.requestFocus();
            String string = (String) jComboBox.getSelectedItem();
            gui.typeBuilder = TypeFactory.getBuilder(string);
            dispose();
        });



        jPanel.add(info);
        jPanel.add(jComboBox);
        add(jPanel);

        pack();

        setResizable(false);
        setDefaultCloseOperation(0);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
