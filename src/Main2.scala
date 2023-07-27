object Main2 {
  def main(args: Array[String]): Unit = {
    val listActionListener = new ListScalaActionListener
    val list = new ListScala
    val test = new Test(list)

    test.testPushRandomElement("Fraction")
    val f1 = new Fraction(23, 7, 3)
    test.testPushElement(f1)
    val f2 = new Fraction(3, 1, 2)
    test.testInsertElement(f2, 2)
    test.testSortedList("Fraction")
    test.testClearList()

    test.testPushRandomElement("Int")
    test.testPushElement(23)
    test.testInsertElement(33, 2)
    test.testSortedList("Int")
    test.testClearList()
    test.testSortedAll();
    val gui = new GUI(listActionListener)
  }
}