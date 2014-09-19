package Website.Controllers;
 
import BOL.Interfaces.IProduct;
import BOL.Interfaces.IUserSessionManager;
import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IReviewAdmin;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that manages /products routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */ 
@Controller
@RequestMapping( value="/Product" )
public class ProductController 
{ 
	       
    private static final boolean DEBUG = false;
    private BSL.Interfaces.IProductAdmin productAdmin;    
    private BSL.Interfaces.ILoginAdmin loginAdmin;
    private BSL.Interfaces.ICharacteristicAdmin characteristicAdmin;
    private BSL.Interfaces.IReviewAdmin reviewAdmin;    
    private BOL.Interfaces.IUserSessionManager userSessionManager; // each logged in user as their own userSessionManager...

    @Autowired
    public void setUserSessionManager(IUserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }
    
    @Autowired
    public void setReviewAdmin(IReviewAdmin reviewAdmin) {
        this.reviewAdmin = reviewAdmin;
    }
    private BOL.Interfaces.IProduct productLogic;

    @Autowired
    public void setProductLogic(IProduct productLogic) {
        this.productLogic = productLogic;
    }    

    @Autowired
    public void setCharacteristicAdmin(ICharacteristicAdmin characteristicAdmin) {
        this.characteristicAdmin = characteristicAdmin;
    }    
    @Autowired
    public void setLoginAdmin(ILoginAdmin loginAdmin) {
        this.loginAdmin = loginAdmin;
    }
    @Autowired
    public void setProductAdmin(IProductAdmin productAdmin) {
        this.productAdmin = productAdmin;
    }
   
    /**
    * Gets all products in DB and adds bean to view which, show all the products.
    * @param model
    * @return view
    * @throws Exception 
    */
    @RequestMapping(value ="/ShowProductList",method = RequestMethod.GET)
    public String showProductsView(ModelMap model) throws Exception
    {      
                
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        
        // Get all products in the database
        ArrayList< BOLO.Product > products = productAdmin.getAllProducts( token );
        
        // add products to model view        
        model.addAttribute("products", products);
        
        return "Products/ShowProducts"; 
    }
    
    /***
     * Return JSON representation of a DEL.Product
     * @param productID
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/Show/{productID}/json", method = RequestMethod.GET)
    @ResponseBody
    public BOLO.Product getProductRaw( @PathVariable("productID") String productID, 
                               ModelMap model) throws Exception
    {
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        return productAdmin.getProductByID(token, productID);
    }
    
      
    /**
    * Show the add product view/form 
    * @param form the modelAttribute to populate the form
    * @param model model for the view
    * @return 
    */
    @RequestMapping( value="/ShowAdd", method = RequestMethod.GET)
    public String addProductView( BOLO.Product form, ModelMap model )
    {
        model.addAttribute("product", form);
        

        return "Products/AddProduct";
    }
    
    /**
    * Deletes a product 
    * @param productID the id of the product to delete
    * @param model view model
    * @return view to list all products after deletion
    * @throws Exception 
    */
    @RequestMapping( value="/Delete/{productID}", method = RequestMethod.GET)
    public String deleteProductByIDView( @PathVariable("productID") String productID, 
                                         ModelMap model) throws Exception
    {
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        
        productAdmin.deleteProductByID( token, productID );

        return "redirect:/Product/ShowProductList";
    }

    /**
    * Edit a product by given product id
    * @param productID id of product to edit
    * @param model views model
    * @return view to list all products
    * @throws Exception 
    */
    @RequestMapping( value="/ShowEdit/{productID}", method = RequestMethod.GET)
    public String editProductByIDView( @PathVariable("productID") String productID, ModelMap model) throws Exception
    {    
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
      
      BOLO.Product newProduct = productAdmin.MakeProductFormFromId(token, productID);

      return addProductView(newProduct, model);
    }
    
    /***
     * Show a specific product page
     * @param productID
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping( value="/Show/{productID}", method = RequestMethod.GET)
    public String viewProduct( @PathVariable("productID") String productID, 
                               ModelMap model) throws Exception
    {    
        //FIXME: We should remove reliance on the DEL here and replace it with BOLO objects
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        BOLO.Product prod = productAdmin.getProductByID(token, productID);        
        List<BOLO.ProductCharacteristic> productCharacteristics = characteristicAdmin.getProductCharacteristics(token, productID);
        List<BOLO.Review> reviews = reviewAdmin.getProductReviews( token, productID );        
        model.addAttribute("productCharacteristics", productCharacteristics);
        model.addAttribute("product",prod);
        model.addAttribute("reviews", reviews);
        return "Products/ViewProduct";
    }
    
    /**
    * Add product to db based on form values
    * @param product modelAttribute used to bind the form
    * @param model model for the view
    * @param result binding result
    * @return view of all products
    * @throws Exception 
    */       
    @RequestMapping(value ="/Create", method = RequestMethod.POST)
    public String addProductToDB(ModelMap model, @Valid @ModelAttribute("product") BOLO.Product product, BindingResult result) throws Exception
    {	
        if( result.hasErrors())
          return "Products/AddProduct";
        
        String token = userSessionManager.GetCurrentUserSession().getSessionToken().getTokenString();
        productAdmin.addProduct( token , product);	

        return "redirect:/Product/ShowProductList";
    }
 
    /**
     * Shows the form that allows the user to add a product
     * @param newChar
     * @param productID
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping( value="/add/characteristic/{productID}", method = RequestMethod.GET)
    public String addProductCharacteristicView( @ModelAttribute("FormProductCharacteristic") BOLO.ProductCharacteristic newChar,
                                                @PathVariable("productID") String productID,
                                                ModelMap map ) throws Exception
    {
      
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
      
        BOLO.Product product = productAdmin.getProductByID(token, productID);
         map.addAttribute("product", product);
        
        return "Products/addProductCharacteristic";
    }
    
    /***
     * Deals with newly posted Characteristic for a product and puts it into the db
     * @param newChar
     * @param productID
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping( value="/add/characteristic/{productID}", method = RequestMethod.POST)
    public String addProductCharacteristicToDB( @Valid @ModelAttribute("FormProductCharacteristic") BOLO.ProductCharacteristic newChar, 
                                                BindingResult result,
                                                @PathVariable("productID") String productID,
                                                ModelMap map ) throws Exception
    {
        if( result.hasErrors())
        {
            return addProductCharacteristicView(newChar,productID,map);
        }
        String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        
            characteristicAdmin.addProductCharacteristic(token,
                                                        productID, 
                                                        newChar.getTitle(),
                                                        newChar.getDescription());
        
        return String.format("redirect:/Product/Show/%s", productID);
    }
	
}