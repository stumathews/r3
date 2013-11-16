/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Website.Controllers;
import BSL.Interfaces.IRecommendationAdmin;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



 
/**
 * Controller that manages /about routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Controller
@RequestMapping("/Recommendation")
public class RecommendationController
{ 
    
    private BSL.Interfaces.IRecommendationAdmin recommendationAdmin;

    @Autowired
    public void setRecommendationAdmin(IRecommendationAdmin recommendationAdmin) {
        this.recommendationAdmin = recommendationAdmin;
    }
    
    /***
     * Show the create recommendation form
     * @param theRecommendation
     * @param model
     * @return 
     */
    @RequestMapping( method = RequestMethod.GET)
    public String get(ModelMap model, @ModelAttribute("NewRecommendation") BOLO.Recommendation theRecommendation)
    {        
        model.addAttribute("NewRecommendation", theRecommendation);
        return "Recommendations/CreateRecommendation"  ;
    }
    
    /***
     * takes the posted data for a recommendation and persists it into the DB
     * @param theRecommendation
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping( method = RequestMethod.POST)
    public String create( ModelMap model, @Valid @ModelAttribute("NewRecommendation") BOLO.Recommendation theRecommendation,BindingResult result) throws Exception
    {
        if( result.hasErrors())
        {
            return get(model, theRecommendation);
        }
        /*Store this nito the database*/
        recommendationAdmin.createRecommendation( Common.GetGenAdminAuthToken(), theRecommendation );
        return "redirect:/Recommendation/ShowRecommendations";
    }
    
    /***
     * Shows all the reviews page
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "ShowRecommendations", method = RequestMethod.GET)
    public String ShowAllReviews(ModelMap model) throws Exception
    {        
        List<BOLO.Recommendation> recommendations = recommendationAdmin.getAllRecommendations( Common.GetGenAdminAuthToken());
        model.addAttribute("recommendations", recommendations);
        return "Recommendations/ShowAllRecommendations";
    }
 
}