/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * A review
 * @author Lenovo x220
 */
public class Review {

    private String text;
    private String lowlights;
    private String highlights;
    private List<BOLO.ProductCharacteristic> characteristics;
    private BOLO.User reviewer;
    
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
