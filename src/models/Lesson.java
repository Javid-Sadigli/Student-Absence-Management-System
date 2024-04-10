package models;

import java.util.Collection;
import java.util.Date;

import interfaces.Model;
import toolkit.FileDatabase;

public class Lesson implements Model
{
    private int id; 
    private Date date; 
    private int groupId;
    private int subjectId; 
    private int room; 

    private static String modelName = "Lesson";
    private static String databasePath = "./database/"+ Lesson.modelName; 

    public Lesson(Date date, int groupId, int subjectId, int room)
    {
        this.room = room;
        this.date = date;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.setId();
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
    public Group getGroup()
    {
        return Group.findById(this.groupId);
    }
    // public Subject getSubject()
    // {
        
    // }

    /* Setters */
    public void setDate(Date date)
    {
        this.date = date;
    }
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
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


}
