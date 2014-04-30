/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOLO.Wrappers;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Stuart
 */
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
