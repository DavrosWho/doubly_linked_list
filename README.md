# doubly_linked_list
Реализовано студентами Ковальчуком А.В. (Владелец репозитория) и [Мостовым Д.В](https://github.com/MOCTNK).

## Что это
Это оконное приложение, реализующее работу с двусвязным разомкнутым списком из двух возможных типов данных. Типы данных: Integer и самодельный Fraction. Список реализован в двух версиях: с помощью Java и Scala. Экземпляр структуры данных (СД) хранит объекты только одного типа. Список реализован в виде шаблона, работающего с произвольным типом данных.  
**Набор операций:** 
- Добавление в конец, получение, вставка и удаление по индесу
- Итератор forEach: интерфейс обратного вызова
- Сортировка слиянием. Сложность O(n*log n)
- Сериализация в формате XML (пример в файле под названием "list.xml"), десериализация
### Как запустить
Проект разработан в среде IntelliJ IDEA (Протестирован в Build #IC-221.6008.13, built on July 19, 2022)  
Для работы со списком на Java настройте IDE для запуска Main.kt, на Scala - Main2.scala

## Как это работает
### Диаграммы классов
Для части на Scala:
![image](https://github.com/DavrosWho/doubly_linked_list/assets/71879137/38bd13e0-b254-4a10-b3b3-4c6fa01ca035)
### Нюансы
- Список состоит из элементов – каждый из которых хранит некоторый объект и указатели на соседей. В списке имеются указатели на первый и последний элемент СД, а также его размер. Индексирование в структуре начинается с нуля.  
- В нашем случае объект "правильная дробь" имеет целую часть, числитель и знаменатель. И имеет следующий формат визуального представления: (ц)ч/з. Класс дроби реализует два ключевых метода: Перевод из неправильной в правильную дробь и наоборот (для сравнения дробей через компаратор).  
- Классы IntType и FractionType имплементируют интерфейс UserType и реализуют методы возврата имени типа, клонирования, сериализации и десериализации объекта, парсинг строки для нахождения объекта необходимого типа и возврат компаратора.  
- Для сравнения объектов одинакового типа данных созданы компараторы, которые возвращают значение «-1», если первый передаваемый объект меньше второго, «1», когда первый объект больше и «0» при равных значениях.  
- Фабрика MyFactory возвращает необходимую Абстракцию по названию типа.  
- Оконное приложение реализовано с помощью JavaFX.  
- Реализована абстракция, работающая с методами любого из списков (Java либо Scala). Для этого написан интерфейс ListActionListener, имеющий все необходимые методы для обработки задач для списков и классы ListScalaActionListener.scala с ListKotlinActionListener.kt, реализующие эти методы.  

## Как это выглядит
![image](https://github.com/DavrosWho/doubly_linked_list/assets/71879137/c35119ef-0f9e-4fee-9806-594fff0cd9ce)

  


