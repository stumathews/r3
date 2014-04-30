/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOLO.Wrappers;

import BOLO.ProductCharacteristic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Stuart
 */
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