/*
 * Copyright (c) 2015, Stuart
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package Website.Controllers;

import BSL.Interfaces.IMealDayService;
import BSL.Interfaces.IMealService;
import BSL.Interfaces.ISettingsService;
import DEL.DailyAmounts;
import DEL.Interfaces.IMeal;
import DEL.Interfaces.IMealDay;
import DEL.MacroUnitProfile;
import DEL.Meal;
import DEL.MealDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Stuart
 */
@Controller

public class MealDayController 
{
    @Autowired
    private IMealDayService mealDayService;
    
    @Autowired
    private IMealService mealService;
    
    @Autowired
    private ISettingsService settingsService;

    @RequestMapping( value = {"/","/today"}, method = RequestMethod.GET)
    String today(Model model)
    {   
        MacroUnitProfile defaultMacroUnitProfile = settingsService.getSettings();  
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        model.addAttribute("dailyamounts", resultDailyAmounts == null ? new DailyAmounts(1, 20,17,12): resultDailyAmounts);
        model.addAttribute("settings", defaultMacroUnitProfile == null ? new MacroUnitProfile(1,15,7,5,"Default"): defaultMacroUnitProfile);
        model.addAttribute("meal", new Meal());
        model.addAttribute("allMeals", mealService.getMeals());        
        model.addAttribute("todaysMeals", mealDayService.getDayMeals());
        return "meals/today";
    }

    @RequestMapping(value = "/today/add", method = RequestMethod.POST)
    String todayAdd(Meal meal)
    {
        Meal m = (Meal) mealService.getMeal(meal.getId());
        mealDayService.addMealDay(m);
        return "redirect:/today";
    }
    @RequestMapping(value = "/today/addbyId/{id}", method = RequestMethod.POST)
    String addbyId(@PathVariable int id)
    {
        Meal m = (Meal) mealService.getMeal(id);
        mealDayService.addMealDay(m);
        return "redirect:/today";
    }
    
    
    @RequestMapping(value = "/today/delete/{id}", method = RequestMethod.POST)
    String deleteMeal(@PathVariable int id )
    {           
        mealDayService.removeMealDay(mealDayService.getDayMeal(id));
        return "redirect:/today";
    }
}
