
package Website.Controllers;

import BSL.Interfaces.IProductAdmin;
import DEL.Product;
import Website.Controllers.Common;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

 import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Controller("getProductsController")
public class WebflowController  implements Action {
    
    @Autowired
    private IProductAdmin productAdmin;
    
    @Override
      public Event execute(RequestContext req) throws Exception {
            //String name = req.getRequestParameters().get("inputName");
        
            ArrayList<Product> result = productAdmin.getAllProducts(Common.GetGenAdminAuthToken());
            req.getFlowScope().put("products",result);
            
            
            return new Event(this, "ok");
      }
    
    public Event test(RequestContext req) throws Exception {
            //String name = req.getRequestParameters().get("inputName");
            ArrayList<Product> result = productAdmin.getAllProducts(Common.GetGenAdminAuthToken());
            req.getFlowScope().put("products",result);
            
            
            
            return new Event(this, "ok");
      }
}



