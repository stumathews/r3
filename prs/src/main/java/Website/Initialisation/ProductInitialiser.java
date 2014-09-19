package Website.Initialisation;

import BSL.Interfaces.IProductAdmin;

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
            
            productAdmin.addProduct(token, product);
            
        }
    }
}
