
package BOLO.Wrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * because JAX-WS cannot deal with exposing Interfaces such as a Collection as a return 
 * type of a web method, or an ArrayList(not an interface), we have to wrap such things in a concreate class
 * which much be annotated to signifiy the collections within. These will then
 * be silently turned into arrays! yay!
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class ReviewList
{
    /* This XmlElementWrapper is important for Java collections to be exposed */
    @XmlElementWrapper(name="reviews")
    public List<BOLO.Review> items;       

}
