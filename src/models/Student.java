package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;

import java.lang.ArrayIndexOutOfBoundsException;

import toolkit.DatabasePath;
import toolkit.FileDatabase;

public class Student implements Model
{
    private String fullName;
    private int id; 
    private int groupId; 

    private static String modelName = "Student";
    private static String databasePath = DatabasePath.getDatabasePath(modelName + ".fdb");

    public Student(String fullName, int groupId)
    {
        this.fullName = fullName;
        this.groupId = groupId;
    }

    /* Getters */
    public String getFullName()
    {
        return this.fullName;
    }
    public int getId()
    {
        return this.id;
    }
    public Group getGroup()
    {
        return Group.findById(this.groupId);
    }

    public Absent[] getAbsents()
    {
        return Absent.filterByStudent(this.id);
    }
    public Absent[] getAbsentsForSubject(int subjectId)
    {
        return Absent.filterByStudentAndSubject(this.id, subjectId);
    }

    /* Setters */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }
    private void setId()
    {
        Student[] students = Student.getAll();
        try
        {
            this.id = students[students.length - 1].getId() + 1;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            if(e.getMessage().equals("Index -1 out of bounds for length 0"))
            {
                this.id = 1; 
            }
        }
    }

    public void save()
    {
        this.setId();        
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        db.add(this);
        db.save();
    }


    /* Static methods */
    public static Student[] getAll()
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        return studentCollection.toArray(new Student[studentCollection.size()]);
    }
    public static Student[] filterByGroup(int groupId)
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        Collection<Student> filteredStudentList = new ArrayList<Student>();
        
        for(Student student : studentCollection)
        {
            if(student.getGroup().getId() == groupId)
            {
                filteredStudentList.add(student);
            }
        }
        return filteredStudentList.toArray(new Student[filteredStudentList.size()]);
    }
    public static Student findById(int id)
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        for(Student student : studentCollection)
        {
            if(student.getId() == id)
            {
                return student; 
            }
        }
        return null; 
    }
}
