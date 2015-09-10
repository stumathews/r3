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
import DEL.Interfaces.IMeal;
import DEL.Interfaces.IMealDay;
import DEL.Meal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Stuart
 */
@Controller
@RequestMapping(value = "/today")
public class MealDayController 
{
    @Autowired
    private IMealDayService mealDayService;
    
    @Autowired
    private IMealService mealService;
    
    @RequestMapping( method = RequestMethod.GET)
    String today(Model model)
    {   
        model.addAttribute("meal", new Meal());
        model.addAttribute("allMeals", mealService.getMeals());        
        model.addAttribute("todaysMeals", getDayMeals());
        model.addAttribute("carbscount",0);
        model.addAttribute("proteinscount",0);
        model.addAttribute("fatscount",0);
        return "meals/today";
    }

    private List<IMeal> getDayMeals() 
    {
        List<IMeal> todaysMeals = new ArrayList<IMeal>();
        for( IMealDay mealday :  mealDayService.getDayMeals(TodaysDate()))
        {
            todaysMeals.add(mealday.getMeal());
        }
        return todaysMeals;
    }

    private Date TodaysDate() 
    {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date date = c.getTime(); //the midnight, that's the first second of the day.
        return date;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String todayAdd(Meal meal )
    {
        Meal m = (Meal) mealService.getMeal(meal.getId());
        mealDayService.addMealDay(TodaysDate(),m);
        return "redirect:/today";
    }
}
