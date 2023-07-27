import java.io.IOException
import javax.swing.DefaultListModel
class ListScalaActionListener extends ListActionListener {
  var list = new ListScala

  override def createList(userType: UserType): Unit = {
    list.push(userType.create)
    list.push(userType.create)
    list.push(userType.create)
    list.push(userType.create)
    list.push(userType.create)
    list.push(userType.create)
  }

  override def setModel(model: DefaultListModel[String]): Unit = {
    for (str <- list.getStringList) {
      model.addElement(str)
    }
  }

  override def push(str: String, userType: UserType): Unit = {
    list.push(userType.parseValue(str))
  }

  override def insert(str: String, id: Int, userType: UserType): Unit = {
    list.insert(id, userType.parseValue(str))
  }

  override def delete(id: Int): Unit = {
    list.delete(id)
  }

  override def serialize(listSerialization: ListSerialization, userType: UserType): Unit = {
    try listSerialization.serialize(userType, list)
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  override def deserialize(listSerialization: ListSerialization, userType: UserType): Unit = {
    try list = listSerialization.deserialize(userType, new ListScala).asInstanceOf[ListScala]
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  override def clear(): Unit = {
    list.clear()
  }

  override def sort(userType: UserType): Unit = {
    val pair = list.sort(userType.getTypeComparator)
    list = pair.getKey().asInstanceOf[ListScala]
  }
}
