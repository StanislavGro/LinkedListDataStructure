package lab.singleList.classes.gui;

import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {

    public Point cameraPosition;
    private boolean firstTime = true;

    public Drawing(){
        cameraPosition = new Point(0, 0);
        setPreferredSize(new Dimension(GUI.Width, GUI.Height));
        setBackground(Color.LIGHT_GRAY);
    }

    public void Paint(){
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(cameraPosition.x, cameraPosition.y);

        if(GUI.singleList.getSize() != 0){

            int i;

            for (i = 0; i < GUI.singleList.getSize(); i++) {
                g.drawRect(160 * i + 30, GUI.Height / 3, 110, 50);
                g.drawLine(140 + 160 * i, GUI.Height / 3 + 25, 190 + 160 * i, GUI.Height / 3 + 25);
                if(GUI.typeBuilder.typeName() == "String")
                    g.drawString(GUI.arrayList.get(i).toString(), 55 + 160 * i, GUI.Height / 3 + 29);
                else
                    g.drawString(GUI.arrayList.get(i).toString(), 80 + 160 * i, GUI.Height / 3 + 29);
            }

            g.drawString("NULL", 50 + 160 * i, GUI.Height / 3 + 29);

        }
        else {
            g.drawString("NULL", 50, GUI.Height / 3 + 29);
            g.drawString("NULL", 210, GUI.Height / 3 + 29);
            g.drawRect(30, GUI.Height / 3, 110, 50);
            g.drawLine(140, GUI.Height / 3 + 25, 190, GUI.Height / 3 + 25);
        }

    }

}
