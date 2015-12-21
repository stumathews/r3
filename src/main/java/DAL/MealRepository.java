    package DAL;

import DEL.Interfaces.IMeal;
import DEL.Meal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages meals in the database
 * @author Stuart
 */
@Component
public class MealRepository implements DAL.Interfaces.IMealRepository 
{
  @Autowired
  private SessionFactory sessionFactory;
  
  private final String MEAL_CACHE = "mealCache";
  
  @Transactional  
  public Set<IMeal> GetMeals()
  {    
    HashSet<IMeal> meals = new HashSet<IMeal>();
    for( IMeal meal : (List<IMeal>) sessionFactory.getCurrentSession().createQuery("from Meal").list())
    {
      meals.add(meal);
    }
    return meals;
  }
  
  @Transactional
  @CachePut(value = MEAL_CACHE, key = "#result.Id")
  public IMeal add(IMeal meal)
  {    
    return (IMeal) sessionFactory.getCurrentSession().merge(meal);
  }
  
  @Transactional
  @CacheEvict(value = MEAL_CACHE,key = "#meal.Id" )
  public void delete(IMeal meal)
  {
    sessionFactory.getCurrentSession().delete(meal);
  }

    @Transactional
    @Cacheable(value = MEAL_CACHE)
    public IMeal GetMeal(long Id)
    {
      return (IMeal) sessionFactory.getCurrentSession().get(Meal.class, Id);   
    }

    @Transactional
    public IMeal GetMealByName(String title) 
    {
        return (IMeal) sessionFactory.getCurrentSession().createCriteria(Meal.class).add(Restrictions.eq("title", title)).uniqueResult();
    }
  
  
}
