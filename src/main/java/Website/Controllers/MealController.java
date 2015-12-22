
package Website.Controllers;

import DEL.Interfaces.IMeal;
import DEL.Meal;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * Deals with meals
 * @author Stuart
 */
@Controller()
@RequestMapping({"/meals"})
public class MealController
{
  @Autowired
  private BSL.Interfaces.IMealService mealService;
  
  @Autowired 
  private BSL.Interfaces.IMealDayService mealDayService;
    
  /***
   * Gets all the meal definitions
   * @param model
   * @return view
   */
  
  @RequestMapping(method = RequestMethod.GET)
  String all(Model model)    
  {
    model.addAttribute("meals", mealService.getMeals());
    return "meals/all";    
  }
  
  /***
   * Show a specific meal
   * @param id the specific meal id
   * @param model
   * @return view page
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  String view(@PathVariable long id, Model model)
  {
    model.addAttribute("meal", mealService.getMeal(id));
    return "meals/view";
  }
  
  /***
   * Add a new meal definition
   * @param model
   * @return add view
   */
  @RequestMapping(value = "add", method = RequestMethod.GET)
  String add(Model model)
  {
    model.addAttribute(new Meal());
    model.addAttribute("addOrUpdate","Add");
    return "meals/add";
  }
  
  /***
   * Gets a meal, loads it up into the add/edit view
   * @param mealid meal to edit
   * @param model
   * @return add/edit view
   */
  @RequestMapping(value = "edit/{mealid}", method = RequestMethod.GET)
  String edit(@PathVariable long mealid, Model model)
  {    
    model.addAttribute("meal", mealService.getMeal(mealid));
    model.addAttribute("addOrUpdate","Update");
    return "meals/add";
  }
  
  /***
   * Creates/Updates a meal
   * @param meal
   * @param errors any errors on the page (validation)
   * @return home view
   */
  @RequestMapping(value = "create", method = RequestMethod.POST)
  String create( @Valid Meal meal, Errors errors)
  {
    if(errors.hasErrors()) { return "/meals/add"; }
    mealService.addMeal(meal);
    return "redirect:/";
  }
  
  /***
   * Deletes a particular meal. 
   * Note: This will delete any meal days associated with this meal (careful!)
   * @param id of the meal to delete
   * @return home page   * 
   */
  @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
  String delete( @PathVariable long id)
  {   
    IMeal meal = mealService.getMeal(id);
    
    // We can only delete the meal if its not associated with any meal days.    
    mealDayService.removeAllDayMealsWithMeal(meal);
    mealService.deleteMeal(meal);
    return "redirect:/";
  }  
  
  /***
   * Upload a new CSV of meal definitions
   * @param csv
   * @param model
   * @return result view
   * @throws IOException
   * @throws Exception 
   */
  @RequestMapping(value="/upload", method=RequestMethod.POST)
  public String upload( @RequestPart("csv") Part csv, Model model) throws IOException, Exception
  {      
      model.addAttribute("meals", mealService.importMealsCSV(csv.getInputStream()));
      return "meals/uploadstatus";
  }
  
  /***
   * Exports all the meal definitions in a csv file
   * @param response csv file 
   * @throws IOException 
   */
  @RequestMapping(value="/export", method=RequestMethod.GET)
  public void export(HttpServletResponse response) throws IOException 
  {
        response.setContentType("text/csv");
        String name = "meals.csv";
        response.setHeader("Content-disposition", "attachment;filename="+name);
        response.getOutputStream().print(mealService.exportMealsCSV());
        response.getOutputStream().flush();
  }
}
