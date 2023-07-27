
import javafx.util.Pair;

import java.io.IOException;

public interface List {
    public boolean isEmpty();
    public int getSize();
    public void push(Object data);
    public Object getDataById(int id);
    public void insert(int id, Object data);
    public void delete(int id);
    public void clear();
    public String[] getStringList();
    public void viewList();
    public void forEach(Action a) ;
    public Pair<List, Integer> sort(Comparator comparator);
}
