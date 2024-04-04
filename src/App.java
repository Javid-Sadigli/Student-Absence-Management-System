import models.Student;

public class App 
{
    public static void main(String[] args) 
    {
        Student[] students = Student.filterByGroup(1); 
        for (Student student : students)
        {
            System.out.println(student.getFullName());
        }
    }
}