class Address(val country: String, val city: String) {
  override def toString: String = country + ", " + city
}
class Human(val name: String, val age: Int, val address: Address) {
  def show(): String = {
    name + ", " + age + ", " + address
  }
}
class Student(name: String, age: Int, val group: String,
              address: Address)
  extends Human(name, age, address) {

  val estimation = scala.util.Random.nextInt(5) + 1

  override def show(): String = {
    super.show() + ", " + group + ", " + estimation
  }
}
class Teacher(name: String, age: Int, address: Address)
  extends Human(name, age, address) {

  var students: List[Student] = List()

  def addStudent(student: Student): Unit = {
    students = students :+ student
  }

  def studentCount: Int = students.length

  override def show(): String = {
    super.show() + ", students: " + studentCount
  }
}
class DataList(val teachers: List[Teacher]) {

  def printAllInformation(): Unit = {
    teachers.foreach(teacher => println(teacher.show()))
  }

  def findTeachers(count: Int): List[Teacher] = {
    teachers.filter(teacher => teacher.studentCount > count)
  }

  def sortByStudentCount(): List[Teacher] = {
    teachers.sortBy(teacher => -teacher.studentCount)
  }
}
object HelloWorld {
  def main(args: Array[String]): Unit = {

    val address = new Address("Ukraine", "Kyiv")

    val student1 = new Student("Ivan", 18, "131 M", address)
    val student2 = new Student("Valerii", 19, "131 M", address)
    val student3 = new Student("Oleg", 18, "131 M", address)

    val teacher1 = new Teacher("Ruslan", 40, address)

    teacher1.addStudent(student1)
    teacher1.addStudent(student2)
    teacher1.addStudent(student3)

    val data = new DataList(List(teacher1))

    data.printAllInformation()

    data.findTeachers(2).foreach(teacher =>
      println(teacher.name)
    )

    data.sortByStudentCount().foreach(teacher =>
      println(teacher.name)
    )
  }
}