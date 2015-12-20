package DAL.Interfaces;

import DEL.Interfaces.IMeal;
import java.util.Set;

/**
 *
 * @author Stuart
 */
public interface IMealRepository
{
  Set<IMeal> GetMeals();

  public IMeal add(IMeal meal);

  public void delete(IMeal meal);

  public IMeal GetMeal(long id);

  public IMeal GetMealByName(String title);
}
