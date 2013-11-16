/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A business recommendation
 * @author stuartm
 */
public class Recommendation 
{

    
    /* A recommendation is made up of many reviews */
    private List<BOLO.Review> reviews;
    /* Is this a recommend or a not recommend */
    private boolean recommend;
    /* Whats the valud of this recommendation/ */
    private int value;    
    /* Person that has compiled the review */
    private BOLO.User recommender;
    /* Every recommendation has a title to identify it. */
    @NotEmpty
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public User getRecommender() {
        return recommender;
    }

    public void setRecommender(User recommender) {
        this.recommender = recommender;
    }
    
    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
