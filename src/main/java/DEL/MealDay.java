package DEL;

import DEL.Interfaces.IMeal;
import java.util.Date;

/**
 * A specific instance of a meal on a day
 * @author Stuart
 */
public class MealDay implements DEL.Interfaces.IMealDay
{
    private long id;
    private Date date;
    private IMeal meal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IMeal getMeal() {
        return meal;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 67 * hash + (this.meal != null ? this.meal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MealDay other = (MealDay) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        if (this.meal != other.meal && (this.meal == null || !this.meal.equals(other.meal))) {
            return false;
        }
        return true;
    }

    public void setMeal(IMeal meal) 
    {
        this.meal = meal;
    }
}
