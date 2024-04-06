package models;

import java.util.ArrayList;
import java.util.Collection;

import parents.Model;

import java.lang.ArrayIndexOutOfBoundsException;

import toolkit.FileDatabase;

public class Student extends Model
{
    private String fullName;
    private int id; 
    private int groupId; 

    public Student(String fullName, int groupId)
    {
        this.fullName = fullName;
        this.groupId = groupId;

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

    /* Getters */
    public String getFullName()
    {
        return this.fullName;
    }
    public int getId()
    {
        return this.id;
    }
    public int getGroupId()
    {
        return this.groupId;
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

    /* Static methods */
    public static Student[] getAll()
    {
        FileDatabase<Student> db = new FileDatabase<Student>("./database/Student");
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        return studentCollection.toArray(new Student[studentCollection.size()]);
    }
    public static Student[] filterByGroup(int groupId)
    {
        FileDatabase<Student> db = new FileDatabase<Student>("./database/Student");
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        Collection<Student> filteredStudentList = new ArrayList<Student>();
        
        for(Student student : studentCollection)
        {
            if(student.getGroupId() == groupId)
            {
                filteredStudentList.add(student);
            }
        }
        return filteredStudentList.toArray(new Student[filteredStudentList.size()]);
    }

    public String getDatabasePath()
    {
        return "./database/" + this.getModelName();
    }

    protected void setModelName()
    {
        this.modelName = "Student";
    }

}
