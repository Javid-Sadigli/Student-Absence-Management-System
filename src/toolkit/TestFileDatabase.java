package toolkit; 

// You must import those:
import java.io.Serializable;
import java.util.Collection;

public class TestFileDatabase {

    // Your object could be of an inner class, a nested static class or any other regular class
    // But it needs to implement *Serializable*

    /* Example of object (could be any one) */
    private static class MyObject implements Serializable {
        private int age;
        private String name;

        public MyObject(String name, int age) {
            this.age = age;
            this.name = name; 
        }

        public String toString() {
            return this.name + " (" + this.age + ")";
        }
    }
    /* ----------------------------------- */

    public static void main(String[] args) {
        /* Instantiate the FileDatabase with your object as type
         * You can choose here the filename that will work as your database
         */
        FileDatabase<MyObject> db = new FileDatabase<>("myobject");
    

        /* You can create and add objects to the database */
        MyObject obj1 = new MyObject("Edward", 21);
        MyObject obj2 = new MyObject("Milla", 23);
        MyObject obj3 = new MyObject("Enya", 19);
        db.add(obj2);
        db.add(obj1);
        db.add(obj3);
        db.add(new MyObject("Jimmy", 18));

        /* When you save the database, your collection of objects
         * will be dumped from memory into a file.
         */
        db.save();

        FileDatabase<MyObject> loadedDb = new FileDatabase<>("myobject");
        /* Here you can load the database; if there is no database yet,
         * it will create an "empty" database file.
         */
        loadedDb.load();

        /* getAll() will get all objects from the database and return them
         * in a ArrayList()
         */
        System.out.println("Loaded collection: " + loadedDb.getAll());
        
        /* search(predicate) will use the predicate (a lambda expression which
         * returns true/false) to filter the objects in the database that fits 
         * that condition (has this predicate) and return them in an ArrayList()
         */ 
        Collection<MyObject> searchResult = db.search(
            obj -> obj.name.startsWith("E") 
            // lambda expression that search for objects which have in the 'name' 
            // attribute a string that starts with the letter "E"
        );
        System.out.println("Search result: " + searchResult);

        /* get(Integer i) will return your object held by in the index i */
        System.out.println(loadedDb.get(2));
    }
}
