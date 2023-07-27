
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionMenu extends JPanel implements ActionListener {
    private JButton buttonAddElement = new JButton("Add Element");
    private JButton buttonInsertElement = new JButton("Insert Element");
    private JButton buttonDeleteElement = new JButton("Delete Element");
    private JButton buttonSaveList = new JButton("Serialize List");
    private JButton buttonLoadList = new JButton("Deserialize List");
    private JButton buttonClearList = new JButton("Clear List");
    private JButton buttonCreateList = new JButton("Create List");
    private JButton buttonSortList = new JButton("Sort List");
    private JTextField textFieldAddElement = new JTextField();
    private JTextField textFieldInsertElement = new JTextField();
    private JTextField textFieldIdInsert = new JTextField();
    private JTextField textFieldIdDelete = new JTextField();
    private JRadioButton radioButton1 = new JRadioButton("Int");
    private JRadioButton radioButton2 = new JRadioButton("Fraction");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel labelAddElement = new JLabel("value:");
    private JLabel labelInsertElement = new JLabel("value:");
    private JLabel labelIdInsertElement = new JLabel("id:");
    private JLabel labelidDeleteElement = new JLabel("id:");
    private JList jList = new JList();
    private MyFactory factory = new MyFactory();
    private DefaultListModel<String> model;
    ListSerialization listSerialization = new ListSerialization();
    private ListActionListener listActionListener;
    String type = "Int";
    boolean isSerialize = false;

    public ActionMenu(ListActionListener listActionListener) {
        this.listActionListener = listActionListener;
        setLayout(null);

        setBounds(0, 0 , 1020, 900);

        buttonAddElement.addActionListener(this);
        buttonAddElement.setBounds(650, 70, 300, 50);
        buttonAddElement.setFocusable(false);
        buttonAddElement.setEnabled(false);
        buttonAddElement.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonAddElement);

        buttonInsertElement.addActionListener(this);
        buttonInsertElement.setBounds(650, 170, 300, 50);
        buttonInsertElement.setFocusable(false);
        buttonInsertElement.setEnabled(false);
        buttonInsertElement.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonInsertElement);

        buttonDeleteElement.addActionListener(this);
        buttonDeleteElement.setBounds(650, 270, 300, 50);
        buttonDeleteElement.setFocusable(false);
        buttonDeleteElement.setEnabled(false);
        buttonDeleteElement.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonDeleteElement);

        buttonSaveList.addActionListener(this);
        buttonSaveList.setBounds(650, 370, 300, 50);
        buttonSaveList.setFocusable(false);
        buttonSaveList.setEnabled(false);
        buttonSaveList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonSaveList);

        buttonLoadList.addActionListener(this);
        buttonLoadList.setBounds(650, 470, 300, 50);
        buttonLoadList.setFocusable(false);
        buttonLoadList.setEnabled(false);
        buttonLoadList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonLoadList);

        buttonClearList.addActionListener(this);
        buttonClearList.setBounds(650, 570, 300, 50);
        buttonClearList.setFocusable(false);
        buttonClearList.setEnabled(false);
        buttonClearList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonClearList);

        buttonCreateList.addActionListener(this);
        buttonCreateList.setBounds(650, 700, 300, 50);
        buttonCreateList.setFocusable(false);
        // buttonCreateList.setEnabled(false);
        buttonCreateList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonCreateList);

        buttonSortList.addActionListener(this);
        buttonSortList.setBounds(300, 700, 300, 50);
        buttonSortList.setFocusable(false);
        buttonSortList.setEnabled(false);
        buttonSortList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(buttonSortList);

        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

        radioButton1.addActionListener(this);
        radioButton1.setSelected(true);
        radioButton1.setBounds(650, 640, 300, 20);
        //textFieldIdInsert.setFocusable(false);
        radioButton1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(radioButton1);

        radioButton2.addActionListener(this);
        radioButton2.setBounds(650, 670, 300, 20);
        //textFieldIdInsert.setFocusable(false);
        radioButton2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(radioButton2);

        textFieldAddElement.addActionListener(this);
        textFieldAddElement.setBounds(850, 20, 100, 50);
        //textFieldAddElement.setFocusable(false);
        textFieldAddElement.setFont(new Font("Arial", Font.PLAIN, 15));
        add(textFieldAddElement);

        textFieldInsertElement.addActionListener(this);
        textFieldInsertElement.setBounds(750, 120, 100, 50);
        //textFieldInsertElement.setFocusable(false);
        textFieldInsertElement.setFont(new Font("Arial", Font.PLAIN, 15));
        add(textFieldInsertElement);

        textFieldIdInsert.addActionListener(this);
        textFieldIdInsert.setBounds(900, 120, 50, 50);
        //textFieldIdInsert.setFocusable(false);
        textFieldIdInsert.setFont(new Font("Arial", Font.PLAIN, 15));
        add(textFieldIdInsert);

        textFieldIdDelete.addActionListener(this);
        textFieldIdDelete.setBounds(850, 220, 100, 50);
        //textFieldIdInsert.setFocusable(false);
        textFieldIdDelete.setFont(new Font("Arial", Font.PLAIN, 15));
        add(textFieldIdDelete);

        labelAddElement.setBounds(790, 20, 100, 50);
        //textFieldIdInsert.setFocusable(false);
        labelAddElement.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelAddElement);

        labelInsertElement.setBounds(690, 120, 100, 50);
        //textFieldIdInsert.setFocusable(false);
        labelInsertElement.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelInsertElement);

        labelIdInsertElement.setBounds(870, 120, 100, 50);
        //textFieldIdInsert.setFocusable(false);
        labelIdInsertElement.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelIdInsertElement);

        labelidDeleteElement.setBounds(820, 220, 100, 50);
        //textFieldIdInsert.setFocusable(false);
        labelidDeleteElement.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelidDeleteElement);

        jList.setBounds(50, 50, 500, 500);
        jList.setFont(new Font("Arial", Font.PLAIN, 25));
        add(jList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonCreateList) {
            isSerialize = false;
            buttonAddElement.setEnabled(true);
            buttonInsertElement.setEnabled(true);
            buttonDeleteElement.setEnabled(true);
            buttonLoadList.setEnabled(false);
            buttonSaveList.setEnabled(true);
            buttonClearList.setEnabled(true);
            buttonSortList.setEnabled(true);
            if(radioButton1.isSelected()) {
                type = "Int";
            }

            if(radioButton2.isSelected()) {
                type = "Fraction";
            }
            UserType userType = factory.getBuilderByName(type);
            listActionListener.clear();

            listActionListener.createList(userType);

            model = new DefaultListModel<>();
            listActionListener.setModel(model);
            jList.setModel(model);

        }

        if(e.getSource() == buttonAddElement) {
            try {
                listActionListener.push(textFieldAddElement.getText(), factory.getBuilderByName(type));
                model = new DefaultListModel<>();
                listActionListener.setModel(model);
                jList.setModel(model);
            } catch (Exception ee) {

            }
        }

        if(e.getSource() == buttonInsertElement) {
            try {
                listActionListener.insert(textFieldInsertElement.getText(),
                        Integer.parseInt(textFieldIdInsert.getText()), factory.getBuilderByName(type));
                model = new DefaultListModel<>();
                listActionListener.setModel(model);
                jList.setModel(model);
            } catch (Exception ee) {

            }
        }

        if(e.getSource() == buttonDeleteElement) {
            try {
                listActionListener.delete(Integer.parseInt(textFieldIdDelete.getText()));
                model = new DefaultListModel<>();
                listActionListener.setModel(model);
                jList.setModel(model);
            } catch (Exception ee) {

            }
        }

        if(e.getSource() == buttonSaveList) {
            listActionListener.serialize(listSerialization, factory.getBuilderByName(type));
            isSerialize = true;
            buttonLoadList.setEnabled(true);
        }

        if(e.getSource() == buttonLoadList) {
            listActionListener.deserialize(listSerialization, factory.getBuilderByName(type));
            model = new DefaultListModel<>();
            listActionListener.setModel(model);
            jList.setModel(model);
        }

        if(e.getSource() == buttonClearList) {
            listActionListener.clear();
            model = new DefaultListModel<>();
            listActionListener.setModel(model);
            jList.setModel(model);
        }

        if(e.getSource() == buttonSortList) {
            listActionListener.sort(factory.getBuilderByName(type));
            model = new DefaultListModel<>();
            listActionListener.setModel(model);
            jList.setModel(model);
        }
    }

}
