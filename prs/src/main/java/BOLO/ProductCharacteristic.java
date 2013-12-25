/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

import DEL.Product;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Product characteristic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class ProductCharacteristic implements Serializable
{
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;        
    private Long Id;
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
