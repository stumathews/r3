package DEL;

import java.io.Serializable;
import java.util.Set;

/**
 * A Characteristic
 * @author Stuart
 */


public class Characteristic implements Serializable
{    
    private Long id;
    private String name;
    private String description;
    private int useful_value;    
    private User creator;
    private Product product;
    private Set<CharacteristicReview> characteristicReviews;

  public Set<CharacteristicReview> getCharacteristicReviews() {
    return characteristicReviews;
  }

  public void setCharacteristicReviews(Set<CharacteristicReview> characteristicReviews) {
    this.characteristicReviews = characteristicReviews;
  }
    
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    

    public int getUseful_value() {
        return useful_value;
    }

    public void setUseful_value(int useful_value) {
        this.useful_value = useful_value;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
    
    @Override
    public int hashCode(){      
    int hash = 7;
    hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
    hash = 31 * hash + (this.description != null ? this.description.hashCode() : 0);
    return hash;
    }
    
    @Override 
    public boolean equals( Object obj){
      if( obj == null){
        return false;
      }
      if(!(obj instanceof DEL.Characteristic)){
        return false;
      }
      return this.id == ((DEL.Characteristic)obj).getId();
    }
    
}
