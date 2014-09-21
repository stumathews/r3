package BOLO.Wrappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CharacteristicList implements Serializable 
{
    @XmlElementWrapper(name="CharacteristicReview")    
    public List items;
    
    public CharacteristicList()
    {
        items = new ArrayList<DEL.Characteristic>();
    }

    public List<DEL.Characteristic> getItems() {
        return items;
    }

    public void setItems(List<DEL.Characteristic> items) {
        this.items = items;
    }
}
    