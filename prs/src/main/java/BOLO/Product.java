/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

/**
 * Command object for adding a product as a form
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class Product 
{
    private String whatIsIt;
    private String whoMadeIt;
    private String title;
    //private CommonsMultipartFile picture;
    private String identifier;

    
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

//    @XmlElement( name ="productPicture")
//    public CommonsMultipartFile getPicture() {
//        return picture;
//    }
//
//    public void setPicture(CommonsMultipartFile picture) {
//        this.picture = picture;
//    }

}
