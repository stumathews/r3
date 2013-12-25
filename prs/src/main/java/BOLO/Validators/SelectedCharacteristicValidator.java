/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOLO.Validators;

import BOLO.Recommendation;
import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stuart
 */
@Component
public class SelectedCharacteristicValidator 
{
    public void validateSelectCharacteristics(BOLO.ProductCharacteristic characteristic, ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (StringUtils.isBlank(characteristic.getTitle())) {
            messages.addMessage(new MessageBuilder().error().source("title").
                defaultText("Characteristics title cannot be blank. Please make a selection").build());
        }
        
    } 
}


