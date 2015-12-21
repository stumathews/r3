package Website.Controllers.ErrorHandling;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles the exceptions that aren't handled by the controllers themselves
 * 
 * @author Stuart Mathews
 */
@ControllerAdvice
public class GlobalErrorHandler 
{    
    @ExceptionHandler(org.springframework.jdbc.UncategorizedSQLException.class)
    public String handleJdbcException(HttpServletRequest request, Exception ex)
    {
        return "Errors/database_error";
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView  handleGeneralException(HttpServletRequest request, Exception ex)
    {   
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter( writer );
        ex.printStackTrace( printWriter );
        printWriter.flush();

        String stackTrace = writer.toString();
        
        mav.addObject("stacktrace", stackTrace);
        mav.setViewName("Errors/general_error");
    return mav;
    }
}
