package models;

import java.io.Serializable;
import java.util.Collection;

import java.lang.ArrayIndexOutOfBoundsException;

import toolkit.FileDatabase;

public class Student implements Serializable 
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

    public void save()
    {
        FileDatabase<Student> db = new FileDatabase<Student>("./database/Student");
        db.load();
        db.add(this);
        db.save();
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
        studentCollection.forEach((student) -> {
            if(student.getGroupId() != groupId)
            {
                studentCollection.remove(student);
            }
        });
        return studentCollection.toArray(new Student[studentCollection.size()]);
    }

}
