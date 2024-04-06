package parents;

import java.io.Serializable;

import toolkit.FileDatabase;

public abstract class Model implements Serializable
{ 
    protected String modelName; 
    public void save()
    {
        FileDatabase<Model> db = new FileDatabase<Model>(this.getDatabasePath());
        db.load();
        db.add(this);
        db.save();
    }

    /* Getters */
    public String getModelName()
    {
        return this.modelName;
    }

    public String getDatabasePath()
    {
        return "./database/" + this.getModelName();
    }; 
    

    protected abstract void setModelName(); 

}
