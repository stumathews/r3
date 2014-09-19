package BOLO.Wrappers;

import BOLO.ProductCharacteristic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CharacteristicReviewList implements Serializable 
{
    @XmlElementWrapper(name="CharacteristicReview")    
      private List<BOLO.CharacteristicReview> items;
    
    public CharacteristicReviewList()
    {
        items = new ArrayList<BOLO.CharacteristicReview>();
    }

    public List<BOLO.CharacteristicReview> getItems() {
        return items;
    }

    public void setItems(List<BOLO.CharacteristicReview> items) {
        this.items = items;
    }
}