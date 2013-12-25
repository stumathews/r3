/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Website.Initialisation;

import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IUserAdmin;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lenovo x220
 */

public class ProductInitialiser 
{
    private IProductAdmin productAdmin;
    
    public ProductInitialiser(IProductAdmin productAdmin){
        this.productAdmin = productAdmin;
    }    
          
    public void CreateProducts(String token, int count) throws Exception
    {
        for( int i = 0; i < count; i++) 
        {
            BOLO.Product product = new BOLO.Product();
                product.setTitle("Product Title #" + i);
                product.setWhatIsIt("What is it place holder #"+i);
                product.setWhoMadeIt("Who made it place holder #"+i);     
            
            productAdmin.addProduct(token, product);
            
        }
    }
}
