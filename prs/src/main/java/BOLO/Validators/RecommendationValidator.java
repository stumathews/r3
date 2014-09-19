package BOLO.Validators;
import BOLO.Recommendation;
import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

/**
 * Validator for BOLO.Recommendation objects
 * @author stuartm
 */
@Component
public class RecommendationValidator 
{
     public void validateCreateRecommendation(Recommendation recommendation, ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (StringUtils.isBlank(recommendation.getTitle())) {
            messages.addMessage(new MessageBuilder().error().source("title").
                defaultText("Recommenation title cannot be blank").build());
        }
        
    }
}
