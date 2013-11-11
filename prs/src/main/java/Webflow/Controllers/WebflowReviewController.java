
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
        
    public ArrayList<BOLO.Product> createReview(RequestContext req) throws Exception 
    {           
        ArrayList<BOLO.Product> result = productAdmin.getAllProducts(Common.GetGenAdminAuthToken());
        
        return result;                        
    }
    
    public void saveReview(RequestContext req, BOLO.Review review, BOLO.Product theProduct) throws Exception
    {        
        String token = Common.GetGenAdminAuthToken();
        reviewAdmin.SaveReview(token, review);        
        List<BOLO.Review> reviews = reviewAdmin.getAllReviews(token);
        req.getFlowScope().put("reviews", reviews);
        req.getFlowScope().put("product", theProduct);
    }
    
    @Override
    public Event execute(RequestContext req) throws Exception {
          //String name = req.getRequestParameters().get("inputName");

          ArrayList<BOLO.Product> result = productAdmin.getAllProducts(Common.GetGenAdminAuthToken());
          req.getFlowScope().put("products",result);          


          return new Event(this, "ok");
    }
}



