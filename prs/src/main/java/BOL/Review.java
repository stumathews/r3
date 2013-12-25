/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IReview;
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
            
            for( DEL.Characteristic dch : dr.getCharacteristics())
            {
                BOLO.ProductCharacteristic pch = new ProductCharacteristic();
                pch.setDescription(dch.getDescription());
                pch.setId(dch.getId());                
                pch.setTitle(dch.getName());
                
                review.getCharacteristics().add(pch);
            }
            
            DEL.Product review_product = dr.getProduct();
            
            review.setReviewer(null);
            BOLO.Product bolo_product = new BOLO.Product();
            bolo_product.setCharacteristics(review.getCharacteristics());
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
            BOLO.Review review = ConvertSingle(del_review);
            reviews.add(review);
        }
        return reviews;
    }
    
    private BOLO.Review ConvertSingle( DEL.Review del_review) throws Exception
    {
        
        BOLO.Review review = new BOLO.Review();
        List<DEL.Characteristic> listOfCharacteristics = new ArrayList<DEL.Characteristic>();
        listOfCharacteristics.addAll(del_review.getCharacteristics());
        
        //review.setCharacteristics( characteristicBOL.Convert( listOfCharacteristics ) );
        return review;
    }

    public DEL.Review ConvertToDel(BOLO.Review review) throws Exception 
    {
        DEL.Review del_review = new DEL.Review();
        
        //yuk
            del_review.setCharacteristics( new SwapableCollection<DEL.Characteristic>()
                                               .ConvertListToSet(
                                                       characteristicBOL.ConvertToDels
                                                        (
                                                               review.getCharacteristics())
                                                        ) 
                                        );
            
        return del_review;                
    }
    
}
