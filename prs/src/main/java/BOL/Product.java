/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import BOL.Interfaces.ICharacteristic;
import DAL.Interfaces.IProductDAO;
import BOL.Interfaces.IProduct;
import Website.Controllers.Common;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product business logic
 * @author Lenovo x220
 */
public class Product implements IProduct
{
    
    private DAL.Interfaces.IProductDAO productDAO;
    private ICharacteristic characteristicBOL;    

    @Autowired
    public void setCharacteristicBOL(Characteristic characteristicBOL) {
        this.characteristicBOL = characteristicBOL;
    }
            

    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
        
    @Transactional
    public BOLO.Product MakeProductFormFromId(String productID) throws Exception 
    {
        DEL.Product prod = productDAO.getProductByID( productID);
        /* Construct the form binding object aka command object: */
        BOLO.Product newProduct = new BOLO.Product();
        newProduct = productDAO.Convert(prod);
        return newProduct;
        
    }

    public BOLO.Product ConvertSingle(DEL.Product del_product) throws Exception 
    {
        BOLO.Product product = new BOLO.Product();
        // Convert set to list
        List<DEL.Characteristic> del_characteristics = new ArrayList<DEL.Characteristic>();
        del_characteristics.addAll( del_product.getCharacteristics());
        
        product.setCharacteristics( characteristicBOL.Convert(del_characteristics)  );
        product.setTitle(del_product.getTitle());
        product.setWhatIsIt(del_product.getWhatIsIt());
        product.setWhoMadeIt(del_product.getWhoMadeIt());
        
        return product;
    }

    public DEL.Product ConvertToDel(BOLO.Product product) throws Exception
    {
        return productDAO.getProductByID(product.getIdentifier());        
    }

    
    
    
}
