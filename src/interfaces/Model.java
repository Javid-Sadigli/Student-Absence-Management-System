package interfaces;

import java.io.Serializable;

/**
 * An interface that all Models of this project will be implemented from. 
 * Extends the interface 'Serializable' for saving objects. 
 */
public interface Model extends Serializable
{ 
    public void save();
    public void destroy();
    public boolean equals(Object obj); 
}
