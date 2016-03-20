package Website.Controllers;

import BSL.Interfaces.IMealDayService;
import BSL.Interfaces.IMealService;
import BSL.Interfaces.ISettingsService;
import DEL.DailyAmounts;
import DEL.MacroUnitProfile;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MacroUnitProfile Controllers manages settings pages
 * @author Stuart
 */
@Controller
@RequestMapping("/settings")
public class SettingsController 
{
    @Autowired
    private IMealDayService mealDayService;
    
    @Autowired
    private IMealService mealService;
    
    @Autowired
    private ISettingsService settingsService;
    
    @RequestMapping(value = "/unitdefinitions", method = RequestMethod.GET)
    String settings(Model model)
    {   
        MacroUnitProfile profile = settingsService.getSettings();
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        model.addAttribute(DAILY_UNITS, resultDailyAmounts == null ? new DailyAmounts(1, 20,17,12): resultDailyAmounts);
        model.addAttribute(MACRO_AMOUNTS, profile == null ? new MacroUnitProfile(1,15,7,5, "Default Macro Unit Profile"): profile);
        return MACRO_AMOUNTS;
    }
    
    @RequestMapping(value = "/unitdefinitions_json", method = RequestMethod.GET)
    @ResponseBody MacroUnitProfile settingsJSON()
    {   
        MacroUnitProfile profile = settingsService.getSettings();                      
        return profile == null ? new MacroUnitProfile(1,15,7,5, "Default Macro Unit Profile"): profile;
    }
    
    private static final String MACRO_AMOUNTS = "macro_amounts";
    private static final String DAILY_UNITS = "daily_units";
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    String save(@Valid DEL.MacroUnitProfile settings, Errors errors)
    {        
        if(errors.hasErrors())
        {
          return "redirect:/settings/unitdefinitions";
        }
        
        settingsService.saveSettings(settings);
        return "redirect:/today";
    }
    
    @RequestMapping( value = "/dailyamounts", method = RequestMethod.GET)
    String dailyAmounts(Model model)
    {   
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        model.addAttribute(DAILY_UNITS, resultDailyAmounts == null ? new DailyAmounts(1, 20,17,12): resultDailyAmounts);
        return DAILY_UNITS;
    }
    
    @RequestMapping(value = "/dailyamounts", method = RequestMethod.POST)
    String saveDailyAmounts(DEL.DailyAmounts dailyAmounts, Errors errors)
    {        
        /*@Valid not being used because it doesnt work - need to fix it*/
        if(errors.hasErrors())
        {
          return "redirect:/settings/dailyamounts";
        }
        
        settingsService.saveDailyAmounts(dailyAmounts);
        return "redirect:/today";
    }

}
