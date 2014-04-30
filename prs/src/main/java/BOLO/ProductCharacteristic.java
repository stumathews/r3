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

  @Override
  public String toString() {
    return "ProductCharacteristic{" + "title=" + title + ", description=" + description + ", Id=" + Id + '}';
  }
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
    
    @Override  
    public boolean equals(Object obj) {  
        if (obj == null) { return false; }  
        if (getClass() != obj.getClass()) { return false; }  
        if (! super.equals(obj)) return false;
        else {
           // compare subclass fields
          ProductCharacteristic otherObject = (ProductCharacteristic) obj;
          return getTitle().equalsIgnoreCase(otherObject.getTitle()) && 
                  getDescription().equalsIgnoreCase(otherObject.getDescription());                  
        }
     
    
    
}

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + (this.title != null ? this.title.hashCode() : 0);
    hash = 31 * hash + (this.description != null ? this.description.hashCode() : 0);
    return hash;
  }
}
