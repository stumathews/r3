/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IReview;
import BOLO.CharacteristicReview;
import BOLO.ProductCharacteristic;
import DAL.Interfaces.IReviewDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import Utility.SwapableCollection;
import java.util.AbstractList;

/**
 * Review business logic
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class Review implements IReview 
{

    private IReviewDAO reviewDAO;
    private ICharacteristic characteristicBOL;
        
   @Autowired
   public void setCharacteristicBOL(ICharacteristic characteristicBOL) {
        this.characteristicBOL = characteristicBOL;
    }
    

    @Autowired
    public void setReviewDAO(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }
    
  
    
    public List<BOLO.Review> getAllReviews() throws Exception
    {
        return reviewDAO.getAllReviews();
    }
    

    public void SaveReview(BOLO.Review theReview) throws Exception {
        reviewDAO.SaveReview( theReview );
    }

    public List<BOLO.Review> getProductReviews(String productID) throws Exception 
    {
        List<BOLO.Review> bolo_reviews = new ArrayList<BOLO.Review>();
        List<DEL.Review> del_reviews =  reviewDAO.getProductReviews(productID);
        for( DEL.Review dr : del_reviews)
        {
            BOLO.Review review = new BOLO.Review();
            review.setHighlights(dr.getHighlights()); 
            review.setLowlights(dr.getLowlights());
            review.setText(dr.getText());
            
            BOLO.User user = new BOLO.User();
            user.setUsername(dr.getReviewer().getUsername());
            user.setPassword(dr.getReviewer().getPassword());
            
            for( DEL.CharacteristicReview dchr : dr.getCharacteristicReviews())
            {
                BOLO.CharacteristicReview characteristicReview = new BOLO.CharacteristicReview();
                characteristicReview.setId(dchr.getId());
                characteristicReview.setReview_text(dchr.getReview_text());
                
                characteristicReview.setUser(user);    
                BOLO.ProductCharacteristic productCharacteristic = new ProductCharacteristic();
                productCharacteristic.setDescription(dchr.getCharacteristic().getDescription());
                productCharacteristic.setId(dchr.getCharacteristic().getId());
                productCharacteristic.setTitle(dchr.getCharacteristic().getName());
                        
                characteristicReview.setCharacteristic(productCharacteristic);
                review.getCharacteristicReviews().add(characteristicReview);
            }
            
            DEL.Product review_product = dr.getProduct();
            
            
            review.setReviewer(user);
            
            BOLO.Product bolo_product = new BOLO.Product();
            bolo_product.setCharacteristics(new ArrayList<ProductCharacteristic>());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
            for( DEL.Characteristic dch : review_product.getCharacteristics())
            {
                BOLO.ProductCharacteristic pch = new ProductCharacteristic();
                pch.setDescription(dch.getDescription());
                pch.setId(dch.getId());
                pch.setTitle(dch.getName());
                bolo_product.getCharacteristics().add(pch);
            }            
            bolo_product.setIdentifier(review_product.getId().toString() );
            bolo_product.setTitle(review_product.getTitle());
            bolo_product.setWhatIsIt(review_product.getWhatIsIt());
            bolo_product.setWhoMadeIt(review_product.getWhoMadeIt());
            
            review.setProduct(bolo_product);
            bolo_reviews.add(review);
        }
        return bolo_reviews;
    }

    private List<BOLO.Review> Convert(List<DEL.Review> del_reviews) throws Exception
    {
        List<BOLO.Review> reviews = new ArrayList<BOLO.Review>();
        for( DEL.Review del_review : del_reviews)
        {
            BOLO.Review review = new BOLO.Review();
            review.setHighlights(del_review.getLowlights());
            review.setLowlights(del_review.getLowlights());
            review.setText(del_review.getText());
            
            BOLO.User user = new BOLO.User();
            user.setPassword(del_review.getReviewer().getPassword());
            user.setUsername(del_review.getReviewer().getUsername());
                    
            
            for( DEL.CharacteristicReview dCharacteristicReview : del_review.getCharacteristicReviews())
            {
                BOLO.CharacteristicReview bCharacteristicReview = new CharacteristicReview();
                
                BOLO.ProductCharacteristic productCharacteristic = new ProductCharacteristic();
                productCharacteristic.setDescription(dCharacteristicReview.getCharacteristic().getDescription());
                productCharacteristic.setId(dCharacteristicReview.getCharacteristic().getId());
                productCharacteristic.setTitle(dCharacteristicReview.getCharacteristic().getName());
                
                bCharacteristicReview.setCharacteristic(productCharacteristic);
                bCharacteristicReview.setId(dCharacteristicReview.getId());
                bCharacteristicReview.setReview_text(dCharacteristicReview.getReview_text());
                bCharacteristicReview.setUser(user);
                review.getCharacteristicReviews().add(bCharacteristicReview);
            }            
            
            BOLO.Product product = new BOLO.Product();
            for( DEL.Characteristic dch : del_review.getProduct().getCharacteristics() )
            {
                BOLO.ProductCharacteristic bProductCharacteristic = new ProductCharacteristic();
                bProductCharacteristic.setDescription(dch.getDescription());
                bProductCharacteristic.setId(dch.getId());
                bProductCharacteristic.setTitle(dch.getName());
                product.getCharacteristics().add(bProductCharacteristic);
                        
            }
            
            product.setIdentifier(del_review.getProduct().getId().toString());
            product.setTitle(del_review.getProduct().getTitle());
            product.setWhatIsIt(del_review.getProduct().getWhatIsIt());
            product.setWhoMadeIt(del_review.getProduct().getWhoMadeIt());
            review.setProduct(null);
            review.setReviewer(user);
            
            
            reviews.add(review);
        }
        return reviews;
    }
    
    public DEL.Review ConvertToDel(BOLO.Review review) throws Exception 
    {
        DEL.Review del_review = new DEL.Review();
        
        //yuk
            del_review.setCharacteristicReviews( new SwapableCollection<DEL.CharacteristicReview>()
                                               .ConvertListToSet(
                                                       characteristicBOL.ConvertToDels
                                                        (
                                                               review.getCharacteristicReviews())
                                                        ) 
                                        );
            
        return del_review;                
    }
    
}
