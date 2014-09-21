package Website.Initialisation;

import BOLO.ProductCharacteristic;
import BSL.Interfaces.IProductAdmin;
import java.util.AbstractList;
import java.util.ArrayList;

public class ProductInitialiser 
{
    private final IProductAdmin productAdmin;
    
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
                BOLO.ProductCharacteristic productCharacteristic = new ProductCharacteristic();
                productCharacteristic.setTitle("blah" + i);
                ArrayList<BOLO.ProductCharacteristic> chars = new ArrayList<ProductCharacteristic>();
                chars.add(productCharacteristic);
                product.setCharacteristics(chars);
            
            productAdmin.addProduct(token, product);
            
        }
    }
}
