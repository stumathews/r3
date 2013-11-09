/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Website.Controllers;
import BSL.Interfaces.IReviewAdmin;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



 
/**
 * Controller that manages /about routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Controller
@RequestMapping("/Review")
public class ReviewController
{ 
    /**
     * Default get method show the about page view
     * @param model
     * @return show the about page view
     */
    
    private BSL.Interfaces.IReviewAdmin reviewAdmin;

    @Autowired
    public void setReviewAdmin(IReviewAdmin reviewAdmin) {
        this.reviewAdmin = reviewAdmin;
    }    
    
    /***
     * Show the new review page
     * @param theReview
     * @param model
     * @return 
     */
    @RequestMapping( method = RequestMethod.GET)
    public String get(@ModelAttribute("NewReview") BOLO.Review theReview, ModelMap model)
    {
        model.addAttribute("NewReview", theReview);
        return "Reviews/CreateReview"  ;
    }
    
    /***
     * Deal with a new review data and persist it to the db
     * @param theReview
     * @param model
     * @return 
     */
    @RequestMapping( method = RequestMethod.POST)
    public String post(@ModelAttribute("NewReview") BOLO.Review theReview, ModelMap model)
    {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
            BOLO.User user = new BOLO.User();
            user.setUsername(auth.getName());            
            theReview.setReviewer(user);
            reviewAdmin.SaveReview(Common.GetGenAdminAuthToken(), theReview);
            
        } catch (Exception ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return "redirect:/Review/ShowAllReviews";
    }
    
    /***
     * Shows all the reviews page
     * @param model
     * @return 
     */
    @RequestMapping(value = "ShowAllReviews", method = RequestMethod.GET)
    public String ShowAllReviews(ModelMap model) throws Exception
    {
        List<BOLO.Review> reviews = new ArrayList<BOLO.Review>();
        reviews = reviewAdmin.getAllReviews(Common.GetGenAdminAuthToken());
       
        return "Reviews/ShowAllReviews";
    }
         
}