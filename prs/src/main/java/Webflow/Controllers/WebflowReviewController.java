/* This is a webflow controller which manages the user review creation process
*/
package Webflow.Controllers;

import BOLO.CharacteristicReview;
import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IReviewAdmin;
import BSL.Interfaces.IUserAdmin;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;



@Service("webflowReviewController") 
public class WebflowReviewController extends FormAction 
{
    
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

    
    public void prepareReviewFlow(RequestContext req) throws Exception 
    {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String username = auth.getName();
        
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        
        // lets do some initialisation of the flowscope variables, not sure if needed but ok to do so for now
        BOLO.Review review = new BOLO.Review();
        review.setCharacteristicReviews(new ArrayList<CharacteristicReview>()); // to prevent nulls
        
        BOLO.User reviewer = userAdmin.getUser(token,username); 
        
        BOLO.ProductCharacteristic selectedCharacteristic =        new BOLO.ProductCharacteristic();
        BOLO.Product product =                                     new BOLO.Product();
        BOLO.Wrappers.CharacteristicList characteristics =         new BOLO.Wrappers.CharacteristicList();
        BOLO.CharacteristicReview currentCharacteristicReview =    new CharacteristicReview();
        BOLO.Wrappers.CharacteristicList selectedCharacteristics = new BOLO.Wrappers.CharacteristicList();
        
        review.setReviewer(reviewer);
                
        req.getFlowScope().put("review", review);
        req.getFlowScope().put("product", product);
        req.getFlowScope().put("selectedCharacteristic", selectedCharacteristic);
        req.getFlowScope().put("reviewer", reviewer);
        req.getFlowScope().put("currentCharacteristicReview", currentCharacteristicReview);
        req.getFlowScope().put("selectedCharacteristics", selectedCharacteristics);
        
        // Lets pull out the product Id of the product that this reviw will be based on
        // injected into request via url /add-review?productId=${product.getIdentifier()}
        String productIdentifier = req.getRequestParameters().get("productId");        
        // get the stored product object which is in flow scope session
        product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class);
        // Set it to the incoming product from the request paramemters
        product.setIdentifier(productIdentifier);        
        // Put it back into the session
        req.getFlowScope().put("product",product);     
                             
    }
    
    public void saveCharacteristicReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct, BOLO.ProductCharacteristic selectedCharacteristic, BOLO.CharacteristicReview selectedCharacteristicReview) throws Exception
    {      
        // lets add this characteristic to the review of this product   
        selectedCharacteristicReview.setCharacteristic(selectedCharacteristic);  
        selectedCharacteristicReview.setUser(review.getReviewer());
        review.getCharacteristicReviews().add(selectedCharacteristicReview);        
        req.getFlowScope().put("review", review);        
    }
    
    public Event saveReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct, BOLO.User reviewer) throws Exception    
    {      
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        review.setReviewer(reviewer);
        review.setProduct(theProduct);
        
        reviewAdmin.SaveReview(token, review);    
        // Get all the reviews now(including the one just saved)
        List<BOLO.Review> reviews = reviewAdmin.getAllReviews(token);
        // Add them all to the session for usage in next stages of flow
        req.getFlowScope().put("reviews", reviews);  
        return new Event(this,"ok");
    }
    
    
    
    public void getProductCharacteristics(RequestContext req, BOLO.Product theProduct) throws Exception
    {
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        List<BOLO.ProductCharacteristic> db_prod_chars   = new ArrayList<BOLO.ProductCharacteristic>();  
        // Get the session storage variable for characteristics...
        // This is a wrapper for a collection of items
        BOLO.Wrappers.CharacteristicList characteristics =  (BOLO.Wrappers.CharacteristicList) req.getFlowScope().get("characteristics",BOLO.Wrappers.CharacteristicList.class);
        
        // This is where the actual items will go, 
        ArrayList<BOLO.ProductCharacteristic> actual_chars = new ArrayList<BOLO.ProductCharacteristic>();
        // Go and get the actual characteristics for this product form the database
        db_prod_chars = characteristicAdmin.getProductCharacteristics(token, theProduct.getIdentifier());
        List<DEL.Characteristic> db_prod_chars2   = new ArrayList<DEL.Characteristic>();
        db_prod_chars2 = characteristicAdmin.getProductDELCharacteristics(token, theProduct.getIdentifier());
        // put the actual items into actual_chars
        for( BOLO.ProductCharacteristic prod_char : db_prod_chars )
        {
            actual_chars.add(prod_char);
        }
        
        // Add them to the flow scope
        
        characteristics.setItems(db_prod_chars2);       
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
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class); 
        if( product.getIdentifier() == null)
            return new Event(this,"no");
        
        
        BOLO.Product result = productAdmin.getProductByID(token, product.getIdentifier() );
        if( result != null)
        {
            req.getFlowScope().put("product", result);   
            // we might as well get the product characteristics now and put them into the flow also
            getProductCharacteristics(req, result);
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
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        ArrayList<BOLO.Product> result = productAdmin.getAllProducts(token);
        req.getFlowScope().put("products", result);
    }
 
    public Event validateCharactersticSelection(RequestContext req, BOLO.Wrappers.CharacteristicList selectedCharacteristics)
    { 
      if( selectedCharacteristics == null || selectedCharacteristics.getItems() == null)
        return new Event(this, "fail");
     
      for( DEL.Characteristic characteristic : selectedCharacteristics.getItems())
      {
        BOLO.ProductCharacteristic select = (BOLO.ProductCharacteristic) req.getFlowScope().get("selectedCharacteristic");
        select.setDescription(characteristic.getDescription());
        select.setId(characteristic.getId());
        select.setTitle(characteristic.getName());
        req.getFlowScope().put("selectedCharacteristic", select);
        return new Event(this,"ok");
      }
        return new Event(this,"fail");
    }
    
 
}

 



