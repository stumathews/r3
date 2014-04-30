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
    