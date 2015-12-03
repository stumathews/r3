package DEL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The settings for the web app
 * @author Stuart Mathews
 */
public class MacroUnitProfile 
{

    public MacroUnitProfile() 
    {
    }
    
    public MacroUnitProfile(long id, int carbUnit, int proteinUnit, int fatUnit, String description) 
    {
        this.id = id;
        this.carbUnit = carbUnit;
        this.proteinUnit = proteinUnit;
        this.fatUnit = fatUnit;
        this.description = description;
    }
    
    @NotNull
    private long id;
    @Min(1)  
    private int carbUnit;
    @Min(1)  
    private int proteinUnit;
    @Min(1)  
    private int fatUnit; 
    
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCarbUnit() {
        return carbUnit;
    }

    public void setCarbUnit(int carbUnit) {
        this.carbUnit = carbUnit;
    }

    public int getProteinUnit() {
        return proteinUnit;
    }

    public void setProteinUnit(int proteinUnit) {
        this.proteinUnit = proteinUnit;
    }

    public int getFatUnit() {
        return fatUnit;
    }

    public void setFatUnit(int fatUnit) {
        this.fatUnit = fatUnit;
    }
    
}
