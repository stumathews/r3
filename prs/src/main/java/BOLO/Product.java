package BOLO;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * A product
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class Product implements Serializable
{
    @NotEmpty( message="Please roughly, specify what the product is? eg. Watch or matress etc.")
    private String whatIsIt;
    @NotEmpty( message="Please specify who originally produced the product. Eg. Apple")
    private String whoMadeIt;
    @NotEmpty( message="Please specify common, usual way to identify the product. Eg. Sunuto Ambit 2003")
    private String title;
    private String identifier;  
    
    
    private List<BOLO.ProductCharacteristic> characteristics;

    public List<ProductCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public String getWhatIsIt() {
        return whatIsIt;
    }

    public void setWhatIsIt(String whatIsIt) {
        this.whatIsIt = whatIsIt;
    }    
    
    public String getWhoMadeIt() {
        return whoMadeIt;
    }

    public void setWhoMadeIt(String whoMadeIt) {
        this.whoMadeIt = whoMadeIt;
    }    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
