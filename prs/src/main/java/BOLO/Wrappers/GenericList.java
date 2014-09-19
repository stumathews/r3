package BOLO.Wrappers;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

public class GenericList implements Serializable 
{
    @XmlElementWrapper(name="characteristics")    
    private List items;
    
    public GenericList()
    {
       
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
