package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

/**
 * An Absent model for representing absents of students. 
 */
public class Absent implements Model
{
    private int id; 
    private int studentId; 
    private int lessonId; 

    private static String modelName = "Absent";
    private static String databasePath = DatabasePath.getDatabasePath(modelName);

    /**
     * A constructor function for initializing a new instance of the Absent model
     * @param studentId The ID of the student that the absent belongs to
     * @param lessonId The ID of the lesson that the absent got from
     */
    public Absent(int studentId, int lessonId)
    {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.id = 0;
    }

    /**
     * Getter method of Absent model for getting the ID of object.
     * @return the ID of the object.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Getter method of absent model for getting the student that this absent belongs to.
     * @return the student that this absent belongs to
     */
    public Student getStudent()
    {
        return Student.findById(this.studentId);
    }

    /**
     * Getter method of absent model for getting the lesson that this absent was got from
     * @return the lesson that this absent was got from
     */
    public Lesson getLesson()
    {
        return Lesson.findById(this.lessonId);
    }

    /**
     * Setter method of absent model for setting the id automatically.
     * It set's this object's id by incrementing the object's id that is the latest object in the database.
     */
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

    /**
     * Setter method for changing studentId attribute. 
     * @param studentId New studentId attribute
     */
    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    /**
     * Setter method for changing lessonId attribute.
     * @param lessonId New lessonId attribute
     */
    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    /**
     * Static method for getting all absent objects from the database. 
     * @return An array of objects that fetched from the database
     * @see FileDatabase
     */
    public static Absent[] getAll()
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        return absentCollection.toArray(new Absent[absentCollection.size()]);
    }

    /**
     * Static method for fetching an absent object from the database by using its id 
     * @param id The id of the object that we are searching for
     * @return The object that fetched from the database using its id
     * @see FileDatabase
     */
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

    /**
     * Static method for getting a student's all absents.
     * @param studentId The id of the student whose absents are going to be searched. 
     * @return An array of all absents that found by using the student's id.
     * @see FileDatabase
     */
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

    /**
     * Static method for getting all absents for a lesson object.
     * @param lessonId The id of the lesson that is going to be used for fetching absents
     * @return An array of all absents that found by using the lesson id. 
     * @see FileDatabase
     */
    public static Absent[] filterByLesson(int lessonId)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        Collection<Absent> filteredAbsentList = new ArrayList<Absent>();
        
        for(Absent absent : absentCollection)
        {
            if(absent.getLesson().getId() == lessonId)
            {
                filteredAbsentList.add(absent);
            }
        }
        return filteredAbsentList.toArray(new Absent[filteredAbsentList.size()]);
    }

    /**
     * A static method for finding a student's absent for a spesific lesson
     * @param lessonId The id of the lesson that is going to be used for fetching the absents
     * @param studentId The id of the student that is going to be used for fetching the absents
     * @return An array of student's absents for a spesific lesson
     * @see FileDatabase
     */
    public static Absent findByLessonAndStudent(int lessonId, int studentId)
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
        db.load();
        Collection<Absent> absentCollection = db.getAll(); 
        
        for(Absent absent : absentCollection)
        {
            if(absent.getLesson().getId() == lessonId && absent.getStudent().getId() == studentId)
            {
                return absent;
            }
        }
        return null; 
    }

    /**
     * A static method for getting all absents for one subject.
     * @param subjectId The id of the subject that is going to be used for fetching absents
     * @return An array of all absents that found by using the subject id.
     * @see FileDatabase
     */
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

    /**
     * A static method for finding a student's absents for only one subject
     * @param studentId The id of the student that is going to be used for fetching the absents
     * @param subjectId The id of the subject that is going to be used for fetching the absents
     * @return An array of student's absents for one subject 
     * @see FileDatabase
     */
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


    /**
     * Overriden method for saving the absent object to the database.
     * It checks at first if the object is new or not. 
     * If the object is new, then it sets the new id and saves the object.
     * If the object exists, then it replaces the existing object with this object. 
     * @see FileDatabase
     */
    @Override
    public void save()
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
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
     * Overridden method for deleting absent objects from the database. 
     * @see FileDatabase
     */
    @Override
    public void destroy()
    {
        FileDatabase<Absent> db = new FileDatabase<Absent>(Absent.databasePath);
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
        Absent absent = (Absent) obj;
        if (absent.getId() == this.getId()) return true; 
        return false;
    }
}
