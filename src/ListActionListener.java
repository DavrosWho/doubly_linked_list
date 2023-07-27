import javax.swing.*;

public interface ListActionListener {
    public void createList(UserType userType);
    public void setModel(DefaultListModel<String> model);
    public void push(String str, UserType userType);
    public void insert(String str, int id, UserType userType);
    public void delete(int id);
    public void serialize(ListSerialization listSerialization, UserType userType);
    public void deserialize(ListSerialization listSerialization, UserType userType);
    public void sort(UserType userType);
    public void clear();
}
