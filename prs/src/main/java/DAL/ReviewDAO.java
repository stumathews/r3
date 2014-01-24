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

        DEL.Review del_review = new DEL.Review();        
            del_review.setCharacteristicReviews(new HashSet<CharacteristicReview>());
            del_review.setText(theReview.getText());
            del_review.setLowlights(theReview.getLowlights());
            del_review.setHighlights(theReview.getHighlights());
            del_review.setReviewer(userDAO.retrieve(theReview.getReviewer().getUsername(), theReview.getReviewer().getPassword()));
            
            
                    
        
        BOLO.Product product = theReview.getProduct();
            del_review.setProduct( productDAO.getProductByID(product.getIdentifier()) );

        // This review consists on one of more characteristicReviews that are saved in the database.
        
        Set<DEL.CharacteristicReview> dCharacteristicReviews = new HashSet<CharacteristicReview>();
        for( BOLO.CharacteristicReview chr : theReview.getCharacteristicReviews())
        {
            
            DEL.CharacteristicReview dchr = new CharacteristicReview();
                dchr.setReview_text(chr.getReview_text());
                
            DEL.Characteristic dCharacteristic = new DEL.Characteristic();                        
                dCharacteristic.setCreator(null); //TODO: we should set this
                dCharacteristic.setDescription(chr.getCharacteristic().getDescription());            
                dCharacteristic.setName(chr.getCharacteristic().getTitle());
                dCharacteristic.setProduct(del_review.getProduct());
                dCharacteristic.setUseful_value(0);
                
                dchr.setCharacteristic(dCharacteristic);                
                dCharacteristicReviews.add(dchr);
        }        
        
       Session session = sessionFactory.getCurrentSession();
       
       // first save the review
       session.save(del_review);
       // then add objects to it
       for( DEL.CharacteristicReview dCharacteristicReview : dCharacteristicReviews)
       {
           dCharacteristicReview.setReview(del_review);
           
           session.save(dCharacteristicReview);
       }
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

    public List<DEL.Review> getProductReviews(String productID) throws Exception 
    {        
        Session session = sessionFactory.getCurrentSession();	        
        List<DEL.Review> del_reviews = new ArrayList<DEL.Review>();     
        
        del_reviews = (ArrayList<DEL.Review>) session.createCriteria(DEL.Review.class)
                                                      .setFetchMode("characteristicReviews", FetchMode.JOIN)
                                                      .createCriteria("product")
                                                      .add(Restrictions.eq("id", Long.parseLong(productID)))
                                                      .list();
                //.createQuery(String.format("from Review where product_id = %s", productID)).list();
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
