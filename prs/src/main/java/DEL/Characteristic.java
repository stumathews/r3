
package DEL;

/**
 * A Characteristic
 * @author Stuart
 */


public class Characteristic 
{    
    private Long id;
    private String name;
    private String description;
    private int useful_value;
    private String review;
    private User creator;
    private Product product;
    
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
    
}
