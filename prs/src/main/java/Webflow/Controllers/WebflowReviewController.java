// This is a webflow controller.
package Webflow.Controllers;

import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IReviewAdmin;
import DEL.Product;
import Website.Controllers.Common;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        
    public void prepareReviewFlow(RequestContext req) throws Exception 
    {           
        // Lest authenticate
        
        // Lets pull out the product Id of the prodeuct that this reviw will be based on
        String productIdentifier = req.getRequestParameters().get("productId");
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class);
        product.setIdentifier(productIdentifier);
        req.getFlowScope().put("product",product);        
                             
    }
    
    public void saveReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct) throws Exception
    {        
        String token = Common.GetGenAdminAuthToken();
        reviewAdmin.SaveReview(token, review);        
        List<BOLO.Review> reviews = reviewAdmin.getAllReviews(token);
        req.getFlowScope().put("reviews", reviews);     
        
    }
       
    public Event isProductSelected(RequestContext req) throws Exception
    {
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class); 
        if( product.getIdentifier() == null)
            return new Event(this,"no");
        BOLO.Product result = productAdmin.getProductByID(Common.GetGenAdminAuthToken(), product.getIdentifier() );
        if( result != null)
        {
            req.getFlowScope().put("product", result);          
            return new Event(this, "yes");
        }else
        {
            return new Event(this,"no");
        }
    }
    
    public void putAllProductsIntoFlow(RequestContext req) throws Exception
    {        
        ArrayList<BOLO.Product> result = productAdmin.getAllProducts(Common.GetGenAdminAuthToken());
        req.getFlashScope().put("products", result);
    }
    
    
    
    @Override
    public Event execute(RequestContext req) throws Exception {
          //String name = req.getRequestParameters().get("inputName");

          return new Event(this, "ok");
    }
}



