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
import DEL.MacroUnitProfile;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    @RequestMapping(method = RequestMethod.GET)
    String settings(@Valid DEL.MacroUnitProfile settings, Model model)
    {   
        MacroUnitProfile profile = settingsService.getSettings();
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        model.addAttribute("dailyamounts", resultDailyAmounts == null ? new DailyAmounts(20,17,12): resultDailyAmounts);
        model.addAttribute("settings", profile == null ? new MacroUnitProfile(0,15,7,5, "Default Macro Unit Profile"): profile);
        return "settings";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    String save(@Valid DEL.MacroUnitProfile settings, Errors errors)
    {        
        if(errors.hasErrors())
        {
          return "redirect:/settings";
        }
        
        settingsService.saveSettings(settings);
        return "redirect:/today";
    }
    
    @RequestMapping( value = "/dailyamounts", method = RequestMethod.GET)
    String dailyAmounts(@Valid DEL.DailyAmounts dailyAmounts, Model model)
    {   
        DailyAmounts resultDailyAmounts = settingsService.getDailyAmounts();
        model.addAttribute("dailyamounts", resultDailyAmounts == null ? new DailyAmounts(20,17,12): resultDailyAmounts);
        return "dailyamounts";
    }
    
    @RequestMapping(value = "/dailyamounts", method = RequestMethod.POST)
    String saveDailyAmounts(@Valid DEL.DailyAmounts dailyAmounts, Errors errors)
    {        
        if(errors.hasErrors())
        {
          return "redirect:/dailyamounts";
        }
        
        settingsService.saveDailyAmounts(dailyAmounts);
        return "redirect:/today";
    }

}
