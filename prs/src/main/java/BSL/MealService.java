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
  
}
