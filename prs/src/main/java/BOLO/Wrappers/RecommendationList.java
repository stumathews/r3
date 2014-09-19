package BOLO.Wrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

public class RecommendationList 
{    
    @XmlElementWrapper(name="recommendations")    
    public List<BOLO.Recommendation> items;
}
