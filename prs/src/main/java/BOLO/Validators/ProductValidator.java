package BOLO.Validators;

import BOLO.Product;
import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator 
{
     public void validateSelectProduct(Product product, ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (StringUtils.isBlank(product.getTitle())) {
            messages.addMessage(new MessageBuilder().error().source("title").
                defaultText("Product title cannot be blank").build());
        } 
        if (StringUtils.isBlank(product.getWhatIsIt())) {
            messages.addMessage(new MessageBuilder().error().source("whatIsIt").
                defaultText("Description of what product is cannot be blank").build());
        } 
        if (StringUtils.isBlank(product.getWhoMadeIt())) {
            messages.addMessage(new MessageBuilder().error().source("whoMadeIt").
                defaultText("Description of who made product cannot be blank").build());
        } 
        
    }
}
