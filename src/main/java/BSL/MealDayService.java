package BSL;

import DAL.MealDayRepository;
import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMeal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author Stuart
 */
public class MealDayService implements BSL.Interfaces.IMealDayService
{
    static final Logger logger = Logger.getLogger(MealDayRepository.class);
    
    @Autowired
    private DAL.Interfaces.IMealDayRepository mealDayRepository;
        
    public IMealDay addMealDay( IMeal meal) 
    {        
       return mealDayRepository.addMealDay( TodaysDate() , meal );
    }

    public Set<IMealDay> getDayMeals() throws ParseException
    {
        return mealDayRepository.getDayMeals(TodaysDate());// mealdays;                
    }
    
    private Date TodaysDate() 
    {   
        DateTimeZone tz = DateTimeZone.forID("Europe/London");
        DateTime dateTime = new DateTime(tz);               
        return dateTime.toDate();
    }

    public void removeMealDay(IMealDay mealDay) 
    {
        mealDayRepository.remove(mealDay);
    }

    public IMealDay getDayMeal(long id) throws ParseException  
    {
        return mealDayRepository.getMealDay(id);
    }

    public void removeAllDayMealsWithMeal(IMeal meal) 
    {
        mealDayRepository.removeAllDayMealsWithMeal(meal);
    }
    
}
