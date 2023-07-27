import javafx.util.Pair
import javax.swing.DefaultListModel
import java.io.IOException

class ListJavaActionListener : ListActionListener {
    var list: List = ListJava()
    override fun createList(userType: UserType) {
        list.push(userType.create())
        list.push(userType.create())
        list.push(userType.create())
        list.push(userType.create())
        list.push(userType.create())
        list.push(userType.create())
    }

    override fun setModel(model: DefaultListModel<String>) {
        for (str in list.stringList) {
            model.addElement(str)
        }
    }

    override fun push(str: String, userType: UserType) {
        list.push(userType.parseValue(str))
    }

    override fun insert(str: String, id: Int, userType: UserType) {
        list.insert(id, userType.parseValue(str))
    }

    override fun delete(id: Int) {
        list.delete(id)
    }

    override fun serialize(listSerialization: ListSerialization, userType: UserType) {
        try {
            listSerialization.serialize(userType, list)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun deserialize(listSerialization: ListSerialization, userType: UserType) {
        try {
            list = listSerialization.deserialize(userType, ListJava()) as ListJava
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun sort(userType: UserType) {
        val pair: Pair<*, *> = list.sort(userType.typeComparator)
        list = pair.key as List
    }

    override fun clear() {
        list.clear()
    }
}