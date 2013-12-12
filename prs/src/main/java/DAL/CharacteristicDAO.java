
package DAL;


import BOLO.ProductCharacteristic;
import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import DAL.Interfaces.IUserDAO;
import DEL.Characteristic;
import DEL.Product;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    
    
    public void addProductCharacteristic(Product product, String title, String description, String review) throws Exception 
    {
        try {
            Session session = sessionFactory.getCurrentSession();
            
            DEL.Characteristic new_char = new DEL.Characteristic();
            new_char.setDescription(description);
            new_char.setName(title);
            new_char.setUseful_value(0);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if( auth != null)
            {
                String authenticated_user = auth.getName();
                DEL.User user = userDAO.getUser(authenticated_user);
                new_char.setCreator(user);
            }
            else
            {
                new_char.setCreator(null);
            }
            new_char.setProduct(product);  
            new_char.setReview(review);
            
            session.save(new_char);
        } catch (Exception e) {
            throw new Exception("Unable to add product caracteristic",e);
        }
    }

    public List<Characteristic> getProductCharacteristics(String productID) throws Exception 
    {
        List<Characteristic> results = new ArrayList<Characteristic>();
        
        try 
        {
            Session session = sessionFactory.getCurrentSession();           
            Criteria cr = session.createCriteria(Characteristic.class)
                   .add(Restrictions.eq("product.id", Long.parseLong(productID) ));                  
            results = cr.list();
            
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to getProductCharacteristics",e);
        }
        return results;
        
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
            prodchar.setReview(ch.getReview());
            prodchar.setTitle(ch.getName());
            DEL.Product delprod = ch.getProduct();
            BOLO.Product bolprod = productDAO.Convert(delprod);            
            prodchar.setProduct(bolprod);          
            
            ret.add(prodchar);
        }
        return ret;
        
    }

    public BOLO.ProductCharacteristic Convert(DEL.Characteristic del_prodchar) throws Exception 
    {
        BOLO.ProductCharacteristic bolo_prodchar = new BOLO.ProductCharacteristic();
        bolo_prodchar.setDescription(del_prodchar.getDescription());
        bolo_prodchar.setProduct(productDAO.Convert(del_prodchar.getProduct()));
        bolo_prodchar.setReview(del_prodchar.getReview());
        bolo_prodchar.setTitle(del_prodchar.getName());
        
        
        return bolo_prodchar;
    }
}
