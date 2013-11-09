
package DEL;


import java.util.List;
import java.util.Set;


/**
 * A Recommendation
 * @author Stuart
 */

public class Recommendation
{
    private static final long serialVersionUID = 1L;    
    private Long id;
    private Set<DEL.Review> reviews;
    private boolean recommend;
    private int value;
    private String title;

    public User getRecommender() {
        return recommender;
    }

    public void setRecommender(User recommender) {
        this.recommender = recommender;
    }
    private DEL.User recommender;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<DEL.Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<DEL.Review> reviews) {
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
    
    
        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Recommendation)) {
            return false;
        }
        Recommendation other = (Recommendation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.DEL.Recommendation[ id=" + id + " ]";
    }
    
}
