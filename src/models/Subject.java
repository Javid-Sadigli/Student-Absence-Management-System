package models;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

public class Subject implements Model
{
    private int id; 
    private String name; 
    private int groupId;

    private static String modelName = "Subject";
    private static String databasePath = DatabasePath.getDatabasePath(modelName + ".fdb");

    public Subject(String name, int groupId)
    {
        this.name = name;
        this.groupId = groupId;
    }

    /* Getters */
    public int getId()
    {
        return this.id; 
    }
    public String getName()
    {
        return this.name;
    }
    public Group getGroup()
    {
        return Group.findById(this.groupId);
    }

    /* Setters */
    public void setName(String name)
    {
        this.name = name;
    }
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
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }


    public void save()
    {
        this.setId();
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
        db.load();
        db.add(this);
        db.save();
    }

    /* Static methods */
    public static Subject[] getAll()
    {
        FileDatabase<Subject> db = new FileDatabase<Subject>(Subject.databasePath);
        db.load();
        Collection<Subject> subjectCollection = db.getAll(); 
        return subjectCollection.toArray(new Subject[subjectCollection.size()]);
    }
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
}
