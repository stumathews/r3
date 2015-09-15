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

import DEL.Interfaces.IMeal;
import DEL.Meal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
