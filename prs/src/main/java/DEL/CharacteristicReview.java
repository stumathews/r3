
package DEL;

/**
 * A Product Characteristic will need to be reviewed by a user for the user's take on that characteristic.
 * @author Stuart
 */
public class CharacteristicReview 
{
    private Long id;
    private DEL.Characteristic characteristic;
    private DEL.Review review;
    private DEL.User user;
    private String review_text;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }
    
    
}
