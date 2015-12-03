package DEL;

import DEL.Interfaces.IMeal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Meal entity
 * @author Stuart
 */
public class Meal implements IMeal
{
  
  @NotNull
  private long id;
  
  @NotEmpty  
  private String title;
  
  @Min(0)  
  private float carbs;
  
  @Min(0)  
  private float proteins;
  
  @Min(0)  
  private float fats;

  public Meal(String title, float carbs, float proteins, float fats)
  {
    this.title = title;
    this.carbs = carbs;
    this.proteins = proteins;
    this.fats = fats;
  }

  public Meal()
  {
  }
  
  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }
  
   
  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public float getCarbs()
  {
    return carbs;
  }

  public void setCarbs(float carbs)
  {
    this.carbs = carbs;
  }

  public float getProteins()
  {
    return proteins;
  }

  public void setProteins(float proteins)
  {
    this.proteins = proteins;
  }

  public float getFats()
  {
    return fats;
  }

  public void setFats(float fats)
  {
    this.fats = fats;
  }

  @Override
  public int hashCode()
  {
    int hash = 7;
    hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 97 * hash + (this.title != null ? this.title.hashCode() : 0);
    hash = (int) (97 * hash + this.carbs);
    hash = (int) (97 * hash + this.proteins);
    hash = (int) (97 * hash + this.fats);
    return hash;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Meal other = (Meal) obj;
    if (this.id != other.id)
      return false;
    if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title))
      return false;
    if (this.carbs != other.carbs)
      return false;
    if (this.proteins != other.proteins)
      return false;
    if (this.fats != other.fats)
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "Meal{" + "title=" + title + ", carbs=" + carbs + ", proteins=" + proteins + ", fats=" + fats + '}';
  }
}
