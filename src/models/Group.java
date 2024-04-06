package models;

import java.util.Collection;

import interfaces.Model;

import toolkit.FileDatabase;

public class Group implements Model
{
    private String name;
    private int id;

    private static String modelName = "Group";
    private static String databasePath = "./database"+ Group.modelName; 

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
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        db.add(this);
        db.save();
    }

    public Student[] getStudents()
    {
        return Student.filterByGroup(this.id); 
    }


    /* Static methods */
    public static Group[] getAll()
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        Collection<Group> groupCollection = db.getAll(); 
        return groupCollection.toArray(new Group[groupCollection.size()]);
    }

    


}
