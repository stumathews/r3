/* This is a webflow controller which manages the user review creation process

*/
package Webflow.Controllers;

import BOL.Interfaces.IUserSessionManager;
import BOLO.CharacteristicReview;
import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IReviewAdmin;
import BSL.Interfaces.IUserAdmin;
import Website.Controllers.Common;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;



@Service("webflowReviewController") 
public class WebflowReviewController  implements Action {
    
    @Autowired
    private IProductAdmin productAdmin;
    @Autowired
    private IReviewAdmin reviewAdmin;
    @Autowired 
    private ICharacteristicAdmin characteristicAdmin;
    @Autowired 
    private IUserAdmin userAdmin;
    @Autowired
    private BOL.Interfaces.IUserSessionManager userSessionManager; // each logged in user as their own userSessionManager...

        
    /**
     * Sets up the session for this flow. 
     * @param req the request parameters
     * @throws Exception of there is a problem
     */
    public void prepareReviewFlow(RequestContext req) throws Exception 
    {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String username = auth.getName();
        
        // lets do some initialisation of the flowscope variables, not sure if needed but ok to do so for now
        BOLO.Review review = new BOLO.Review();
        review.setCharacteristicReviews(new ArrayList<CharacteristicReview>()); // to prevent nulls
        
        BOLO.User reviewer = userAdmin.getUser(userSessionManager.getTokenString(),username); 
        BOLO.ProductCharacteristic selectedCharacteristic = new BOLO.ProductCharacteristic();
        BOLO.Product product = new BOLO.Product();
        BOLO.Wrappers.CharacteristicList characteristics = new BOLO.Wrappers.CharacteristicList();
        BOLO.CharacteristicReview currentCharacteristicReview = new CharacteristicReview();
        
        review.setReviewer(reviewer);
                
        req.getFlowScope().put("review", review);
        req.getFlowScope().put("product", product);
        req.getFlowScope().put("selectedCharacteristic", selectedCharacteristic);
        req.getFlowScope().put("reviewer", reviewer);
        req.getFlowScope().put("currentCharacteristicReview", currentCharacteristicReview);
        
        
                                
        // Lets pull out the product Id of the product that this reviw will be based on
        String productIdentifier = req.getRequestParameters().get("productId");        
        // get the stored product object which is in flow scope session
        product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class);
        // Set it to the incoming product from the request paramemters
        product.setIdentifier(productIdentifier);        
        // Put it back into the session
        req.getFlowScope().put("product",product);     
                             
    }
    
    /**
     * Saves the provided review.
     * @param req the request parameters
     * @param review the review to be saved
     * @param theProduct the product that this review is for
     * @param selectedCharacteristic the selected characteristic
     * @throws Exception if any problems arise.
     */
    public void saveCharacteristicReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct, BOLO.ProductCharacteristic selectedCharacteristic, BOLO.CharacteristicReview selectedCharacteristicReview) throws Exception
    {      
        // lets add this characteristic to the review of this product   
        selectedCharacteristicReview.setCharacteristic(selectedCharacteristic);
                
        review.getCharacteristicReviews().add(selectedCharacteristicReview);        
        req.getFlowScope().put("review", review);        
    }
    
    public Event saveReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct, BOLO.User reviewer) throws Exception    
    {      
        String token = userSessionManager.getTokenString();
        review.setReviewer(reviewer);
        review.setProduct(theProduct);
        
        reviewAdmin.SaveReview(token, review);    
        // Get all the reviews now(including the one just saved)
        List<BOLO.Review> reviews = reviewAdmin.getAllReviews(token);
        // Add them all to the session for usage in next stages of flow
        req.getFlowScope().put("reviews", reviews);  
        return new Event(this,"ok");
    }
    
    
    /**
     * Gets the list of characteristics for this product. 
     * It stores them in the flow scope variable, characteristics
     * @param req the request parameters
     * @param theProduct the product for which the characteristics need to be fetched
     * @returns nothing other than putting found characteristics into the sessions
     */
    public void getProductCharacteristics(RequestContext req, BOLO.Product theProduct) throws Exception
    {
        List<BOLO.ProductCharacteristic> db_prod_chars   = new ArrayList<BOLO.ProductCharacteristic>();  
        // Get the session storage variable for characteristics...
        // This is a wrapper for a collection of items
        BOLO.Wrappers.CharacteristicList characteristics =  (BOLO.Wrappers.CharacteristicList) req.getFlowScope().get("characteristics",BOLO.Wrappers.CharacteristicList.class);
        
        // This is where the actual items will go, 
        ArrayList<BOLO.ProductCharacteristic> actual_chars = new ArrayList<BOLO.ProductCharacteristic>();
        // Go and get the actual characteristics for this product form the database
        db_prod_chars = characteristicAdmin.getProductCharacteristics(userSessionManager.getTokenString(), theProduct.getIdentifier());
        
        // put the actual items into actual_chars
        for( BOLO.ProductCharacteristic prod_char : db_prod_chars )
        {
            actual_chars.add(prod_char);
        }
        
        // Add them to the flow scope
        
        characteristics.setItems(actual_chars);       
        theProduct.setCharacteristics(db_prod_chars);
        
        req.getFlowScope().put("characteristics",characteristics);
        req.getFlowScope().put("product", theProduct);
    }
            
    /**
     * Determines if a product in the flow is is set or not
     * @param req the request parameters
     * @return an Event "yes" if a product is selected, otherwise "no"
     * @throws Exception if problems arise
     */
    public Event isProductSelected(RequestContext req) throws Exception
    {
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class); 
        if( product.getIdentifier() == null)
            return new Event(this,"no");
        BOLO.Product result = productAdmin.getProductByID(userSessionManager.getTokenString(), product.getIdentifier() );
        if( result != null)
        {
            req.getFlowScope().put("product", result);   
            // we might as well get the product characteristics now and put them into the flow also
            getProductCharacteristics(req, product);
            return new Event(this, "yes");
        }else
        {
            return new Event(this,"no");
        }
    }
    
    /**
     * Gets all the products form the database, and puts them into 
     * the corresponding flow variable.
     * @param req the request parameters
     * @throws Exception if there is a problem
     */
    public void putAllProductsIntoFlow(RequestContext req) throws Exception
    {        
        ArrayList<BOLO.Product> result = productAdmin.getAllProducts(userSessionManager.getTokenString());
        req.getFlowScope().put("products", result);
    }
       
    /**
     * Executed by default if no method is specified the type WebflowReviewController
     * @param req the request parameters
     * @return "ok"
     * @throws Exception if a problem arises
     */
    @Override
    public Event execute(RequestContext req) throws Exception {
          //String name = req.getRequestParameters().get("inputName");

          return new Event(this, "ok");
    }
    
    public Event validateCharactersticSelection(RequestContext req, BOLO.ProductCharacteristic selectedCharacteristic)
    {  
        if( !selectedCharacteristic.getTitle().isEmpty())
        {           
            
            BOLO.Wrappers.CharacteristicList chars = (BOLO.Wrappers.CharacteristicList) req.getFlowScope().get("characteristics");            
            for( BOLO.ProductCharacteristic ch : chars.getItems())  
            {
                if( ch.getTitle().equalsIgnoreCase(selectedCharacteristic.getTitle()))
                {
                    // lets get the rest of the object, the part which couldnt be set in the form
                    selectedCharacteristic.setDescription(ch.getDescription());                     
                    selectedCharacteristic.setId(ch.getId());
                    req.getFlowScope().put("selectedCharacteristic", selectedCharacteristic);
                    break;
                } 
            }
            return new Event(this,"ok");
        }
        else
            return new Event(this,"fail");
    }

}



