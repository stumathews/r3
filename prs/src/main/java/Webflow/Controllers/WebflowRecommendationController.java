// This is a webflow controller.
package Webflow.Controllers;

import BOL.Interfaces.IUserSessionManager;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IRecommendationAdmin;
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


@Service("webflowRecommendationController") 
public class WebflowRecommendationController  implements Action {
    
    @Autowired
    private IProductAdmin productAdmin;
    @Autowired
    private IRecommendationAdmin recommendationAdmin;
    private BOL.Interfaces.IUserSessionManager userSessionManager; // each logged in user as their own userSessionManager...

    @Autowired
    public void setUserSessionManager(IUserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }
        
    /**
     * Sets up and prepares the recommendation webflow environment
     * @param req RequestContext
     * @throws Exception 
     */
    public void prepareRecommendationFlow(RequestContext req) throws Exception 
    {           
        String productIdentifier = req.getRequestParameters().get("productId");
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class);
        product.setIdentifier(productIdentifier);
        req.getFlowScope().put("product",product);         
                             
    }   
       
    public Event isProductSelected(RequestContext req) throws Exception
    {
        BOLO.Product product = (BOLO.Product) req.getFlowScope().get("product",BOLO.Product.class); 
        if( product.getIdentifier() == null)
            return new Event(this,"no");
        
        BOLO.Product result = productAdmin.getProductByID(userSessionManager.getTokenString(), product.getIdentifier() );
        
        if( result != null)
        {
            req.getFlowScope().put("product", result);          
            return new Event(this, "yes");
        }
        else
        {
            return new Event(this,"no");
        }
    }
    
    public void saveRecommendation(RequestContext req, BOLO.Recommendation recommendation, BOLO.Product product) throws Exception
    {        
        recommendationAdmin.createRecommendation(userSessionManager.getTokenString(), recommendation);        
        req.getFlowScope().put("recommendation", recommendation);  
    }
    
    @Override
    public Event execute(RequestContext req) throws Exception {
          //String name = req.getRequestParameters().get("inputName");

          prepareRecommendationFlow(req);
          return new Event(null,"ok");
    }
}



