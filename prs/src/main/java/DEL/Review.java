
package DEL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A Review
 * @author Stuart
 */

public class Review  {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String Text;
    private int rating;    
    private String highlights;
    private String lowlights;
    private Recommendation recommendation;
    private Set<CharacteristicReview> characteristicReviews;
    private Product product;
    private DEL.User reviewer;
    private int reuse_rate;   
    private int credibility_rating; //Rating depending on something - system value

        
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    } 

    public Set<CharacteristicReview> getCharacteristicReviews() {
        return characteristicReviews;
    }

    public void setCharacteristicReviews(Set<CharacteristicReview> characteristicReviews) {
        this.characteristicReviews = characteristicReviews;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
    
    
    

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getLowlights() {
        return lowlights;
    }

    public void setLowlights(String lowlights) {
        this.lowlights = lowlights;
    }
    

    public int getCredibility_rating() {
        return credibility_rating;
    }

    public void setCredibility_rating(int credibility_rating) {
        this.credibility_rating = credibility_rating;
    }
    

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(DEL.User reviewer) {
        this.reviewer = reviewer;
    }

    public int getReuse_rate() {
        return reuse_rate;
    }

    public void setReuse_rate(int reuse_rate) {
        this.reuse_rate = reuse_rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
