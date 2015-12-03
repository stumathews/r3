package BSL.Interfaces;

import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMeal;
import java.util.Set;

/**
 * Meal Day service interface
 * @author Stuart
 */
public interface IMealDayService 
{
    public IMealDay addMealDay( IMeal meal);
    public Set<IMealDay> getDayMeals();
    public void removeMealDay(IMealDay mealDay);
    public IMealDay getDayMeal(long id);

    public void removeAllDayMealsWithMeal(IMeal meal);
}
