/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Website.Initialisation;


import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IUserAdmin;
import Website.Controllers.Common;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> 
{    
     
    @Autowired
    private ILoginAdmin loginAdmin;
    
    @Autowired
    private IProductAdmin productAdmin;
    
    @Autowired
    private ICharacteristicAdmin characteristicAdmin; 
    
    @Autowired
    private BSL.Interfaces.IRecommendationAdmin recommendationAdmin;
    
    @Autowired
    private BSL.Interfaces.IReviewAdmin reviewAdmin;
    
    @Autowired
    private IUserAdmin userAdmin;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) 
    {
        try  
        { 

           // This is being called twice for some reason...
            // do some boot straping like injecting sample products characteristics etc.
            String token = Common.GetGenAdminAuthToken(); 
            Website.Initialisation.ProductInitialiser prodiniter = new Website.Initialisation.ProductInitialiser(productAdmin);
            prodiniter.CreateProducts(token, 5);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
