package BSL;

import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMeal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

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
