package DAL;

import DEL.Interfaces.IMealDay;
import DAL.Interfaces.IMealDayRepository;
import DEL.Interfaces.IMeal;
import DEL.MealDay;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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
        Calendar c = new GregorianCalendar();
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
            DateTimeZone tz = DateTimeZone.forID("Europe/London");
            DateTime dt = new DateTime(tz);               
            GregorianCalendar cal = dt.toGregorianCalendar();
            mealDay.setDate(cal.getTime());
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
       IMealDay md = (IMealDay) sessionFactory.getCurrentSession().get(MealDay.class, id);    
        DateTimeZone tz = DateTimeZone.forID("Europe/London");
            DateTime dt = new DateTime(tz);               
            GregorianCalendar cal = dt.toGregorianCalendar();
            md.setDate(cal.getTime());
       return md;
    }
    
    @Transactional
    public void removeAllDayMealsWithMeal(IMeal meal) 
    {
        Query q = sessionFactory.getCurrentSession().createQuery("delete from MealDay where meal_id = :meal_id");
        q.setLong("meal_id", meal.getId());
        q.executeUpdate();
    }
}
