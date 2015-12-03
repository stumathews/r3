package DEL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A setting that holds daily amounts of things
 * @author Stuart
 */
public class DailyAmounts 
{
    @NotNull
    public long id;
    @Min(1)
    public int maxCarbUnitsPerDay;
    @Min(1)
    public int maxProteinUnitsPerDay;
    @Min(1)
    public int maxFatUnitsPerDay;
    
    public DailyAmounts( long id, int maxCarbUnitsPerDay, int maxProteinUnitsPerDay, int maxFatUnitsPerDay) 
    {
        this.id = id;
        this.maxCarbUnitsPerDay = maxCarbUnitsPerDay;
        this.maxProteinUnitsPerDay = maxProteinUnitsPerDay;
        this.maxFatUnitsPerDay = maxFatUnitsPerDay;
    }

    public DailyAmounts() 
    {
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getMaxCarbUnitsPerDay() {
        return maxCarbUnitsPerDay;
    }

    public void setMaxCarbUnitsPerDay(int maxCarbUnitsPerDay) {
        this.maxCarbUnitsPerDay = maxCarbUnitsPerDay;
    }

    public int getMaxProteinUnitsPerDay() {
        return maxProteinUnitsPerDay;
    }

    public void setMaxProteinUnitsPerDay(int maxProteinUnitsPerDay) {
        this.maxProteinUnitsPerDay = maxProteinUnitsPerDay;
    }

    public int getMaxFatUnitsPerDay() {
        return maxFatUnitsPerDay;
    }

    public void setMaxFatUnitsPerDay(int maxFatUnitsPerDay) {
        this.maxFatUnitsPerDay = maxFatUnitsPerDay;
    }    
}
