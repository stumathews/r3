package DAL.Interfaces;

import DEL.Interfaces.IMeal;
import DEL.Interfaces.IMealDay;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Stuart
 */public interface IMealDayRepository 
{
    public IMealDay addMealDay(Date date, IMeal meal);    

    public Set<IMealDay> getDayMeals(Date date) throws ParseException;

    public void remove(IMealDay mealDay);

    public IMealDay getMealDay(long id) throws ParseException;

    public void removeAllDayMealsWithMeal(IMeal meal);
}
