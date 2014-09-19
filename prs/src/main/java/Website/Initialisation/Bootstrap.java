package Website.Initialisation;

import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IUserAdmin;
import Website.Controllers.Common;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap
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
    
    @Autowired
    private WSL.Interfaces.IAdminFacade adminFacade;
    

    @PostConstruct
    public void onAppStartup()
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
