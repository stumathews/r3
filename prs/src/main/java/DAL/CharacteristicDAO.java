
package DAL;


import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import DAL.Interfaces.IUserDAO;
import DEL.Characteristic;
import DEL.Product;
import Utility.SwapableCollection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Characteristics Data access object. Managers interaction with
 * characteristics in the database
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class CharacteristicDAO implements ICharacteristicsDAO
{
    private SessionFactory sessionFactory;
    private IProductDAO productDAO;

    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    private IUserDAO userDAO;
    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void addCharacteristic(String name, String description) 
    {
        DEL.Characteristic new_characteristic = new DEL.Characteristic();
        new_characteristic.setDescription(description);
        new_characteristic.setName(name);
        
        Session session = sessionFactory.getCurrentSession();
        session.save(new_characteristic);
    }
    
    
    public void addProductCharacteristic(Product product, String title, String description) throws Exception 
    {
        try 
        {
            Session session = sessionFactory.getCurrentSession();
            
            DEL.Characteristic new_char = new DEL.Characteristic();
            new_char.setDescription(description);
            new_char.setName(title);
            new_char.setUseful_value(0);
            new_char.setProduct(product);
            
            product = (DEL.Product) session.get(DEL.Product.class, product.getId());
            
            product.getCharacteristics().add(new_char);
            
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to add product caracteristic",e);
        }
    }

    public List<DEL.Characteristic> getProductCharacteristics(String productID) throws Exception 
    {
        List<DEL.Characteristic> results = new ArrayList<DEL.Characteristic>();
        
        try 
        {
            Session session = sessionFactory.getCurrentSession();           
            DEL.Product product = (DEL.Product) session.load(DEL.Product.class, Long.parseLong(productID));
            
            SwapableCollection collection = new SwapableCollection<DEL.Characteristic>();
            return collection.ConvertSetToList(product.getCharacteristics());
            
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to getProductCharacteristics",e);
        }
        
        
    }
    
    
    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception
    {                 
        Session session = sessionFactory.getCurrentSession();
        ArrayList<DEL.Characteristic> chars = new  ArrayList<DEL.Characteristic>();
        ArrayList<BOLO.ProductCharacteristic> ret = new ArrayList<BOLO.ProductCharacteristic>();
        
        chars = (ArrayList<DEL.Characteristic>) session.createQuery("from Characteristic").list();
        for( DEL.Characteristic ch : chars)
        {
            BOLO.ProductCharacteristic prodchar = new BOLO.ProductCharacteristic();
            
            prodchar.setDescription(ch.getDescription());
            prodchar.setTitle(ch.getName());           
           
            ret.add(prodchar);
        }
        return ret;
        
    }

    public BOLO.ProductCharacteristic Convert(DEL.Characteristic del_prodchar, DEL.Product product) throws Exception 
    {
        BOLO.ProductCharacteristic bolo_prodchar = new BOLO.ProductCharacteristic();
        bolo_prodchar.setDescription(del_prodchar.getDescription()); 
        bolo_prodchar.setTitle(del_prodchar.getName());
        
        
        return bolo_prodchar;
    }

    public Characteristic getCharacteristic(Long characteristic_id) throws Exception 
    {
       Session session = sessionFactory.getCurrentSession();
       return (DEL.Characteristic) session.get(DEL.Characteristic.class, characteristic_id);
    }
}
