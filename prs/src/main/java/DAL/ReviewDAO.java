/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BOL.Characteristic;
import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IProduct;
import BOLO.Review;
import DAL.Interfaces.ICharacteristicReviewDAO;
import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import DAL.Interfaces.IReviewDAO;
import DAL.Interfaces.IUserDAO;
import DEL.CharacteristicReview;
import DEL.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


public class ReviewDAO implements IReviewDAO {

    private SessionFactory sessionFactory;
    private IUserDAO userDAO;
    private IProductDAO productDAO;
    private DAL.Interfaces.ICharacteristicsDAO characteristicDAO;
    private ICharacteristic characteristicBOL;
    private IProduct productLogic;
    private DAL.Interfaces.ICharacteristicReviewDAO characteristicReviewDAO;

    @Autowired
    public void setCharacteristicReviewDAO(ICharacteristicReviewDAO characteristicReviewDAO) {
        this.characteristicReviewDAO = characteristicReviewDAO;
    }

    @Autowired
    public void setCharacteristicDAO(ICharacteristicsDAO characteristicDAO) {
        this.characteristicDAO = characteristicDAO;
    }
    @Autowired
    public void setCharacteristicBOL(ICharacteristic characteristicBOL) {
        this.characteristicBOL = characteristicBOL;
    }
    @Autowired
    public void setProductLogic(IProduct productLogic) {
        this.productLogic = productLogic;
    }

    @Autowired
    public void setCharacteristicBOL(Characteristic characteristicBOL) {
        this.characteristicBOL = characteristicBOL;
    }
    
    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    

    
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
    public void SaveReview(BOLO.Review theReview) throws Exception
    {
        Session session = sessionFactory.getCurrentSession();
        
        DEL.Review  del_review = new DEL.Review();
                    del_review.setCredibility_rating(0);
                    del_review.setHighlights(theReview.getHighlights());
                    del_review.setLowlights(theReview.getLowlights());
                    del_review.setRating(0);
                    del_review.setRecommendation(null);
                    del_review.setReuse_rate(0);
                    del_review.setText(theReview.getText());
                    
        session.save(del_review); //del_review is how persistant tracked variable...
        
        for( BOLO.CharacteristicReview bCharacteristicReview : theReview.getCharacteristicReviews())
        {
          DEL.CharacteristicReview dCharacteristicReview = new CharacteristicReview();
                                   dCharacteristicReview.setReview(del_review);
                                   session.save(dCharacteristicReview); // This is persistant now
          
          DEL.Characteristic dCharacteristic = (DEL.Characteristic) 
                                                session.get(DEL.Characteristic.class,
                                                            bCharacteristicReview.getCharacteristic().getId()); // this i spersistant now
            
                                  dCharacteristicReview.setCharacteristic(dCharacteristic);
                                  dCharacteristicReview.setReview_text(theReview.getText());
                                  dCharacteristicReview.setUser(dCharacteristicReview.getCharacteristic().getCreator());
                                  dCharacteristicReview.setReview_text(bCharacteristicReview.getReview_text());
          
          if( del_review.getReviewer() == null )
          {
            DEL.User user = userDAO.getUser(theReview.getReviewer().getUsername());
            del_review.setReviewer(user);            
          }
          
          if( del_review.getProduct() == null)
          {
            del_review.setProduct(dCharacteristicReview.getCharacteristic().getProduct());
          }          
          del_review.getCharacteristicReviews().add(dCharacteristicReview);
          
        }
        
            
        
                
        
       
    }

    /***
     * Gets all reviews as Business objects
     * @return 
     */
    public List<DEL.Review> getAllReviews()
    {        
        return getAllRawReviews();
    }

    public List<DEL.Review> getProductReviews(String productID) throws Exception 
    {        
        Session session = sessionFactory.getCurrentSession();	        
        List<DEL.Review> del_reviews = new ArrayList<DEL.Review>();    
        
        del_reviews = (ArrayList<DEL.Review>) session.createCriteria(DEL.Review.class)                                  
                                                      .createCriteria("product")
                                                      .add(Restrictions.eq("id", Long.parseLong(productID)))
                                                      .list();
                //.createQuery(String.iformat("from Review where product_id = %s", productID)).list();
        return del_reviews;
    }

  public List<DEL.Review> getUserReviews(User user) throws Exception 
  {
    
    // Get reviews for this user only.
    Session session = sessionFactory.getCurrentSession();    
    List<DEL.Review> user_reviews = session.createQuery(String.format("from Review where user_id = %s",user.getId())).list();
    
    return user_reviews;
    
  }
    

}
