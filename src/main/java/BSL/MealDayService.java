package BSL;

import DAL.MealDayRepository;
import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMeal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stuart
 */
public class MealDayService implements BSL.Interfaces.IMealDayService
{
    static final Logger logger = Logger.getLogger(MealDayRepository.class);
    
    @Autowired
    private DAL.Interfaces.IMealDayRepository mealDayRepository;
        
    public IMealDay addMealDay(IMeal meal) 
    {        
       return mealDayRepository.addMealDay(new Date() , meal);
    }

    public List<IMealDay> getDayMealsFromDateOnwards(TimeZone timeZone, Date from) throws ParseException
    {
        List<IMealDay> mealDays = mealDayRepository.getDayMealsFromDateOnwards(from);
        
        // Convert to GTM time as thats what I'm in
        for( IMealDay mealDay : mealDays )
        {            
            mealDay.setLocalTimeString(ConvertDateToTimeZoneTimeString(mealDay.getDate(), timeZone));  
        }
        return mealDays;               
    }
    
    public void removeMealDay(IMealDay mealDay) 
    {
        mealDayRepository.remove(mealDay);
    }

    public IMealDay getDayMeal(long id, TimeZone timeZone) throws ParseException  
    {
        IMealDay mealDay = mealDayRepository.getMealDay(id);
        
        // Convert localtime to GMT, thats where I be at.
        if( mealDay != null)
        {
            mealDay.setLocalTimeString(ConvertDateToTimeZoneTimeString(mealDay.getDate(),timeZone));  
        }
        return mealDay;        
    }

    public void removeAllDayMealsWithMeal(IMeal meal) 
    {
        mealDayRepository.removeAllDayMealsWithMeal(meal);
    }

    /***
     * Computes the localtime of the date, according to timezone
     * @param date raw date instance
     * @param timeZone timezone to represent date in
     * @return  string time in timezone
     */
    private String ConvertDateToTimeZoneTimeString(Date date, TimeZone timeZone) 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }
    
}
