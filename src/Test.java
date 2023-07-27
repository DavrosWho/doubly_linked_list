import javafx.util.Pair;

public class Test {

    private MyFactory factory = new MyFactory();
    private List list;
    Test(List list) {
        this.list = list;
    }
    public void testPushRandomElement(String type) {
        try {
            System.out.println("Test push random Element");
            UserType userType = factory.getBuilderByName(type);
            list.push(userType.create());
            list.push(userType.create());
            list.push(userType.create());
            list.push(userType.create());
            list.push(userType.create());
            list.viewList();
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testPushElement(Object o) {
        try {
            System.out.println("Test push Element "+o.toString());
            list.push(o);
            list.viewList();
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testInsertElement(Object o, int id) {
        try {
            System.out.println("Test Insert Element "+o.toString()+" by id "+id);
            list.insert(id, o);
            list.viewList();
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testDeleteElement(int id) {
        try {
            System.out.println("Test delete Element by id "+id);
            list.delete(id);
            list.viewList();
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testSortedList(String type) {
        try {
            System.out.println("Test sorted List");
            UserType userType = factory.getBuilderByName(type);
            Pair tempPair = list.sort(userType.getTypeComparator());

            List l = (List) tempPair.getKey();
            list.viewList();
            l.viewList();
            System.out.println("Count Elements: " + tempPair.getValue());
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testClearList() {
        try {
            System.out.println("Test clear List");
            list.clear();
            list.viewList();
            System.out.println();
        }
        catch(Exception e) {

        }
    };

    public void testSortedAll() {
        try {
            System.out.println("Test sorted all");
            UserType userType = factory.getBuilderByName("Int");
            for(int i = 10000; i <= 100000; i = i + 10000) {
                list.clear();
                for(int j = 0; j < i; j++) {
                    list.push(userType.create());
                }
                Pair tempPair = list.sort(userType.getTypeComparator());
                System.out.println("Size: "+i+ "  Count elements: "+tempPair.getValue());
            }
        }
        catch(Exception e) {

        }
    };

}
