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
package BSL;

import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMeal;
import DEL.Meal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.support.RequestContext;

/**
 *
 * @author Stuart
 */
public class MealDayService implements BSL.Interfaces.IMealDayService
{
    @Autowired
    private DAL.Interfaces.IMealDayRepository mealDayRepository;
        
    public IMealDay addMealDay( IMeal meal) 
    {        
       return mealDayRepository.addMealDay( TodaysDate() , meal );
    }

    public Set<IMealDay> getDayMeals() 
    {
        return mealDayRepository.getDayMeals(TodaysDate() );// mealdays;                
    }
    
    private Date TodaysDate() 
    {
       
        Calendar c = new GregorianCalendar(LocaleContextHolder.getTimeZone(),LocaleContextHolder.getLocale());
        Date date = c.getTime(); //the midnight, that's the first second of the day.
        return date;
    }

    public void removeMealDay(IMealDay mealDay) 
    {
        mealDayRepository.remove(mealDay);
    }

    public IMealDay getDayMeal(long id) 
    {
        return mealDayRepository.getMealDay(id);
    }

    public void removeAllDayMealsWithMeal(IMeal meal) 
    {
        mealDayRepository.removeAllDayMealsWithMeal(meal);
    }
    
}
