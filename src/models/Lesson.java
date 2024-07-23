package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

/**
 * A Lesson model for representing the lessons
 */
public class Lesson implements Model
{
    private int id; 
    private Date date; 
    private int subjectId; 
    private int room; 

    private static String modelName = "Lesson";
    private static String databasePath = DatabasePath.getDatabasePath(modelName);

    /**
     * A constructor function for initializing a new instance of the Lesson model 
     * @param date The date of the lesson
     * @param subjectId The id of the subject that the lesson belongs to
     * @param room The id of the room that the lesson will be
     */
    public Lesson(Date date, int subjectId, int room)
    {
        this.room = room;
        this.date = date;
        this.subjectId = subjectId;
        this.id = 0;
    }

    /**
     * Getter method for getting the id of the lesson
     * @return The id of the lesson
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Getter method for getting the date of the lesson
     * @return The date of the lesson
     */
    public Date getDate()
    {
        return this.date;
    }

    /**
     * Getter method for getting the subject that owns this lesson
     * @return The subject that owns this lesson
     * @see Subject#findById(int)
     */
    public Subject getSubject() 
    {
        return Subject.findById(this.subjectId);
    }

    /**
     * Getter method for getting the room of the lesson
     * @return The room of the lesson
     */
    public int getRoom()
    {
        return this.room;
    }

    /**
     * Setter method for changing the date of the lesson
     * @param date New date of the lesson
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Setter method for changing the room of the lesson
     * @param room New room of the lesson
     */
    public void setRoom(int room)
    {
        this.room = room;
    }

    /**
     * Setter method for changing the subject id of the lesson
     * @param subjectId New subject id of the lesson
     */
    public void setSubjectId(int subjectId)
    {
        this.subjectId = subjectId;
    }

    /**
     * Setter method for setting the id of the lesson
     * It set's this object's id by incrementing the object's id that is the latest object in the database.
     */
    private void setId()
    {
        Lesson[] lessons = Lesson.getAll();
        try
        {
            int length = lessons.length;
            Lesson last = lessons[length - 1];
            this.id = last.getId() + 1;
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
     * Static method for getting all lesson objects from the database. 
     * @return An array of objects that fetched from the database
     * @see FileDatabase
     */
    public static Lesson[] getAll()
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        Collection<Lesson> lessonCollection = db.getAll(); 
        return lessonCollection.toArray(new Lesson[lessonCollection.size()]);
    }

    /**
     * Static method for fetching a lesson object from the database by using its id 
     * @param id The id of the object that we are searching for
     * @return The object that fetched from the database using its id
     * @see FileDatabase
     */
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

    /**
     * Static method for getting a subject's all lessons
     * @param subjectId The id of the subject whose lessons are going to be searched. 
     * @return An array of all lessons that found by using the subject's id.
     * @see FileDatabase
     */
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

    /**
     * Static method for getting the lessons that is in the same room 
     * @param room The room that the lessons will be in
     * @return An array of all lessons 
     * @see FileDatabase
     */
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

    /**
     * Overriden method for saving the lesson object to the database.
     * It checks at first if the object is new or not. 
     * If the object is new, then it sets the new id and saves the object.
     * If the object exists, then it replaces the existing object with this object. 
     * @see FileDatabase
     */
    @Override
    public void save()
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
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
     * Overridden method for deleting lesson objects from the database. 
     * @see FileDatabase
     */
    @Override 
    public void destroy()
    {
        FileDatabase<Lesson> db = new FileDatabase<Lesson>(Lesson.databasePath);
        db.load();
        db.remove(this);
        db.save();
    }

    /**
     * Overridden method for checking if this object equals to another or not.
     * It checks if their id's are equal or not. If they are equal, it considers the objects as equal. 
     */
    @Override
    public boolean equals(Object obj) 
    {
        Lesson lesson = (Lesson) obj;
        if (lesson.getId() == this.getId()) return true; 
        return false;
    }

    /**
     * Overriden method for getting the String representation of the lesson object
     * @return Lesson's date, subject and room
     */
    @Override
    public String toString() 
    {
        String subjectName = (this.getSubject() != null) ? this.getSubject().getName() : "Unknown";
        return "Date: " + this.date.toString() + ", Subject: " + subjectName + ", Room: " + this.room;
    }

}
