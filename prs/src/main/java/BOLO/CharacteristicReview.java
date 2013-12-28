/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOLO;

import java.io.Serializable;

/**
 * Represents a users review on a characteristic
 * @author Stuart
 */
public class CharacteristicReview implements Serializable
{
    private Long id;
    private BOLO.ProductCharacteristic characteristic;
    private BOLO.User user;
    private String review_text;
    
    public CharacteristicReview()
    {
        id = 0L;
        characteristic = new ProductCharacteristic();
        user = new User();
        review_text = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCharacteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(ProductCharacteristic characteristic) {
        this.characteristic = characteristic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }
    
    
    
}
