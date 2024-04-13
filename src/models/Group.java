package models;

import java.util.Collection;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

public class Group implements Model
{
    private String name;
    private int id;

    private static String modelName = "Group";
    private static String databasePath = DatabasePath.getDatabasePath(modelName + ".fdb");

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
    private void setId()
    {
        Group[] groups = Group.getAll();
        try
        {
            this.id = groups[groups.length - 1].getId() + 1;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            if(e.getMessage().equals("Index -1 out of bounds for length 0"))
            {
                this.id = 1; 
            }
        }
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
    public static Group findById(int id)
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        Collection<Group> groupCollection = db.getAll(); 
        for(Group group : groupCollection)
        {
            if(group.getId() == id)
            {
                return group; 
            }
        }
        return null; 
    }


    @Override
    public void save()
    {
        this.setId();
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        db.add(this);
        db.save();
    }

    @Override
    public void destroy()
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        db.remove(this);
        db.save();
    }

    @Override
    public boolean equals(Object obj) 
    {
        Group group = (Group) obj;
        if (group.getId() == this.getId()) return true; 
        return false;
    }
    


}
