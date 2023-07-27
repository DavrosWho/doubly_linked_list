import javafx.util.Pair

class ListJava : List {
    inner class Element {
        var data: Object? = null
        var next: Element? = null
        var prev: Element? = null
        fun setData(data: Object?) {
            this.data = data
        }

        fun getData(): Object? {
            return data
        }
    }

    private var begin: Element? = null
    private var end: Element? = null

    @get:Override
    var size = 0
        private set

    @get:Override
    val isEmpty: Boolean
        get() = if (size <= 0) {
            true
        } else {
            false
        }

    @Override
    fun push(data: Object?) {
        val newElement: Element = Element()
        newElement.setData(data)
        if (isEmpty) {
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
        return if (!isEmpty && id <= size) {
            var tempElement = begin
            for (i in 1 until id) {
                tempElement = tempElement!!.next
            }
            tempElement
        } else {
            null
        }
    }

    @Override
    fun getDataById(id: Int): Object? {
        val tempElement = getElementById(id)
        return tempElement?.getData()
    }

    @Override
    fun insert(id: Int, data: Object?) {
        if (id > 0 && id <= size) {
            val newElement: Element = Element()
            newElement.setData(data)
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

    @Override
    fun delete(id: Int) {
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

    @Override
    fun clear() {
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

    @get:Override
    val stringList: Array<String?>
        get() {
            val list = arrayOfNulls<String>(size)
            if (!isEmpty) {
                var tempElement = begin
                for (i in 0 until size) {
                    list[i] = tempElement!!.getData().toString()
                    tempElement = tempElement.next
                }
            }
            return list
        }

    @Override
    fun viewList() {
        System.out.print("List: ")
        if (!isEmpty) {
            var tempElement = begin
            for (i in 0 until size) {
                System.out.print(tempElement!!.getData().toString() + " ")
                tempElement = tempElement.next
            }
        } else {
            System.out.print("is Empty!")
        }
        System.out.print("\n")
    }

    @Override
    fun forEach(a: Action) {
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

    fun sort(comparator: Comparator): Pair<List, Integer> {
        if (size <= 1) {
            return Pair<List, Integer>(this, 0)
        }
        var countAll = 0
        var tempElement = begin!!.next
        val pivot = ListJava()
        var left = ListJava()
        var right = ListJava()
        pivot.push(begin!!.getData())
        countAll = countAll + 1
        for (i in 2 until size + 1) {
            if (comparator.compare(pivot.getDataById(1), tempElement!!.getData()) === 0) {
                countAll = countAll + 1
                pivot.push(tempElement!!.getData())
            }
            if (comparator.compare(pivot.getDataById(1), tempElement!!.getData()) === 1) {
                countAll = countAll + 1
                left.push(tempElement!!.getData())
            }
            if (comparator.compare(pivot.getDataById(1), tempElement!!.getData()) === -1) {
                countAll = countAll + 1
                right.push(tempElement!!.getData())
            }
            tempElement = tempElement!!.next
        }
        val pairLeft: Pair<List, Integer> = left.sort(comparator)
        left = pairLeft.getKey()
        countAll = countAll + pairLeft.getValue()
        val pairRight: Pair<List, Integer> = right.sort(comparator)
        right = pairRight.getKey()
        countAll = countAll + pairRight.getValue()
        val sortedList: List = ListJava()
        var tempLeftElement = left.begin
        for (i in 0 until left.size) {
            sortedList.push(tempLeftElement!!.getData())
            tempLeftElement = tempLeftElement.next
        }
        var tempPivotElement = pivot.begin
        for (i in 0 until pivot.size) {
            sortedList.push(tempPivotElement!!.getData())
            tempPivotElement = tempPivotElement.next
        }
        var tempRightElement = right.begin
        for (i in 0 until right.size) {
            sortedList.push(tempRightElement!!.getData())
            tempRightElement = tempRightElement.next
        }
        return Pair<List, Integer>(sortedList, countAll)
    }
}