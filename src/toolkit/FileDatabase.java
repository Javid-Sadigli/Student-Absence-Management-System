package toolkit; 

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A database class that stores objects in a file.
 * @param <T> The type of objects to store, must be serializable.
 */
public class FileDatabase<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Collection<T> collection;
    private String filePath = "auto.fdb";

    /**
     * Constructs a new FileDatabase with an empty collection. The default file path
     * used for saving and loading is 'auto.fdb'
     */
    public FileDatabase() {
        this.collection = new ArrayList<>();
    }

    /**
     * Constructs a new FileDatabase with an empty collection and a specified file path.
     * @param filePath The file path to use for saving and loading the collection. If
     * the extension is not given, '.fdb' will be use automatically.
     */
    public FileDatabase(String filePath) {
        this.collection = new ArrayList<>();
        this.filePath = filePath.endsWith(".fdb")?
            filePath : filePath + ".fdb";
    }

    /**
     * Searches the collection for objects that match the given predicate.
     * @param predicate The predicate to use for filtering the collection.
     * @return A collection of objects that match the predicate.
     */
    public Collection<T> search(Predicate<T> predicate) {
        return this.collection
            .stream()
            .filter(predicate)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Adds an object to the collection.
     * @param obj The object to add to the collection.
     */
    public void add(T obj) {
        this.collection.add(obj);
    }

    /**
     * Removes an object from the collection.
     * @param obj The object to remove from the collection.
     */
    public void remove(T obj) {
        this.collection.remove(obj);
    }

    /**
     * Gets the object at the specified index in the collection.
     * @param index The index of the object to get.
     * @return The object at the specified index.
     */
    public T get(Integer index) {
        return new ArrayList<>(this.collection).get(index);
    }

    /**
     * Gets all objects in the collection.
     * @return A collection containing all objects in the collection.
     */
    public Collection<T> getAll() {
        return new ArrayList<>(this.collection);
    }

    /**
     * Saves the collection to the file specified by the file path.
     * @see #FileDatabase(String)
     */
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(this.filePath))
            ) {
            oos.writeObject(this.collection);
            // System.out.println("Data saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving collection to file: "
                + e.getMessage());
        }
    }

    /**
     * Loads the collection from the file specified by the file path.
     * If the file is missing, it will save an empty file.
     * @see #FileDatabase(String)
     */
    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))
            )
        {
            this.collection = (Collection<T>) ois.readObject();
            // System.out.println("Data loaded from file: " + filePath);
        } catch (IOException e) {
            this.save(); // missing file, saving a new empty one
            // System.out.println("Data saved to file: " + filePath);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading collection from file: "
                + e.getMessage());
        }
    }
}