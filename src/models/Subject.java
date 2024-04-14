package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

/**
 * A Subject model for representing the subjects
 */
public class Subject implements Model
{
    private int id; 
    private String name; 
    private int groupId;

    private static String modelName = "Subject";
    private static String databasePath = DatabasePath.getDatabasePath(modelName);

    /**
     * A constructor function for initializing the subject model
     * @param name The name of the subject
     * @param groupId The group in which this subject will be passed
     */
    public Subject(String name, int groupId)
    {
        this.name = name;
        this.groupId = groupId;
        this.id = 0;
    }

    /**
     * Getter method for getting the id of the subject
     * @return The id of the subject
     */
    public int getId()
    {
        return this.id; 
    }

    /**
     * Getter method for getting the name of the subject
     * @return The name of the subject
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter method for getting the group that subject will be passed
     * @return The group that subject will be passed
     * @see Group#findById(int)
     */
    public Group getGroup()
    {
        return Group.findById(this.groupId);
    }

    /**
     * Setter method for changing the name of the subject
     * @param name New name of the subject
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter method for setting the id of the subject
     * It set's this object's id by incrementing the object's id that is the latest object in the database.
     */
    private void setId()
    {
        Subject[] subjects = Subject.getAll();
        try
        {
            this.id = subjects[subjects.length - 1].getId() + 1;
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
     * Setter method for changing the group id of the subject
     * @param groupId New group id
     */
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }

    /**
     * Static method for getting all subject objects from the database. 
     * @return An array of objects that fetched from the database
     * @see FileDatabase
     */
    public static Subject[] getAll()
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
        db.load();
        Collection<Subject> subjectCollection = db.getAll(); 
        return subjectCollection.toArray(new Subject[subjectCollection.size()]);
    }

    /**
     * Static method for fetching a subject object from the database by using its id 
     * @param id The id of the object that we are searching for
     * @return The object that fetched from the database using its id
     * @see FileDatabase
     */
    public static Subject findById(int id)
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
        db.load();
        Collection<Subject> subjectCollection = db.getAll(); 
        for(Subject subject : subjectCollection)
        {
            if(subject.getId() == id)
            {
                return subject; 
            }
        }
        return null; 
    }

    /**
     * Static method for getting a group's all subjects
     * @param groupId The id of the group whose subjects are going to be searched. 
     * @return An array of all subjects that found by using the group's id.
     * @see FileDatabase
     */
    public static Subject[] filterByGroup(int groupId)
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
        db.load();
        Collection<Subject> subjectCollection = db.getAll(); 
        Collection<Subject> filteredSubjectList = new ArrayList<Subject>();
        
        for(Subject subject : subjectCollection)
        {
            if(subject.getGroup().getId() == groupId)
            {
                filteredSubjectList.add(subject);
            }
        }
        return filteredSubjectList.toArray(new Subject[filteredSubjectList.size()]);
    }

    /**
     * Overriden method for saving the subject object to the database.
     * It checks at first if the object is new or not. 
     * If the object is new, then it sets the new id and saves the object.
     * If the object exists, then it replaces the existing object with this object. 
     * @see FileDatabase
     */
    @Override 
    public void save()
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
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
     * Overridden method for deleting subject objects from the database. 
     * @see FileDatabase
     */
    @Override
    public void destroy()
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
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
        Subject subject = (Subject) obj;
        if (subject.getId() == this.getId()) return true; 
        return false;
    }
}
