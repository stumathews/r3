/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.Interfaces.IReviewDAO;
import DAL.Interfaces.IUserDAO;
import DEL.Review;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class ReviewDAO implements IReviewDAO {

    private SessionFactory sessionFactory;
    private IUserDAO userDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    
    
    @Autowired 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /***
     * Gets the DEL reviews
     * @return  List<DAL.DEL.Review>
     * @throws Exception 
     */    
    private List<DEL.Review> getAllRawReviews() 
    {
        ArrayList<DEL.Review> reviews = new ArrayList<DEL.Review>();
        
        Session session = sessionFactory.getCurrentSession();		
        reviews = (ArrayList<DEL.Review>) session.createQuery("from Review").list();
        
        return reviews;
    }    
    
    /***
     * Saves a BOLO.Review as best it can( converts to DEL.Review)
     * @param theReview
     */
    public void SaveReview(BOLO.Review theReview)
    {
        
        DEL.Review review = new DEL.Review();
        review.setText(theReview.getText());
        review.setLowlights(theReview.getLowlights());
        review.setHighlights(theReview.getHighlights());
           
       Session session = sessionFactory.getCurrentSession();
       
       
       session.save(review);
    }

    /***
     * Gets all reviews as Business objects
     * @return 
     */
    public List<BOLO.Review> getAllReviews(){
        
        List<BOLO.Review> reviews = new ArrayList<BOLO.Review>();
        for( DEL.Review review : getAllRawReviews() )
        {
            BOLO.Review newReview = new BOLO.Review();
            newReview.setText(review.getText());
            newReview.setHighlights(review.getHighlights());
            newReview.setLowlights(review.getLowlights());
            
            reviews.add(newReview);
        }
        return reviews;
    }
    

}
