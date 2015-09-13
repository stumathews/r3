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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        HashSet<IMealDay> meals = new HashSet<IMealDay>();
        
        Query q = sessionFactory.getCurrentSession().createQuery("from MealDay md where md.date = :date");
        
        q.setDate("date", date);
        
        for( IMealDay mealDay : (List<IMealDay>) q.list())
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

    
}
