package models;

import java.util.Collection;

import interfaces.Model;
import toolkit.DatabasePath;
import toolkit.FileDatabase;

/**
 * A Group model for representing groups of students
 */
public class Group implements Model
{
    private String name;
    private int id;

    private static String modelName = "Group";
    private static String databasePath = DatabasePath.getDatabasePath(modelName);

    /**
     * A constructor function for initializing a new instance of the Group model
     * @param name The name of the group
     */
    public Group(String name)
    {
        this.name = name;
        this.id = 0;
    }

    /**
     * Getter method for getting the name of the group
     * @return The name of the group
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter method for getting the id of the group
     * @return The id of the group
     */
    public int getId()
    {
        return this.id;
    }
    /**
     * Setter method for changing the name of the group
     * @param name New name of the group
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter method for setting the id of the group 
     * It set's this object's id by incrementing the object's id that is the latest object in the database.
     */
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

    /**
     * Method for getting the students of the group
     * @return An array of students of this group
     * @see Student#filterByGroup(int)
     */
    public Student[] getStudents()
    {
        return Student.filterByGroup(this.id); 
    }

    /**
     * Method for getting all groups from the database
     * @return An array of groups that fetched from the database
     * @see FileDatabase
     */
    public static Group[] getAll()
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
        db.load();
        Collection<Group> groupCollection = db.getAll(); 
        return groupCollection.toArray(new Group[groupCollection.size()]);
    }

    /**
     * Static method for fetching a group object from the database by using its id 
     * @param id The id of the object that we are searching for
     * @return The object that fetched from the database using its id
     * @see FileDatabase
     */
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

    /**
     * Overriden method for saving the group object to the database.
     * It checks at first if the object is new or not. 
     * If the object is new, then it sets the new id and saves the object.
     * If the object exists, then it replaces the existing object with this object. 
     * @see FileDatabase
     */
    @Override
    public void save()
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
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
     * Overridden method for deleting group objects from the database. 
     * @see FileDatabase
     */
    @Override
    public void destroy()
    {
        FileDatabase<Group> db = new FileDatabase<Group>(Group.databasePath);
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
        Group group = (Group) obj;
        if (group.getId() == this.getId()) return true; 
        return false;
    }
}