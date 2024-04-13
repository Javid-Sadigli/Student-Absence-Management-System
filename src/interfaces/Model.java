package interfaces;

import java.io.Serializable;

public interface Model extends Serializable
{ 
    public void save();
    public void destroy();
    public boolean equals(Object obj); 
}
