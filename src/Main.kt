import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic

object Main {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val listActionListener: ListActionListener = ListJavaActionListener()
        val list: List = ListJava()
        val test = Test(list)
        test.testPushRandomElement("Fraction")
        val f1 = Fraction(23, 7, 3)
        test.testPushElement(f1)
        val f2 = Fraction(3, 1, 2)
        test.testInsertElement(f2, 2)
        test.testSortedList("Fraction")
        test.testClearList()
        test.testPushRandomElement("Int")
        test.testPushElement(23)
        test.testInsertElement(33, 2)
        test.testSortedList("Int")
        test.testClearList()
        val gui = GUI(listActionListener)
    }
}