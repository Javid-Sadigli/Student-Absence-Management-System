package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;
import toolkit.FileDatabase;

public class Absent implements Model
{
    private int id; 
    private int studentId; 
    private int lessonId; 

    private static String modelName = "Absent";
    private static String databasePath = "./database/"+ Absent.modelName; 


    /* Getters */
    public int getId()
    {
        return this.id;
    }
    public Student getStudent()
    {
        return Student.findById(this.studentId);
    }
    public Lesson getLesson()
    {
        return Lesson.findById(this.lessonId);
    }

    /* Setters */
    private void setId()
    {
        Absent[] absents = Absent.getAll();
        try
        {
            this.id = absents[absents.length - 1].getId() + 1;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            if(e.getMessage().equals("Index -1 out of bounds for length 0"))
            {
                this.id = 1; 
            }
        }
    }
    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }
    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    public void save()
    {
        this.setId();
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        db.add(this);
        db.save();
    }

    /* Static methods */
    public static Absent[] getAll()
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        return absentCollection.toArray(new Absent[absentCollection.size()]);
    }
    public static Absent findById(int id)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        for(Absent absent: absentCollection)
        {
            if(absent.getId() == id)
            {
                return absent; 
            }
        }
        return null; 
    }
    
    public static Absent[] filterByStudent(int studentId)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        Collection<Absent> filteredAbsentList = new ArrayList<Absent>();
        
        for(Absent absent : absentCollection)
        {
            if(absent.getStudent().getId() == studentId)
            {
                filteredAbsentList.add(absent);
            }
        }
        return filteredAbsentList.toArray(new Absent[filteredAbsentList.size()]);
    }

    public static Absent[] filterBySubject(int subjectId)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        Collection<Absent> filteredAbsentList = new ArrayList<Absent>();
        
        for(Absent absent : absentCollection)
        {
            if(absent.getLesson().getSubject().getId() == subjectId)
            {
                filteredAbsentList.add(absent);
            }
        }
        return filteredAbsentList.toArray(new Absent[filteredAbsentList.size()]);
    }

    public static Absent[] filterByStudentAndSubject(int studentId, int subjectId)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        Collection<Absent> filteredAbsentList = new ArrayList<Absent>();
        
        for(Absent absent : absentCollection)
        {
            if(absent.getLesson().getSubject().getId() == subjectId && absent.getStudent().getId() == studentId)
            {
                filteredAbsentList.add(absent);
            }
        }
        return filteredAbsentList.toArray(new Absent[filteredAbsentList.size()]);
    }


}
