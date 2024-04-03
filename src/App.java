import models.Student;

public class App 
{
    public static void main(String[] args) 
    {
        Student student = new Student("Qumru Sadigli", 1);
        student.save();
        for (int i = 0; i < Student.getAll().length; i++) {
            System.out.println(Student.getAll()[i].getFullName());
        }
    }
}