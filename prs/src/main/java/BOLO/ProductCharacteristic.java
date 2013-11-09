/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

import DEL.Product;
import javax.xml.bind.annotation.XmlElement;

/**
 * A Product characteristic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class ProductCharacteristic 
{
    private String title;
    private String description;
    private String review;
    private BOLO.Product product;

    
    public BOLO.Product getProduct() {
        return product;
    }

    public void setProduct(BOLO.Product product) {
        this.product = product;
    }
    
    
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
