package DEL.Interfaces;

/**
 * A Meal
 * @author Stuart
 */
public interface IMeal
{
  
  public long getId();
  public void setId(long id);   
  
  public String getTitle();
  public void setTitle(String title);
  
  public float getCarbs();
  public void setCarbs(float carbs);
  
  public float getProteins();
  public void setProteins(float proteins);
  
  public float getFats();
  public void setFats(float fats);
  
}
