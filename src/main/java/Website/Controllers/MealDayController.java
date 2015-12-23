package Website.Controllers;

import BSL.Interfaces.IMealDayService;
import BSL.Interfaces.IMealService;
import BSL.Interfaces.ISettingsService;
import DAL.MealDayRepository;
import DEL.DailyAmounts;
import DEL.Interfaces.IMeal;
import DEL.MacroUnitProfile;
import DEL.Meal;
import java.text.ParseException;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;
import java.util.TimeZone;

@Controller

public class MealDayController 
{
    @Autowired
    private IMealDayService mealDayService;
    
    @Autowired
    private IMealService mealService;
    
    @Autowired
    private ISettingsService settingsService;
    
    static final Logger logger = Logger.getLogger(MealDayRepository.class);
    
    @RequestMapping(value = "/today/history", method = RequestMethod.GET)
    String viewAllMealsHistory(Model model) throws ParseException
    { 
        MacroUnitProfile defaultMacroUnitProfile = settingsService.getSettings();  
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        
        model.addAttribute("dailyamounts", resultDailyAmounts == null ? new DailyAmounts(1, 20,17,12): resultDailyAmounts);
        model.addAttribute("settings", defaultMacroUnitProfile == null ? new MacroUnitProfile(1,15,7,5,"Default"): defaultMacroUnitProfile);
        model.addAttribute("allMeals", mealService.getMeals());        
        model.addAttribute("allDayMeals", mealDayService.getDayMealsFromDateOnwards(TimeZone.getTimeZone("GMT"), null,"d MMM HH:mm")); // get all
        
        return "mealday/history";
    }
    
    @RequestMapping( value = {"/","/today"}, method = RequestMethod.GET)
    String today(Model model) throws ParseException
    {           
        MacroUnitProfile defaultMacroUnitProfile = settingsService.getSettings();  
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        
        model.addAttribute("dailyamounts", resultDailyAmounts == null ? new DailyAmounts(1, 20,17,12): resultDailyAmounts);
        model.addAttribute("settings", defaultMacroUnitProfile == null ? new MacroUnitProfile(1,15,7,5,"Default"): defaultMacroUnitProfile);

        // This could be a repost with errors, in which case we have a meal already
        if( !model.containsAttribute("meal"))
        {
            model.addAttribute("meal", new Meal()); 
        }
        model.addAttribute("allMeals", mealService.getMeals());        
        model.addAttribute("todaysMeals", mealDayService.getDayMealsFromDateOnwards(TimeZone.getTimeZone("GMT"), new Date(),"HH:mm:ss"));        
        model.addAttribute("localdate", new Date().toString());        
          
        return "meals/today";
    }

    @RequestMapping(value = "/today/add", method = RequestMethod.POST)
    String todayAdd(@Valid Meal meal, Errors errors, Model model) throws ParseException
    {
        if(errors.hasErrors())
        {
          return today(model);
        }
        
        IMeal found = (IMeal) mealService.getMealByName(meal.getTitle());
        
        if(found == null)
        {
            errors.rejectValue("title", "validation.notfound","meal not found");
            return today(model);
        }
        
        mealDayService.addMealDay(found);
        return today(model);
    }
    
    @RequestMapping(value = "/today/addbyId/{id}", method = RequestMethod.POST)
    String addbyId(@PathVariable int id)
    {
        Meal m = (Meal) mealService.getMeal(id);
        mealDayService.addMealDay(m);
        return "redirect:/today";
    }
   
    
    @RequestMapping(value = "/today/delete/{id}", method = RequestMethod.POST)
    String deleteMeal(@PathVariable int id ) throws ParseException
    {           
        mealDayService.removeMealDay(mealDayService.getDayMeal(id, TimeZone.getTimeZone("GMT"),"HH:mm:ss"));
        return "redirect:/today";
    }    
}
