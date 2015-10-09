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
package DAL;

import DEL.Interfaces.IMealDay;
import DAL.Interfaces.IMealDayRepository;
import DEL.Interfaces.IMeal;
import DEL.Meal;
import DEL.MealDay;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MealDayRepository implements IMealDayRepository 
{    
    @Autowired
    private SessionFactory sessionFactory;  
    
    private final String MEALDAY_CACHE = "mealDayCache";
    
    @Transactional
    public IMealDay addMealDay(final Date date, final IMeal meal) 
    {
        MealDay mealDay = new MealDay();
        mealDay.setDate(date);
        mealDay.setMeal(meal);
        
        Serializable id = sessionFactory.getCurrentSession().save(mealDay);
        return (IMealDay) sessionFactory.getCurrentSession().get(MealDay.class, id);
    }
    
    @Transactional
    public Set<IMealDay> getDayMeals(Date date) 
    {
       Calendar c = new GregorianCalendar(LocaleContextHolder.getLocale());       
        c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        Date begining_day = c.getTime();
        
                
        HashSet<IMealDay> meals = new HashSet<IMealDay>(); 
        
              
                
        Criteria query = sessionFactory.getCurrentSession()
                    .createCriteria(MealDay.class)
                    .add(Restrictions.ge("date", new Timestamp(begining_day.getTime())))
                    //.add(Restrictions.gt("date", new Timestamp(date.getTime())))
                    //.add(Restrictions.lt("date", new Timestamp(toDate.getTime())))
                    .addOrder(Order.desc("id"));
        long from = date.getTime();
        
       
        for( IMealDay mealDay : (List<IMealDay>) query.list())
        {
          meals.add(mealDay);
        }
    return meals;
    }

    @Transactional
    public void remove(IMealDay mealDay) 
    {        
        sessionFactory.getCurrentSession().delete(mealDay);
    }

    @Transactional
    public IMealDay getMealDay(long id) 
    {
       return (IMealDay) sessionFactory.getCurrentSession().get(MealDay.class, id);
    }
    
    @Transactional
    public void removeAllDayMealsWithMeal(IMeal meal) 
    {
        Query q = sessionFactory.getCurrentSession().createQuery("delete from MealDay where meal_id = :meal_id");
        q.setLong("meal_id", meal.getId());
        q.executeUpdate();
    }

    
}
