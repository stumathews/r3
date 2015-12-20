package BSL;

import DEL.Interfaces.IMeal;
import DEL.Meal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stuart
 */
@Component
public class MealService implements BSL.Interfaces.IMealService
{
  @Autowired
  private DAL.Interfaces.IMealRepository mealRepository;
  
  public Set<IMeal> getMeals()
  {
      return mealRepository.GetMeals();
  }

  public void addMeal(IMeal meal)
  {
    mealRepository.add(meal);
  }

  public void deleteMeal(IMeal meal)
  {
    mealRepository.delete(meal);
  }

  public IMeal getMeal(long id)
  {
   return mealRepository.GetMeal(id);
  }

  public String exportMealsCSV()
  {
      StringBuilder sb = new StringBuilder();      
      for ( IMeal meal : mealRepository.GetMeals())
      {
          sb.append(meal.getTitle()).append(",");
          sb.append(meal.getCarbs()).append(",");
          sb.append(meal.getProteins()).append(",");
          sb.append(meal.getFats());
          sb.append(System.getProperty("line.separator"));          
      }
      return sb.toString();
  }
  
    public Set<IMeal> importMealsCSV(InputStream inputStream) throws Exception, IOException
    {

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
         String line = bufferedReader.readLine();
         Set<IMeal> meals = new HashSet<IMeal>();
         while(line != null)
         {
             String[] items = line.split(",");
             if( items.length != 4 || items[0].isEmpty() || items[1].isEmpty() || items[2].isEmpty() || items[3].isEmpty() )
             {
                 throw new Exception("Invalid CSV format");
             }
             
             String mealname = items[0];
             String mealCarbs = items[1];
             String mealProtiens = items[2];
             String mealFats = items[3];
             IMeal meal = new Meal();
                meal.setTitle(mealname);
                meal.setCarbs(Integer.parseInt(mealCarbs));
                meal.setProteins(Integer.parseInt(mealProtiens));
                meal.setFats(Integer.parseInt(mealFats));
                meals.add(meal);
                mealRepository.add(meal);
             line = bufferedReader.readLine();
         }
         return meals;
    }

    public IMeal getMealByName(String title) 
    {
        return mealRepository.GetMealByName(title);
    }

    
}
