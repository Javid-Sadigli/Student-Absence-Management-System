package models;

import java.util.Collection;

import parents.Model;
import toolkit.FileDatabase;

public class Group extends Model
{
    private String name;
    private int id;

    public Group(String name)
    {
        this.name = name;
        
    }

    /* Getters */
    public String getName()
    {
        return this.name;
    }
    public int getId()
    {
        return this.id;
    }

    /* Setters */
    public void setName(String name)
    {
        this.name = name;
    }


    public void save()
    {
        FileDatabase<Group> db = new FileDatabase<Group>("./database/Group");
        db.load();
        db.add(this);
        db.save();
    }


    /* Static methods */
    public static Group[] getAll()
    {
        FileDatabase<Group> db = new FileDatabase<Group>("./database/Group");
        db.load();
        Collection<Group> groupCollection = db.getAll(); 
        return groupCollection.toArray(new Group[groupCollection.size()]);
    }

    public void setModelName()
    {
        this.modelName = "Group";
    }


}
