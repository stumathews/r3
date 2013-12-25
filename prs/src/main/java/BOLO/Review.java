/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * A review
 * @author Lenovo x220
 */
public class Review implements Serializable {

    private String text;
    private String lowlights;
    private String highlights;
    private List<BOLO.ProductCharacteristic> characteristics;
    private BOLO.User reviewer;
    private BOLO.Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Review()
    {
        characteristics = new ArrayList<BOLO.ProductCharacteristic>();
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
    public String getLowlights() {
        return lowlights;
    }

    public void setLowlights(String lowlights) {
        this.lowlights = lowlights;
    }

    
    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }
        
    public List<ProductCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    
    public BOLO.User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
    
    
}
