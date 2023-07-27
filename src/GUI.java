import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private int width;
    private int height;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private ActionMenu actionMenu;

    public GUI (ListActionListener listActionListener) {
        actionMenu = new ActionMenu(listActionListener);
        width = 1020;
        height = 900;
        setVisible(true);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(dimension.width/2 - width/2, dimension.height/2 - height/2 , width, height);
        setTitle("Test List");
        add(actionMenu);
    }
}
