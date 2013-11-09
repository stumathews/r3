package DEL;

import java.util.List;

/**
 * A characteristic can belong to a group which contains other like minded characteristics
 * @author Lenovo x220
 */
public class CharacteristicArea 
{
    private long id;
    List<Characteristic> characteristics;

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
    private String areaName; // GPS
    private String areaDescription; // such as GPS singal or GPS battery    
 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }
        
}
