
package BOLO.Wrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class RecommendationList 
{    
    @XmlElementWrapper(name="recommendations")    
    public List<BOLO.Recommendation> items;
}
