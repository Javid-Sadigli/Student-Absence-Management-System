package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;

import java.lang.ArrayIndexOutOfBoundsException;

import toolkit.DatabasePath;
import toolkit.FileDatabase;

/**
 * A Student model for representing the students
 */
public class Student implements Model
{
    private String fullName;
    private int id; 
    private int groupId; 

    private static String modelName = "Student";
    private static String databasePath = DatabasePath.getDatabasePath(modelName);

    /**
     * A constructor function for initializing a new instance of the Student model
     * @param fullName Full name of the student
     * @param groupId The id of the group that the student belongs to
     */
    public Student(String fullName, int groupId)
    {
        this.fullName = fullName;
        this.groupId = groupId;
        this.id = 0;
    }

    /**
     * Getter method for getting the full name of the student
     * @return Full name of the student
     */
    public String getFullName()
    {
        return this.fullName;
    }

    /**
     * Getter method for getting the id of the student
     * @return Id of the student
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Getter method for getting the group of the student
     * @return Group of the student
     * @see Group#findById(int)
     */
    public Group getGroup()
    {
        return Group.findById(this.groupId);
    }

    /**
     * Getter method for getting the absents of the student
     * @return An array of student's absents
     * @see Absent#filterByStudent(int)
     */
    public Absent[] getAbsents()
    {
        return Absent.filterByStudent(this.id);
    }

    /**
     * Getter method for getting student's absents for a subject
     * @param subjectId The id of the subject that is going to be used for for fetching absents
     * @return The student's absents for a subject
     * @see Absent#filterByStudentAndSubject(int, int)
     */
    public Absent[] getAbsentsForSubject(int subjectId)
    {
        return Absent.filterByStudentAndSubject(this.id, subjectId);
    }

    /**
     * Setter method for changing the full name of the student
     * @param fullName New full name of the student
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * Setter method for changing the group id of the student
     * @param groupId New group id of the student
     */
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }

    /**
     * Setter method for setting the id of the student
     * It set's this object's id by incrementing the object's id that is the latest object in the database.
     */
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

    /**
     * Static method for getting all student objects from the database. 
     * @return An array of objects that fetched from the database
     * @see FileDatabase
     */
    public static Student[] getAll()
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        return studentCollection.toArray(new Student[studentCollection.size()]);
    }

    /**
     * Static method for getting a group's all students
     * @param groupId The id of the group whose students are going to be searched. 
     * @return An array of all students that found by using the group's id.
     * @see FileDatabase
     */
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

    /**
     * Static method for fetching a student object from the database by using its id 
     * @param id The id of the object that we are searching for
     * @return The object that fetched from the database using its id
     * @see FileDatabase
     */
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

    /**
     * Static method for fetching a student object from the database by using its full name
     * @param fullName The full name of the object that we are searching for
     * @return The object that fetched from the database using its full name
     * @see FileDatabase
     */
    public static Student findByName(String fullName)
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        Collection<Student> studentCollection = db.getAll(); 
        for(Student student : studentCollection)
        {
            if(student.getFullName().equals(fullName))
            {
                return student; 
            }
        }
        return null; 
    }
    
    /**
     * Overridden method for checking if this object equals to another or not.
     * It checks if their id's are equal or not. If they are equal, it considers the objects as equal. 
     */
    @Override
    public boolean equals(Object obj) 
    {
        Student student = (Student) obj;
        if (student.getId() == this.getId()) return true; 
        return false;
    }

    /**
     * Overriden method for saving the student object to the database.
     * It checks at first if the object is new or not. 
     * If the object is new, then it sets the new id and saves the object.
     * If the object exists, then it replaces the existing object with this object. 
     * @see FileDatabase
     */
    @Override
    public void save()
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        if(this.id == 0)
        {
            this.setId();        
            db.add(this);
            db.save();
        }
        else 
        {
            db.replace(db.indexOf(this), this);
            db.save();
        }
    }

    /**
     * Overridden method for deleting student objects from the database. 
     * @see FileDatabase
     */
    @Override 
    public void destroy()
    {
        FileDatabase<Student> db = new FileDatabase<Student>(Student.databasePath);
        db.load();
        db.remove(this);
        db.save();
    }
}
