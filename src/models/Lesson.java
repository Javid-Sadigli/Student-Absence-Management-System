package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import interfaces.Model;
import toolkit.FileDatabase;

public class Lesson implements Model
{
    private int id; 
    private Date date; 
    private int subjectId; 
    private int room; 

    private static String modelName = "Lesson";
    private static String databasePath = "./database/"+ Lesson.modelName; 

    public Lesson(Date date, int subjectId, int room)
    {
        this.room = room;
        this.date = date;
        this.subjectId = subjectId;
    }

    /* Getters */
    public int getId()
    {
        return this.id;
    }
    public Date getDate()
    {
        return this.date;
    }
    public Subject getSubject()
    {
        return Subject.findById(this.subjectId);
    }
    public int getRoom()
    {
        return this.room;
    }

    /* Setters */
    public void setDate(Date date)
    {
        this.date = date;
    }
    public void setRoom(int room)
    {
        this.room = room;
    }
    public void setSubjectId(int subjectId)
    {
        this.subjectId = subjectId;
    }
    private void setId()
    {
        Lesson[] lessons = Lesson.getAll();
        try
        {
            this.id = lessons[lessons.length - 1].getId() + 1;
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
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        db.add(this);
        db.save();
    }

    /* Static methods */
    public static Lesson[] getAll()
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        Collection<Lesson> lessonCollection = db.getAll(); 
        return lessonCollection.toArray(new Lesson[lessonCollection.size()]);
    }
    public static Lesson findById(int id)
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        Collection<Lesson> lessonCollection = db.getAll(); 
        for(Lesson lesson : lessonCollection)
        {
            if(lesson.getId() == id)
            {
                return lesson; 
            }
        }
        return null; 
    }
    public static Lesson[] filterBySubject(int subjectId)
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        Collection<Lesson> lessonCollection = db.getAll(); 
        Collection<Lesson> filteredLessonList = new ArrayList<Lesson>();
        
        for(Lesson lesson : lessonCollection)
        {
            if(lesson.getSubject().getId() == subjectId)
            {
                filteredLessonList.add(lesson);
            }
        }
        return filteredLessonList.toArray(new Lesson[filteredLessonList.size()]);
    }

    public static Lesson[] filterByRoom(int room)
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        Collection<Lesson> lessonCollection = db.getAll(); 
        Collection<Lesson> filteredLessonList = new ArrayList<Lesson>();
        
        for(Lesson lesson : lessonCollection)
        {
            if(lesson.getRoom() == room)
            {
                filteredLessonList.add(lesson);
            }
        }
        return filteredLessonList.toArray(new Lesson[filteredLessonList.size()]);
    }

}
