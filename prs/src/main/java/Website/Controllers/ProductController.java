package Website.Controllers;
 
import BOL.Interfaces.IProduct;
import BOL.com.dotcypress.ljbeetle.upload.ImageShackHosting;
import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IProductAdmin;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
        // Get all products in the database
        ArrayList< BOLO.Product > products = productAdmin.getAllProducts( Common.GetGenAdminAuthToken() );
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
        return productAdmin.getProductByID(Common.GetGenAdminAuthToken(), productID);
    }
    
    

    /**
     * We store images on ImageShack, not locally. Lets not thrash imageshack's servers unless we're live. Use past summbited, cached dummy URL
     * @param product form command aka form backing object
     * @return url of pre cached image on imageshack's server
     */
    private String CacheImageURL(BOLO.Product product) 
    {        
        String url = "http://lorempixel.com/149/207";
        
//        if (!DEBUG) 
//        {
//            if( !product.getPicture().isEmpty())
//            {
//                url = ImageShackHosting.upload(product.getPicture().getBytes());
//            }
//        }
        
        return url;
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
        model.addAttribute("NewProduct", form);

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
        String token = Common.GetGenAdminAuthToken();
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
       BOLO.Product newProduct = productLogic.MakeProductFormFromId(productID);
        
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
        BOLO.Product prod = productAdmin.getProductByID(Common.GetGenAdminAuthToken(), productID);        
        List<DEL.Characteristic> productCharacteristics = characteristicAdmin.getProductCharacteristics(Common.GetGenAdminAuthToken(), productID);
        model.addAttribute("productCharacteristics", productCharacteristics);
        model.addAttribute("product",prod);
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
    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public String addProductToDB(ModelMap model, @Valid @ModelAttribute("NewProduct") BOLO.Product product, BindingResult result) throws Exception
    {	

        if( result.hasErrors())
        {
            //return addProductView(product, model);            
            return "Products/AddProduct";
        }                
               
        String url = CacheImageURL(product);        

        //prod.setImageURL(url); 
        productAdmin.addProduct( Common.GetGenAdminAuthToken() , product);	

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
        BOLO.Product product = productLogic.MakeProductFormFromId(productID);
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
            characteristicAdmin.addProductCharacteristic(Common.GetGenAdminAuthToken(),
                                                        productID, 
                                                        newChar.getTitle(),
                                                        newChar.getDescription(),
                                                        newChar.getReview());
        
        return String.format("redirect:/Product/Show/%s", productID);
    }
	
}