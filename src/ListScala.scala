import javafx.util.Pair

class ListScala extends List{
  class Element {
    private var data: Any = null
    var next: Element = null
    var prev: Element = null

    def setData(data: Any): Unit = {
      this.data = data
    }

    def getData: Any = this.data

  }

  private var begin: Element = null
  private var end: Element = null
  private var size: Int = 0

  override def isEmpty: Boolean = {
    if (size <= 0) return true
    else return false
  }

  override def getSize: Int = {
    return size
  }

  override def push(data: Any): Unit = {
    val newElement = new Element
    newElement.setData(data)
    if (this.isEmpty) {
      this.begin = newElement
      this.end = newElement
    }
    else {
      this.end.next = newElement
      newElement.prev = this.end
      this.end = newElement
    }
    size += 1
  }

  def getElementById(id: Int): Element = {
    if (!this.isEmpty && id <= size) {
      var tempElement = this.begin
      for (i <- 1 until id) {
        tempElement = tempElement.next
      }
      return tempElement
    }
    else return null
  }

  override def getDataById(id: Int): Any = {
    val tempElement = this.getElementById(id)
    if (tempElement == null) return null
    else return tempElement.getData
  }

  override def insert(id: Int, data: Any): Unit = {
    if (id > 0 && id <= size) {
      val newElement = new Element
      newElement.setData(data)
      val tempElement = this.getElementById(id)
      if (tempElement eq begin) {
        begin.prev = newElement
        newElement.next = begin
        begin = newElement
      }
      else if (tempElement eq end) {
        end.next = newElement
        newElement.prev = end
        end = newElement
      }
      else {
        tempElement.prev.next = newElement
        newElement.prev = tempElement.prev
        newElement.next = tempElement
        tempElement.prev = newElement
      }
      size += 1
    }
  }

  override def delete(id: Int): Unit = {
    if (!isEmpty && id > 0 && id <= size) {
      var tempElement = this.getElementById(id)
      if (tempElement eq begin) {
        begin = begin.next
        begin.prev = null
      }
      else if (tempElement eq end) {
        end = end.prev
        end.next = null
      }
      else {
        tempElement.prev.next = tempElement.next
        tempElement.next.prev = tempElement.prev
      }
      tempElement.next = null
      tempElement.prev = null
      tempElement = null
      size -= 1
    }
  }

  override def clear(): Unit = {
    if (!isEmpty) {
      var tempElement = this.begin
      for (i <- 0 until size) {
        val temp = tempElement.next
        tempElement.next = null
        tempElement.prev = null
        tempElement = temp
      }
      tempElement = null
      begin = null
      end = null
      size = 0
    }
  }

  override def getStringList: Array[String] = {
    val list = new Array[String](size)
    if (!isEmpty) {
      var tempElement = this.begin
      for (i <- 0 until size) {
        list(i) = tempElement.getData.toString
        tempElement = tempElement.next
      }
    }
    return list
  }

  override def viewList(): Unit = {
    System.out.print("List: ")
    if (!isEmpty) {
      var tempElement = this.begin
      for (i <- 0 until size) {
        System.out.print(tempElement.getData + " ")
        tempElement = tempElement.next
      }
    }
    else System.out.print("is Empty!")
    System.out.print("\n")
  }

  override def forEach(a: Action): Unit = {
    var tempElement = begin
    for (i <- 0 until size) {
      a.toDo(tempElement.getData)
      tempElement = tempElement.next
    }
  }

  override def sort(comparator: Comparator): Pair[List, Integer] = {
    if(size <= 1) {
      return new Pair[List, Integer](this, 0)
    }
    var countAll = 0;
    var tempElement = begin.next
    val pivot = new ListScala
    var left = new ListScala
    var right = new ListScala
    pivot.push(begin.getData)
    countAll = countAll + 1
    for(i <- 2 until getSize() + 1) {
      if(comparator.compare(pivot.getDataById(1), tempElement.getData) == 0) {
        countAll = countAll + 1
        pivot.push(tempElement.getData)
      }
      if(comparator.compare(pivot.getDataById(1), tempElement.getData) == 1) {
        countAll = countAll + 1
        left.push(tempElement.getData)
      }
      if(comparator.compare(pivot.getDataById(1), tempElement.getData) == -1) {
        countAll = countAll + 1
        right.push(tempElement.getData)
      }
      tempElement = tempElement.next
    }

    val pairLeft = left.sort(comparator)
    left = pairLeft.getKey().asInstanceOf[ListScala]
    countAll = countAll + pairLeft.getValue()
    val pairRight = right.sort(comparator)
    right = pairRight.getKey().asInstanceOf[ListScala]
    countAll = countAll + pairRight.getValue()

    val sortedList = new ListScala
    var tempLeftElement = left.begin
    for(i <- 0  until left.getSize()) {
      sortedList.push(tempLeftElement.getData)
      tempLeftElement = tempLeftElement.next
    }

    var tempPivotElement = pivot.begin
    for(i <- 0  until pivot.getSize()) {
      sortedList.push(tempPivotElement.getData)
      tempPivotElement = tempPivotElement.next
    }

    var tempRightElement = right.begin
    for(i <- 0  until right.getSize()) {
      sortedList.push(tempRightElement.getData)
      tempRightElement = tempRightElement.next
    }

    return new Pair[List, Integer](sortedList, countAll)
  }

  /*private def mergeSort(b: Element, comparator: Comparator): Element = {
    if (b == null || b.next == null) return b
    val middle = getMiddle(b)
    val middleNext = middle.next
    middle.next = null
    val left = mergeSort(b, comparator)
    val right = mergeSort(middleNext, comparator)
    return merge(left, right, comparator)
  }

  private def merge(head11: Element, head22: Element, comparator: Comparator) : Element = {
    var left = head11
    var right = head22
    val merged = new Element
    var temp = merged
    while ( {
      left != null && right != null
    }) {
      if (comparator.compare(left.getData, right.getData) < 0) {
        temp.next = left
        left.prev = temp
        left = left.next
      }
      else {
        temp.next = right
        right.prev = temp
        right = right.next
      }
      temp = temp.next
    }
    while ( {
      left != null
    }) {
      temp.next = left
      left.prev = temp
      left = left.next
      temp = temp.next
    }
    while ( {
      right != null
    }) {
      temp.next = right
      right.prev = temp
      right = right.next
      temp = temp.next
      this.end = temp
    }
    return merged.next
  }

  private def getMiddle(b: Element): Element = {
    if (b == null) return null
    var fast = b.next
    var slow = b
    while ( {
      fast != null
    }) {
      fast = fast.next
      if (fast != null) {
        slow = slow.next
        fast = fast.next
      }
    }
    return slow
  }*/
}
