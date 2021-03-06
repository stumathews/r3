package DEL;

import java.io.Serializable;
import java.util.Set;


/**
 * A product
 * @author Stuart
 *
 */

public class Product implements Serializable{

    private Long id;
    private String title;
    private String whatIsIt;
    private String whoMadeIt;
    private String imageURL;
    private Set<Characteristic> characteristics;
    private Set<Review> reviews;

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getWhatIsIt() {
        return whatIsIt;
    }

    public void setWhatIsIt(String whatIsIt) {
        this.whatIsIt = whatIsIt;
    }

    
    public String getWhoMadeIt() {
        return whoMadeIt;
    }

    public void setWhoMadeIt(String whoMadeIt) {
        this.whoMadeIt = whoMadeIt;
    }

    public Product() {
    }

    public Product(String title) 
    {
        setTitle(title);
    }

    public Long getId() {
        return this.id;

    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
