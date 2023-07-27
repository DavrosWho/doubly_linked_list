import javafx.util.Pair
import java.io.IOException

class ListJava : List {
    inner class Element {
        var data: Any? = null
        var next: Element? = null
        var prev: Element? = null
    }

    private var begin: Element? = null
    private var end: Element? = null
    private var size = 0
    override fun isEmpty(): Boolean {
        return if (size <= 0) {
            true
        } else {
            false
        }
    }

    override fun getSize(): Int {
        return size
    }

    override fun push(data: Any) {
        val newElement: Element = Element()
        newElement.data = data
        if (this.isEmpty) {
            begin = newElement
            end = newElement
        } else {
            end!!.next = newElement
            newElement.prev = end
            end = newElement
        }
        size++
    }

    private fun getElementById(id: Int): Element? {
        return if (!this.isEmpty && id <= size) {
            var tempElement = begin
            for (i in 1 until id) {
                tempElement = tempElement!!.next
            }
            tempElement
        } else {
            null
        }
    }

    override fun getDataById(id: Int): Any? {
        val tempElement = getElementById(id)
        return if (tempElement == null) {
            null
        } else {
            tempElement.data!!
        }
    }

    override fun insert(id: Int, data: Any) {
        if (id > 0 && id <= size) {
            val newElement: Element = Element()
            newElement.data = data
            val tempElement = getElementById(id)
            if (tempElement === begin) {
                begin!!.prev = newElement
                newElement.next = begin
                begin = newElement
            } else if (tempElement === end) {
                end!!.next = newElement
                newElement.prev = end
                end = newElement
            } else {
                tempElement!!.prev!!.next = newElement
                newElement.prev = tempElement.prev
                newElement.next = tempElement
                tempElement.prev = newElement
            }
            size++
        }
    }

    override fun delete(id: Int) {
        if (!isEmpty && id > 0 && id <= size) {
            var tempElement = getElementById(id)
            if (tempElement === begin) {
                begin = begin!!.next
                begin!!.prev = null
            } else if (tempElement === end) {
                end = end!!.prev
                end!!.next = null
            } else {
                tempElement!!.prev!!.next = tempElement.next
                tempElement.next!!.prev = tempElement.prev
            }
            tempElement!!.next = null
            tempElement.prev = null
            tempElement = null
            size--
        }
    }

    override fun clear() {
        if (!isEmpty) {
            var tempElement = begin
            for (i in 0 until size) {
                val temp = tempElement!!.next
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

    override fun getStringList(): Array<String?> {
        val list = arrayOfNulls<String>(size)
        if (!isEmpty) {
            var tempElement = begin
            for (i in 0 until size) {
                list[i] = tempElement!!.data.toString()
                tempElement = tempElement.next
            }
        }
        return list
    }

    override fun viewList() {
        print("List: ")
        if (!isEmpty) {
            var tempElement = begin
            for (i in 0 until size) {
                print(tempElement!!.data.toString() + " ")
                tempElement = tempElement.next
            }
        } else {
            print("is Empty!")
        }
        print("\n")
    }

    override fun forEach(a: Action) {
        var tempElement = begin
        for (i in 0 until size) {
            try {
                a.toDo(tempElement!!.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            tempElement = tempElement!!.next
        }
    }

    override fun sort(comparator: Comparator): Pair<List, Int> {
        if (size <= 1) {
            return Pair<List, Int>(this, 0)
        }
        var countAll = 0
        var tempElement = begin!!.next
        val pivot = ListJava()
        var left = ListJava()
        var right = ListJava()
        begin!!.data?.let { pivot.push(it) }
        countAll = countAll + 1
        for (i in 2 until size + 1) {
            if (comparator.compare(pivot.getDataById(1), tempElement!!.data) === 0) {
                countAll = countAll + 1
                tempElement!!.data?.let { pivot.push(it) }
            }
            if (comparator.compare(pivot.getDataById(1), tempElement!!.data) === 1) {
                countAll = countAll + 1
                tempElement!!.data?.let { left.push(it) }
            }
            if (comparator.compare(pivot.getDataById(1), tempElement!!.data) === -1) {
                countAll = countAll + 1
                tempElement!!.data?.let { right.push(it) }
            }
            tempElement = tempElement!!.next
        }
        val pairLeft: Pair<List, Int> = left.sort(comparator)
        left = pairLeft.getKey() as ListJava
        countAll = countAll + pairLeft.getValue()
        val pairRight: Pair<List, Int> = right.sort(comparator)
        right = pairRight.getKey() as ListJava
        countAll = countAll + pairRight.getValue()
        val sortedList: List = ListJava()
        var tempLeftElement = left.begin
        for (i in 0 until left.size) {
            sortedList.push(tempLeftElement!!.data)
            tempLeftElement = tempLeftElement.next
        }
        var tempPivotElement = pivot.begin
        for (i in 0 until pivot.size) {
            sortedList.push(tempPivotElement!!.data)
            tempPivotElement = tempPivotElement.next
        }
        var tempRightElement = right.begin
        for (i in 0 until right.size) {
            sortedList.push(tempRightElement!!.data)
            tempRightElement = tempRightElement.next
        }
        return Pair<List, Int>(sortedList, countAll)
    }
}