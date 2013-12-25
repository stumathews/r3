/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BOL.Characteristic;
import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IProduct;
import BOL.Product;
import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import DAL.Interfaces.IReviewDAO;
import DAL.Interfaces.IUserDAO;
import DEL.Review;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import Utility.SwapableCollection;


public class ReviewDAO implements IReviewDAO {

    private SessionFactory sessionFactory;
    private IUserDAO userDAO;
    private IProductDAO productDAO;
    private DAL.Interfaces.ICharacteristicsDAO characteristicDAO;
    private ICharacteristic characteristicBOL;
    private IProduct productLogic;

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
        del_review.setText(theReview.getText());
        del_review.setLowlights(theReview.getLowlights());
        del_review.setHighlights(theReview.getHighlights());        
        BOLO.Product product = theReview.getProduct();
        del_review.setProduct( productDAO.getProductByID(product.getIdentifier()) );

        // This review consists on one of more characteristics that are saved in the database.
        del_review.setCharacteristics(new HashSet<DEL.Characteristic>());
        for( BOLO.ProductCharacteristic bch : theReview.getCharacteristics())
        {
            // Get the details of the characteristics of this potential review...
            DEL.Characteristic dch = characteristicDAO.getCharacteristic(bch.getId());
            del_review.getCharacteristics().add(dch);
        }        
        
       Session session = sessionFactory.getCurrentSession();
       
       
       session.save(del_review);
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
        
        del_reviews = (ArrayList<DEL.Review>) session.createQuery(String.format("from Review where product_id = %s", productID)).list();
        return del_reviews;
    }
    

}
