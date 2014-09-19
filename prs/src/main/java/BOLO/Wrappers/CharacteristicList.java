package BOLO.Wrappers;

import BOLO.ProductCharacteristic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CharacteristicList implements Serializable 
{
    @XmlElementWrapper(name="CharacteristicReview")    
    private List<BOLO.ProductCharacteristic> items;
    
    public CharacteristicList()
    {
        items = new ArrayList<BOLO.ProductCharacteristic>();
    }

    public List<BOLO.ProductCharacteristic> getItems() {
        return items;
    }

    public void setItems(List<BOLO.ProductCharacteristic> items) {
        this.items = items;
    }
}
    