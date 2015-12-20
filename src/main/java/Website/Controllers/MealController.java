
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
    
  @RequestMapping(method = RequestMethod.GET)
  String all(Model model)    
  {
    model.addAttribute("meals", mealService.getMeals());
      return "meals/all";    
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  String view(@PathVariable long id, Model model)
  {
    model.addAttribute("meal", mealService.getMeal(id));
    return "meals/view";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.GET)
  String add(Model model)
  {
    model.addAttribute(new Meal());
    return "meals/add";
  }
  
  @RequestMapping(value = "create", method = RequestMethod.POST)
  String create(
          @Valid Meal meal,
          Errors errors)
  {
    if(errors.hasErrors())
    {
      return "/meals/add";
    }
    mealService.addMeal(meal);
    return "redirect:/";
  }
  
  @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
  String delete( @PathVariable long id)
  {
    // We can only delete the meal if its not associated with any meal days.
    
    IMeal meal = mealService.getMeal(id);
    mealDayService.removeAllDayMealsWithMeal(meal);
    mealService.deleteMeal(meal);
    return "redirect:/";
  }  
  
  @RequestMapping(value="/upload", method=RequestMethod.POST)
  public String upload( @RequestPart("csv") Part csv, Model model) throws IOException, Exception
  {      
      model.addAttribute("meals", mealService.importMealsCSV(csv.getInputStream()));
      return "meals/uploadstatus";
  }
  
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
