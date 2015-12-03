package BSL.Interfaces;

import DEL.Interfaces.IMeal;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Service for dealing with meals
 * @author Stuart
 */

public interface IMealService
{
  public Set<IMeal> getMeals();

  public void addMeal(IMeal meal);

  public void deleteMeal(IMeal meal);

  public IMeal getMeal(long id);

  public Set<IMeal>  importMealsCSV(InputStream inputStream) throws Exception, IOException;
  public String exportMealsCSV();
}
