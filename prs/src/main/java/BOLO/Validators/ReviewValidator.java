/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO.Validators;
import BOLO.Review;
import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author stuartm
 */
@Component
public class ReviewValidator 
{
     public void validateCreateReview(Review review, ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (StringUtils.isBlank(review.getText())) {
            messages.addMessage(new MessageBuilder().error().source("text").
                defaultText("Review text cannot be blank").build());
        }
        if( StringUtils.isBlank(review.getHighlights()))
        {
             messages.addMessage(new MessageBuilder().error().source("highlights").
                defaultText("Highlights cannot be blank").build());
        }
         if( StringUtils.isBlank(review.getLowlights()))
        {
             messages.addMessage(new MessageBuilder().error().source("lowlights").
                defaultText("Lowlights cannot be blank").build());
        }
    }
}
